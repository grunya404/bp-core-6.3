/*
 * ----------------------------------------------------------------
 * --- WARNING: THIS FILE IS GENERATED AND WILL BE OVERWRITTEN!
 * --- Generated at 25 Aug 2017 4:31:25 PM
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
package de.hybris.platform.ycommercewebservices.queues.data;

import de.hybris.platform.ycommercewebservices.queues.data.OrderStatusUpdateElementData;
import java.util.List;

public  class OrderStatusUpdateElementDataList  implements java.io.Serializable 
{


	/** <i>Generated property</i> for <code>OrderStatusUpdateElementDataList.orderStatusUpdateElements</code> property defined at extension <code>ycommercewebservices</code>. */
		
	private List<OrderStatusUpdateElementData> orderStatusUpdateElements;
	
	public OrderStatusUpdateElementDataList()
	{
		// default constructor
	}
	
		
	
	public void setOrderStatusUpdateElements(final List<OrderStatusUpdateElementData> orderStatusUpdateElements)
	{
		this.orderStatusUpdateElements = orderStatusUpdateElements;
	}

		
	
	public List<OrderStatusUpdateElementData> getOrderStatusUpdateElements() 
	{
		return orderStatusUpdateElements;
	}
	


}