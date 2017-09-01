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

package de.hybris.platform.financialfacades.strategies;


public interface PolicyPdfUrlGeneratorStrategy
{
	/**
	 * Creates a new url which is based upon the supplied policy specific Id. This Id is passed by the calling code and
	 * could be either a policy id, or the originating order id.
	 *
	 * @param policyId
	 *           the ID of the newly generated policy
	 * @return a string containing the new url
	 */
	public String generatePdfUrlForPolicy(String policyId);
}
