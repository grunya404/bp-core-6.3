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
package de.hybris.platform.ycommercewebservices.user.data;

import de.hybris.platform.commercefacades.user.data.AddressData;
import java.util.List;

public  class AddressDataList  implements java.io.Serializable 
{


	/** <i>Generated property</i> for <code>AddressDataList.addresses</code> property defined at extension <code>ycommercewebservices</code>. */
		
	private List<AddressData> addresses;
	
	public AddressDataList()
	{
		// default constructor
	}
	
		
	
	public void setAddresses(final List<AddressData> addresses)
	{
		this.addresses = addresses;
	}

		
	
	public List<AddressData> getAddresses() 
	{
		return addresses;
	}
	


}