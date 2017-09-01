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

package de.hybris.platform.financialfacades.facades;

import de.hybris.platform.commercefacades.insurance.data.PolicyRequestData;
import de.hybris.platform.commercefacades.insurance.data.PolicyResponseData;


/**
 * The class of PolicyService.
 */
public interface PolicyServiceFacade
{

	/**
	 * Request to create a policy to a external system, and return to hybris the information about the policy creation.
	 *
	 * @param requestData
	 *           the policy request data
	 * @return policy response data
	 */
	PolicyResponseData requestPolicyCreation(final PolicyRequestData requestData);
}
