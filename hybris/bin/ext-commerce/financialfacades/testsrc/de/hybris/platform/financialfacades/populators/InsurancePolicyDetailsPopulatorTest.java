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

import de.hybris.platform.commercefacades.insurance.data.InsurancePolicyData;
import de.hybris.platform.commercefacades.order.data.OrderEntryData;
import de.hybris.platform.commercefacades.product.data.CategoryData;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.xyformsfacades.data.YFormDataData;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;


/**
 * The class of InsuranceQuoteReviewDetailsPopulatorTest.
 */
public class InsurancePolicyDetailsPopulatorTest
{

	private InsuranceQuoteReviewDetailsPopulator insuranceQuoteReviewDetailsPopulator;

	private Populator<YFormDataData, InsurancePolicyData> mockPopulatorA;

	private Populator<YFormDataData, InsurancePolicyData> mockPopulatorB;

	private List<Populator<YFormDataData, InsurancePolicyData>> populatorListA;
	private List<Populator<YFormDataData, InsurancePolicyData>> populatorListB;

	private Map<String, List<Populator<YFormDataData, InsurancePolicyData>>> populatorMap;

	private final String keyA = "KEY_A";
	private final String keyB = "KEY_B";

	@Before
	public void setup()
	{
		insuranceQuoteReviewDetailsPopulator = new InsuranceQuoteReviewDetailsPopulator();
		mockPopulatorA = Mockito.mock(Populator.class);
		mockPopulatorB = Mockito.mock(Populator.class);
		populatorListA = Lists.newArrayList();
		populatorListB = Lists.newArrayList();
		populatorListA.add(mockPopulatorA);
		populatorListB.add(mockPopulatorB);

		populatorMap = Maps.newHashMap();
		populatorMap.put(keyA, populatorListA);
		populatorMap.put(keyB, populatorListB);
		insuranceQuoteReviewDetailsPopulator.setDetailsPopulatorsMap(populatorMap);
	}

	@Test
	public void shouldRunPopulatorA()
	{
		final OrderEntryData orderEntryData = new OrderEntryData();
		final ProductData product = new ProductData();
		final CategoryData defaultCategoryData = new CategoryData();
		defaultCategoryData.setCode(keyA);
		product.setDefaultCategory(defaultCategoryData);
		orderEntryData.setProduct(product);
		orderEntryData.setFormDataData(Arrays.asList(new YFormDataData()));

		insuranceQuoteReviewDetailsPopulator.populate(orderEntryData, new InsurancePolicyData());

		Mockito.verify(mockPopulatorA).populate(Mockito.any(YFormDataData.class), Mockito.any(InsurancePolicyData.class));
		Mockito.verify(mockPopulatorB, Mockito.never()).populate(Mockito.any(YFormDataData.class),
				Mockito.any(InsurancePolicyData.class));
	}

	@Test
	public void shouldNotRunPopulators()
	{
		final OrderEntryData orderEntryData = new OrderEntryData();
		final ProductData product = new ProductData();
		final CategoryData defaultCategoryData = new CategoryData();
		defaultCategoryData.setCode("any other key");
		product.setDefaultCategory(defaultCategoryData);
		orderEntryData.setProduct(product);
		orderEntryData.setFormDataData(Arrays.asList(new YFormDataData()));

		insuranceQuoteReviewDetailsPopulator.populate(orderEntryData, new InsurancePolicyData());

		Mockito.verify(mockPopulatorA, Mockito.never()).populate(Mockito.any(YFormDataData.class),
				Mockito.any(InsurancePolicyData.class));
		Mockito.verify(mockPopulatorB, Mockito.never()).populate(Mockito.any(YFormDataData.class),
				Mockito.any(InsurancePolicyData.class));

	}

	@Test
	public void shouldNotRunPopulatorIfNoYFormData()
	{
		final OrderEntryData orderEntryData = new OrderEntryData();
		final ProductData product = new ProductData();
		final CategoryData defaultCategoryData = new CategoryData();
		defaultCategoryData.setCode(keyA);
		product.setDefaultCategory(defaultCategoryData);
		orderEntryData.setProduct(product);
		orderEntryData.setFormDataData(null);

		insuranceQuoteReviewDetailsPopulator.populate(orderEntryData, new InsurancePolicyData());

		Mockito.verify(mockPopulatorA, Mockito.never()).populate(Mockito.any(YFormDataData.class),
				Mockito.any(InsurancePolicyData.class));
		Mockito.verify(mockPopulatorB, Mockito.never()).populate(Mockito.any(YFormDataData.class),
				Mockito.any(InsurancePolicyData.class));
	}
}
