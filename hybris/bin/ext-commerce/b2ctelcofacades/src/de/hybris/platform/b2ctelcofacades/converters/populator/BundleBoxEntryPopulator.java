/*
* [y] hybris Platform
*
* Copyright (c) 2017 SAP SE or an SAP affiliate company.  All rights reserved.
*
* This software is the confidential and proprietary information of SAP
* ("Confidential Information"). You shall not disclose such Confidential
* Information and shall use it only in accordance with the terms of the
* license agreement you entered into with SAP.
*
*/
package de.hybris.platform.b2ctelcofacades.converters.populator;

import de.hybris.platform.b2ctelcofacades.data.BundleBoxEntryData;
import de.hybris.platform.commercefacades.converter.ConfigurablePopulator;
import de.hybris.platform.commercefacades.product.ProductOption;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Required;


/**
 * It converts the extra product {@link ProductModel} for Guided selling to {@link BundleBoxEntryData}.
 *
 * @param <SOURCE> source class
 * @param <TARGET> target class
 */
public class BundleBoxEntryPopulator<SOURCE extends ProductModel, TARGET extends BundleBoxEntryData> implements
		Populator<SOURCE, TARGET>
{
	private Converter<ProductModel, ProductData> productConverter;
	private ConfigurablePopulator<ProductModel, ProductData, ProductOption> productConfiguredPopulator;

	@Override
	public void populate(final ProductModel source, final BundleBoxEntryData target) throws ConversionException
	{
		final List options = Arrays.asList(ProductOption.BASIC, ProductOption.PRICE, ProductOption.SUMMARY,
				ProductOption.DESCRIPTION, ProductOption.STOCK);

		final ProductData productData = getProductConverter().convert(source);
		getProductConfiguredPopulator().populate(source, productData, options);

		target.setProduct(productData);

	}


	protected Converter<ProductModel, ProductData> getProductConverter()
	{
		return productConverter;
	}

	@Required
	public void setProductConverter(final Converter<ProductModel, ProductData> productConverter)
	{
		this.productConverter = productConverter;
	}

	protected ConfigurablePopulator<ProductModel, ProductData, ProductOption> getProductConfiguredPopulator()
	{
		return productConfiguredPopulator;
	}

	@Required
	public void setProductConfiguredPopulator(
			final ConfigurablePopulator<ProductModel, ProductData, ProductOption> productConfiguredPopulator)
	{
		this.productConfiguredPopulator = productConfiguredPopulator;
	}

}