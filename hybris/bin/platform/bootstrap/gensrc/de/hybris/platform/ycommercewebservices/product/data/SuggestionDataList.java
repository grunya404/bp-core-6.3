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
package de.hybris.platform.ycommercewebservices.product.data;

import de.hybris.platform.commercefacades.product.data.SuggestionData;
import java.util.List;

public  class SuggestionDataList  implements java.io.Serializable 
{


	/** <i>Generated property</i> for <code>SuggestionDataList.suggestions</code> property defined at extension <code>ycommercewebservices</code>. */
		
	private List<SuggestionData> suggestions;
	
	public SuggestionDataList()
	{
		// default constructor
	}
	
		
	
	public void setSuggestions(final List<SuggestionData> suggestions)
	{
		this.suggestions = suggestions;
	}

		
	
	public List<SuggestionData> getSuggestions() 
	{
		return suggestions;
	}
	


}