/*
 * [y] hybris Platform
 *
 * Copyright (c) 2017 SAP SE or an SAP affiliate company.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 *
 *
 */
package de.hybris.platform.assistedserviceservices.impl;

import de.hybris.platform.assistedserviceservices.AssistedServiceService;
import de.hybris.platform.assistedserviceservices.constants.AssistedserviceservicesConstants;
import de.hybris.platform.assistedserviceservices.exception.AssistedServiceCartBindException;
import de.hybris.platform.assistedserviceservices.exception.AssistedServiceException;
import de.hybris.platform.assistedserviceservices.utils.AssistedServiceSession;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.customer.CustomerAccountService;
import de.hybris.platform.commerceservices.customer.DuplicateUidException;
import de.hybris.platform.commerceservices.model.user.StoreEmployeeGroupModel;
import de.hybris.platform.commerceservices.order.CommerceCartService;
import de.hybris.platform.commerceservices.search.dao.PagedGenericDao;
import de.hybris.platform.core.model.order.AbstractOrderModel;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.OrderModel;
import de.hybris.platform.core.model.security.PrincipalGroupModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.order.CartService;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.i18n.CommonI18NService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.session.SessionService;
import de.hybris.platform.servicelayer.user.UserService;
import de.hybris.platform.site.BaseSiteService;
import de.hybris.platform.storelocator.model.PointOfServiceModel;
import de.hybris.platform.util.localization.Localization;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;


public class DefaultAssistedServiceService implements AssistedServiceService
{
	private static final Logger LOG = Logger.getLogger(DefaultAssistedServiceService.class);

	private CartService cartService;
	private SessionService sessionService;
	private UserService userService;
	private BaseSiteService baseSiteService;
	private ModelService modelService;
	private FlexibleSearchService flexibleSearchService;
	private CommerceCartService commerceCartService;
	private CustomerAccountService customerAccountService;
	private CommonI18NService commonI18NService;
	private PagedGenericDao<CartModel> cartModelDao;

	@Override
	public List<CustomerModel> getSuggestedCustomers(final String username)
	{
		final StringBuffer buf = new StringBuffer();

		// get suggested customers using wildcard search by uid and name
		buf.append("SELECT DISTINCT {p:" + CustomerModel.PK + "} ");
		buf.append("FROM {" + CustomerModel._TYPECODE + " AS p ");
		buf.append("JOIN " + PrincipalGroupModel._PRINCIPALGROUPRELATION + " AS pgr ");
		buf.append("ON {p:pk} = {pgr:source} ");
		buf.append("JOIN " + PrincipalGroupModel._TYPECODE + " AS pg ");
		buf.append("ON {pgr:target} = {pg:pk} } ");
		buf.append("WHERE NOT {" + CustomerModel.UID + "}='anonymous' ");
		buf.append("AND (LOWER({p:" + CustomerModel.UID + "}) LIKE CONCAT(?username, '%') ");
		buf.append("OR LOWER({p:name}) LIKE CONCAT('%', CONCAT(?username, '%'))) ");
		buf.append("AND {pg.uid} <> '" + AssistedserviceservicesConstants.AS_AGENT_GROUP_UID + "'");

		final FlexibleSearchQuery query = new FlexibleSearchQuery(buf.toString());
		query.addQueryParameter("username", username.toLowerCase());
		final List<CustomerModel> suggestedUsers = new ArrayList<CustomerModel>(
				getFlexibleSearchService().<CustomerModel> search(query).getResult());
		Collections.sort(suggestedUsers, Comparator.comparing(CustomerModel::getName).thenComparing(CustomerModel::getUid));
		return suggestedUsers;
	}

	@Override
	public void bindCustomerToCart(final String customerId, final String cartId) throws AssistedServiceException
	{
		final UserModel customer = customerId == null ? getUserService().getCurrentUser()
				: getUserService().getUserForUID(customerId);
		final CartModel cart = cartId == null ? getCartService().getSessionCart()
				: getCartByCode(cartId, getUserService().getAnonymousUser());
		if (cart == null || !getUserService().isAnonymousUser(cart.getUser()))
		{
			throw new AssistedServiceCartBindException(Localization.getLocalizedString("asm.bindCart.error.not_anonymous_cart"));
		}
		getUserService().setCurrentUser(customer);
		getCartService().setSessionCart(cart);
		getCartService().changeCurrentCartUser(customer);
		getAsmSession().setEmulatedCustomer(customer);
	}

