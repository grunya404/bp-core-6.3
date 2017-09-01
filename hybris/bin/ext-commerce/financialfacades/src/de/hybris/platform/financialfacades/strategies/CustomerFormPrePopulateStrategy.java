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

import java.io.Serializable;


/**
 * The class of CustomerFormPrePopulateStrategy.
 */
public interface CustomerFormPrePopulateStrategy
{
	/**
	 * Store the customer filled form data. e.g. store to session.
	 */
	void storeCustomerFormData();

	/**
	 * Get the customer filled form. e.g. from session.
	 *
	 * @return customer form session data.
	 */
	<T extends Serializable> T getCustomerFormData();

	/**
	 * check if the customer store the filled form. e.g. check session.
	 *
	 * @return true if has stored.
	 */
	boolean hasCustomerFormDataStored();

	/**
	 * Remove the customer filled form data. e.g. from session.
	 */
	void removeStoredCustomerFormData();
}
