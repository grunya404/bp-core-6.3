/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at 25 Aug 2017 4:31:26 PM
 * ----------------------------------------------------------------
 *
 * [y] hybris Platform
 * 
 * Copyright (c) 2000-2016 SAP SE
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information of SAP 
 * Hybris ("Confidential Information"). You shall not disclose such 
 * Confidential Information and shall use it only in accordance with the 
 * terms of the license agreement you entered into with SAP Hybris.
 */
package de.hybris.platform.ycommercewebservices.storesession.data;

import de.hybris.platform.commercefacades.storesession.data.CurrencyData;
import java.util.Collection;

public  class CurrencyDataList  implements java.io.Serializable 
{


	/** <i>Generated property</i> for <code>CurrencyDataList.currencies</code> property defined at extension <code>ycommercewebservices</code>. */
		
	private Collection<CurrencyData> currencies;
	
	public CurrencyDataList()
	{
		// default constructor
	}
	
		
	
	public void setCurrencies(final Collection<CurrencyData> currencies)
	{
		this.currencies = currencies;
	}

		
	
	public Collection<CurrencyData> getCurrencies() 
	{
		return currencies;
	}
	


}