	@Override
	public AssistedServiceSession getAsmSession()
	{
		return getSessionService().getAttribute(AssistedserviceservicesConstants.ASM_SESSION_PARAMETER);
	}

	@Override
	public void restoreCartToUser(final CartModel cart, final UserModel user)
	{
		if (user != null)
		{
			if (CollectionUtils.isNotEmpty(cart.getEntries()))
			{
				getCartService().changeCurrentCartUser(user);
				// refresh persistence context after cart user manipulations
				// without this step - customer will not have modified cart
				getModelService().refresh(user);
			}
		}
	}

	@Override
	public CustomerModel createNewCustomer(final String customerId, final String customerName) throws DuplicateUidException
	{
		final CustomerModel customerModel = getModelService().create(CustomerModel.class);

		customerModel.setName(customerName.trim());
		customerModel.setUid(customerId);
		customerModel.setLoginDisabled(false);
		customerModel.setSessionCurrency(getCommonI18NService().getCurrentCurrency());
		customerModel.setSessionLanguage(getCommonI18NService().getCurrentLanguage());
		getCustomerAccountService().register(customerModel, null);
		LOG.info(String.format("New customer has been created via ASM: uid [%s]", customerId));

		return customerModel;
	}

	@Override
	public Collection<CartModel> getCartsForCustomer(final CustomerModel customer)
	{
		final BaseSiteModel paramBaseSiteModel = getBaseSiteService().getCurrentBaseSite();
		return getCommerceCartService().getCartsForSiteAndUser(paramBaseSiteModel, customer);
	}

	@Override
	public UserModel getCustomer(final String customerId)
	{
		if (StringUtils.isBlank(customerId))
		{
			return getUserService().getAnonymousUser();
		}
		else
		{
			final StringBuffer buf = new StringBuffer();

			// select the chosen customer using his UID or CustomerId
			buf.append("SELECT DISTINCT {p:" + CustomerModel.PK + "} ");
			buf.append("FROM {" + CustomerModel._TYPECODE + " as p } ");
			buf.append("WHERE {p:" + CustomerModel.UID + "} = ?customerId ");
			buf.append("OR {p:" + CustomerModel.CUSTOMERID + "} = ?customerId ");

			final FlexibleSearchQuery query = new FlexibleSearchQuery(buf.toString());
			query.addQueryParameter("customerId", customerId);
			final List<CustomerModel> matchCustomers = getFlexibleSearchService().<CustomerModel> search(query).getResult();
			if (CollectionUtils.isEmpty(matchCustomers))
			{
				throw new UnknownIdentifierException(
						(new StringBuilder("Cannot find user with id '")).append(customerId).append("'").toString());
			}
			if (matchCustomers.size() > 1)
			{
				LOG.warn("More than two customers were found with id=[" + customerId + "]");
			}
			return matchCustomers.iterator().next();
		}
	}

	@Override
	public CartModel getLatestModifiedCart(final UserModel customer)
	{
		return getCommerceCartService().getCartForGuidAndSiteAndUser(null, getBaseSiteService().getCurrentBaseSite(), customer);
	}

	@Override
	public OrderModel gerOrderByCode(final String orderCode, final UserModel customer)
	{
		final StringBuffer buf = new StringBuffer();

		// select the chosen order using his code
		buf.append("SELECT DISTINCT {p:" + OrderModel.PK + "} ");
		buf.append("FROM {" + OrderModel._TYPECODE + " as p } ");
		buf.append("WHERE {p:" + OrderModel.CODE + "} = ?orderCode ");
		buf.append("OR {p:" + OrderModel.GUID + "} = ?orderCode ");

		final FlexibleSearchQuery query = new FlexibleSearchQuery(buf.toString());
		query.addQueryParameter("orderCode", orderCode);
		final List<OrderModel> matchedOrder = getFlexibleSearchService().<OrderModel> search(query).getResult();
		if (CollectionUtils.isEmpty(matchedOrder))
		{
			return null;
		}
		if (matchedOrder.size() > 1)
		{
			LOG.warn("More than two orders were found with code=[" + orderCode + "]"); // how??
		}
		final OrderModel order = matchedOrder.iterator().next();
		if (!isAbstractOrderMatchBaseSite(order)
				|| ((!getUserService().isAnonymousUser(customer)) && !order.getUser().getUid().equals(customer.getUid())))
		{
			return null;
		}
		return order;
	}

