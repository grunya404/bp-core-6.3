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
package de.hybris.platform.commercefacades.xstream.alias;

public class ImplicitCollection
{
	private String fieldName;
	private Class ownerType;
	private String itemFieldName;
	private Class itemType;

	public String getFieldName()
	{
		return fieldName;
	}

	public void setFieldName(final String fieldName)
	{
		this.fieldName = fieldName;
	}

	public Class getOwnerType()
	{
		return ownerType;
	}

	public void setOwnerType(final Class ownerType)
	{
		this.ownerType = ownerType;
	}

	public String getItemFieldName()
	{
		return itemFieldName;
	}

	public void setItemFieldName(final String itemFieldName)
	{
		this.itemFieldName = itemFieldName;
	}

	public Class getItemType()
	{
		return itemType;
	}

	public void setItemType(final Class itemType)
	{
		this.itemType = itemType;
	}



}
