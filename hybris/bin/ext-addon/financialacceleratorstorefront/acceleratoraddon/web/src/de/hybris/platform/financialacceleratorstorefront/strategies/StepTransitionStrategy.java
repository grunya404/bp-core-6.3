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

package de.hybris.platform.financialacceleratorstorefront.strategies;

import de.hybris.platform.financialacceleratorstorefront.checkout.step.InsuranceCheckoutStep;


public interface StepTransitionStrategy
{
	/**
	 * Set visited flag on the checkout step if the step is active by indicated current url.
	 */
	void setVisited(InsuranceCheckoutStep checkoutStep, String currentURL);

	void setVisited(InsuranceCheckoutStep checkoutStep);
}
