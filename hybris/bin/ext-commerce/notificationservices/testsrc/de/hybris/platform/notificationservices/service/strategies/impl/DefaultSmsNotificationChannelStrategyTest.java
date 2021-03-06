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
package de.hybris.platform.notificationservices.service.strategies.impl;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.config.ConfigurationService;

import org.apache.commons.configuration.Configuration;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import junit.framework.Assert;


@UnitTest
public class DefaultSmsNotificationChannelStrategyTest
{
	private DefaultSmsNotificationChannelStrategy smsNotificationChannelStrategy;
	private static final String TEST_MOBILE_NUMBER_KEY = "sms.channel.mobilePhone";
	private static final String TEST_MOBILE_NUMBER = "15992284444";

	@Mock
	private ConfigurationService configurationService;

	@Before
	public void setUp()
	{
		MockitoAnnotations.initMocks(this);

		smsNotificationChannelStrategy = new DefaultSmsNotificationChannelStrategy();
		smsNotificationChannelStrategy.setConfigurationService(configurationService);
	}

	@Test
	public void testGetMobilePhoneNumber()
	{
		CustomerModel customer = new CustomerModel();
		final Configuration configuration = mock(Configuration.class);

		given(configuration.getString(TEST_MOBILE_NUMBER_KEY)).willReturn(TEST_MOBILE_NUMBER);
		given(configurationService.getConfiguration()).willReturn(configuration);

		Assert.assertEquals(TEST_MOBILE_NUMBER, smsNotificationChannelStrategy.getMobilePhoneNumber(customer));
	}
}
