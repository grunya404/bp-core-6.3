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

package de.hybris.platform.financialfacades.process.xslfo.context;

import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commercefacades.insurance.data.InsurancePolicyData;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;


/**
 * Travel Policy Context for populating Property Homeowners templates
 */
public class PropertyHomeownerPolicyContext extends AbstractPolicyContext
{
	@SuppressWarnings("unused")
	private static final Logger LOG = Logger.getLogger(PropertyHomeownerPolicyContext.class);

	@Override
	public void init(final BaseSiteModel baseSite, final InsurancePolicyData policyData)
	{
		super.init(baseSite, policyData);

		put("addressLine1", StringEscapeUtils.escapeXml(policyData.getAddressLine1()));
		put("propertyCoverRequired", StringEscapeUtils.escapeXml(policyData.getPropertyCoverRequired()));
		put("propertyType", StringEscapeUtils.escapeXml(policyData.getPropertyType()));
		put("propertyValue", StringEscapeUtils.escapeXml(policyData.getPropertyValue()));
	}
}
