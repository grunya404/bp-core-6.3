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

package de.hybris.platform.financialacceleratorstorefront.controllers.pages;

import de.hybris.platform.commercefacades.order.CartFacade;
import de.hybris.platform.commercefacades.order.data.CartData;
import de.hybris.platform.financialacceleratorstorefront.controllers.imported.AcceleratorCategoryPageController;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ModelAttribute;


/**
 * Category Page Controller
 */
public class CategoryPageController extends AcceleratorCategoryPageController
{
	protected static final Logger LOG = Logger.getLogger(CategoryPageController.class);

	@Resource(name = "cartFacade")
	private CartFacade cartFacade;

	@ModelAttribute("cartData")
	public CartData getCartData()
	{
		return cartFacade.getSessionCart();
	}

}