	@Override
	public CartModel getCartByCode(final String cartCode, final UserModel customer)
	{
		final CartModel cartModel = getCommerceCartService().getCartForCodeAndUser(cartCode, customer);
		if (cartModel != null)
		{
			return isAbstractOrderMatchBaseSite(cartModel) ? cartModel : null;
		}
		return getCommerceCartService().getCartForGuidAndSiteAndUser(cartCode, getBaseSiteService().getCurrentBaseSite(), customer);
	}

	@Override
	public PointOfServiceModel getAssistedServiceAgentStore()
	{
		return getAssistedServiceAgentStore(getAsmSession().getAgent());
	}

	@Override
	public PointOfServiceModel getAssistedServiceAgentStore(final UserModel agent)
	{
		if (agent != null && CollectionUtils.isNotEmpty(agent.getAllGroups()))
		{
			final List<StoreEmployeeGroupModel> storeEmployeeGroups = getUserService()
					.getAllUserGroupsForUser(agent, StoreEmployeeGroupModel.class).stream().filter(group -> group.getStore() != null)
					.collect(Collectors.toList());
			if (!storeEmployeeGroups.isEmpty())
			{
				return storeEmployeeGroups.get(0).getStore();
			}

		}
		return null;
	}

	protected FlexibleSearchService getFlexibleSearchService()
	{
		return flexibleSearchService;
	}

	@Required
	public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService)
	{
		this.flexibleSearchService = flexibleSearchService;
	}

	protected CommerceCartService getCommerceCartService()
	{
		return commerceCartService;
	}

	@Required
	public void setCommerceCartService(final CommerceCartService commerceCartService)
	{
		this.commerceCartService = commerceCartService;
	}

	@Override
	public boolean isAbstractOrderMatchBaseSite(final AbstractOrderModel abstractOrderModel)
	{
		return abstractOrderModel.getSite() != null
				&& getBaseSiteService().getCurrentBaseSite().getUid().equals(abstractOrderModel.getSite().getUid());
	}

	protected ModelService getModelService()
	{
		return modelService;
	}

	public CartService getCartService()
	{
		return cartService;
	}

	@Required
	public void setCartService(final CartService cartService)
	{
		this.cartService = cartService;
	}

	protected CustomerAccountService getCustomerAccountService()
	{
		return customerAccountService;
	}

	@Required
	public void setCustomerAccountService(final CustomerAccountService customerAccountService)
	{
		this.customerAccountService = customerAccountService;
	}

	@Required
	protected SessionService getSessionService()
	{
		return sessionService;
	}

	public void setSessionService(final SessionService sessionService)
	{
		this.sessionService = sessionService;
	}

	public CommonI18NService getCommonI18NService()
	{
		return commonI18NService;
	}

	@Required
	public void setCommonI18NService(final CommonI18NService commonI18NService)
	{
		this.commonI18NService = commonI18NService;
	}

	@Required
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	protected UserService getUserService()
	{
		return userService;
	}

	@Required
	public void setUserService(final UserService userService)
	{
		this.userService = userService;
	}

	protected BaseSiteService getBaseSiteService()
	{
		return baseSiteService;
	}

	@Required
	public void setBaseSiteService(final BaseSiteService baseSiteService)
	{
		this.baseSiteService = baseSiteService;
	}

	protected PagedGenericDao<CartModel> getCartModelDao()
	{
		return cartModelDao;
	}

	@Required
	public void setCartModelDao(final PagedGenericDao<CartModel> cartModelDao)
	{
		this.cartModelDao = cartModelDao;
	}

}
