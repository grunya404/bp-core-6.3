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
package de.hybris.platform.commercewebservicescommons.dto.product;

import de.hybris.platform.commercewebservicescommons.dto.product.ProductReferenceWsDTO;
import java.util.List;

public  class ProductReferenceListWsDTO  implements java.io.Serializable 
{


	/** <i>Generated property</i> for <code>ProductReferenceListWsDTO.references</code> property defined at extension <code>commercewebservicescommons</code>. */
		
	private List<ProductReferenceWsDTO> references;
	
	public ProductReferenceListWsDTO()
	{
		// default constructor
	}
	
		
	
	public void setReferences(final List<ProductReferenceWsDTO> references)
	{
		this.references = references;
	}

		
	
	public List<ProductReferenceWsDTO> getReferences() 
	{
		return references;
	}
	


}