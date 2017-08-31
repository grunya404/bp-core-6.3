/*
 * [y] hybris Platform
 *
 * Copyright (c) 2017 SAP SE or an SAP affiliate company.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */

package com.hybris.datahub.core.facades;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import de.hybris.bootstrap.annotations.UnitTest;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;


@UnitTest
@SuppressWarnings("javadoc")
public class ImportErrorUnitTest
{
	@Test
	public void testSourceLineIsEmptyWhenSourceLineWasNotFound()
	{
		final ImportError err = ImportError.create(null, "an error happened");
		assertEquals("", err.getScriptLine());
	}

	@Test
	public void testItemTypeIsNullWhenSourceLineWasNotFound()
	{
		final ImportError err = ImportError.create(null, "something is wrong");
		Assert.assertNull(err.getItemType());
	}

	@Test
	public void testItemIdIsNullWhenSourceLineWasNotFound()
	{
		final ImportError err = ImportError.create(null, "can't find the script");
		Assert.assertNull(err.getCanonicalItemId());
	}

	@Test
	public void testNoErrorIsCreatedWhenMessageIsNull()
	{
		final ImportError err = ImportError.create(";1234;dress;", null);

		Assert.assertNull(err);
	}

	@Test
	public void testItemTypeIsPopulatedWhenSourceLineIsAnUpdateHeader()
	{
		final ImportError err = ImportError.create("UPDATE Product;;code;$catalogVersion",
				"unknown attribute '$catalogVersion' in header 'INSERT_UPDATE Product'");

		assertEquals("Product", err.getItemType());
	}

	@Test
	public void testItemTypeIsPopulatedWhenSourceLineIsAnImpexInsertUpdateHeader()
	{
		final ImportError err = ImportError.create("INSERT_UPDATE Product;;code;$catalogVersion",
				"unknown attribute '$catalogVersion' in header 'INSERT_UPDATE Product'");

		assertEquals("Product", err.getItemType());
	}

	@Test
	public void testItemTypeIsPopulatedWhenSourceLineIsAnImpexInsertHeader()
	{
		final ImportError err = ImportError.create("INSERT Product;;code;$catalogVersion",
				"unknown attribute '$catalogVersion' in header 'INSERT_UPDATE Product'");

		assertEquals("Product", err.getItemType());
	}

	@Test
	public void testItemTypeIsPopulatedWhenSourceLineIsAnImpexRemoveHeader()
	{
		final ImportError err = ImportError.create("REMOVE Category;;$catalogVersion",
				"unknown attributes [Category.$catalogVersion] - cannot resolve item reference");

		assertEquals("Category", err.getItemType());
	}

	@Test
	public void testItemTypeNotAvailableWhenSourceLineIsNeitherDataNorHeader()
	{
		final ImportError err = ImportError.create("CREATE Product;;code;$catalogVersion", "unknown command 'CREATE'");

		Assert.assertNull(err.getItemType());
	}

	@Test
	public void testItemIdNotAvailableWhenSourceLineIsAnImpexHeaderLine()
	{
		final ImportError err = ImportError.create("INSERT_UPDATE Product;;code;$catalogVersion",
				"unknown attribute '$catalogVersion' in header 'INSERT_UPDATE Product'");

		Assert.assertNull(err.getCanonicalItemId());
	}

	@Test
	public void testItemIdIsPopulatedWhenSourceLineIsAnImpexDataLine()
	{
		final ImportError err = ImportError.create(";123456789;;Material;3912;",
				"item reference a for attribute CatalogVersion.version does not provide enough values at position 1");

		assertEquals(new Long(123456789), err.getCanonicalItemId());
	}

	@Test
	public void testItemTypeIsNullWhenImpexLineIsADataLine()
	{
		final ImportError err = ImportError.create(";123;;Material;3912;",
				"item reference a for attribute CatalogVersion.version does not provide enough values at position 1");

		Assert.assertNull(err.getItemType());
	}

	@Test
	public void testCreatedErrorHasTheSpecifiedErrorMessage()
	{
		final ImportError err = ImportError.create(";;;Material;3912;", "This is an error");

		assertEquals("This is an error", err.getMessage());
	}

	@Test
	public void testCreatedErrorHasTheSpecifiedScriptLine()
	{
		final ImportError err = ImportError.create(";;;Material;3912;", "This is an error");

		assertEquals(";;;Material;3912;", err.getScriptLine());
	}
}
