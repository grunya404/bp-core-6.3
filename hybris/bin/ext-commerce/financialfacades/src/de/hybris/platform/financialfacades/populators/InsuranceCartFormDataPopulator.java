/*
 * [y] hybris Platform
 *
 * Copyright (c) 2017 SAP SE or an SAP affiliate company.  All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */

package de.hybris.platform.financialfacades.populators;

import de.hybris.platform.commercefacades.order.data.CartData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.financialfacades.strategies.CustomerFormPrePopulateStrategy;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;


/**
 * The class of InsuranceCartFormDataPopulator.
 */
public class InsuranceCartFormDataPopulator implements Populator<CartModel, CartData>
{

	private CustomerFormPrePopulateStrategy customerFormPrePopulateStrategy;

	@Override
	public void populate(final CartModel cartModel, final CartData cartData)
	{
		Assert.notNull(cartData, "cartData cannot be null");

		cartData.setHasSessionFormData(getCustomerFormPrePopulateStrategy().hasCustomerFormDataStored());
	}

	protected CustomerFormPrePopulateStrategy getCustomerFormPrePopulateStrategy()
	{
		return customerFormPrePopulateStrategy;
	}

	@Required
	public void setCustomerFormPrePopulateStrategy(final CustomerFormPrePopulateStrategy customerFormPrePopulateStrategy)
	{
		this.customerFormPrePopulateStrategy = customerFormPrePopulateStrategy;
	}
}
