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
package de.hybris.platform.subscriptionfacades.product.converters.populator;

import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;
import de.hybris.platform.subscriptionfacades.data.BillingTimeData;
import de.hybris.platform.subscriptionfacades.data.OneTimeChargeEntryData;
import de.hybris.platform.subscriptionservices.model.BillingTimeModel;
import de.hybris.platform.subscriptionservices.model.OneTimeChargeEntryModel;

import org.springframework.beans.factory.annotation.Required;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

/**
 * Populate DTO {@link OneTimeChargeEntryData} with data from {@link OneTimeChargeEntryModel}.
 *
 * @param <SOURCE> source class
 * @param <TARGET> target class
 */
public class OneTimeChargeEntryPopulator<SOURCE extends OneTimeChargeEntryModel, TARGET extends OneTimeChargeEntryData> extends
		AbstractChargeEntryPopulator<SOURCE, TARGET>
{

	private Converter<BillingTimeModel, BillingTimeData> billingTimeConverter;

	@Override
	public void populate(final SOURCE source, final TARGET target) throws ConversionException
	{
		validateParameterNotNullStandardMessage("source", source);
		validateParameterNotNullStandardMessage("target", target);

		target.setName(source.getName());

		if (source.getBillingEvent() != null)
		{
			target.setBillingTime(getBillingTimeConverter().convert(source.getBillingEvent()));
		}

		super.populate(source, target);
	}

	protected Converter<BillingTimeModel, BillingTimeData> getBillingTimeConverter()
	{
		return billingTimeConverter;
	}

	@Required
	public void setBillingTimeConverter(final Converter<BillingTimeModel, BillingTimeData> billingTimeConverter)
	{
		this.billingTimeConverter = billingTimeConverter;
	}
}
