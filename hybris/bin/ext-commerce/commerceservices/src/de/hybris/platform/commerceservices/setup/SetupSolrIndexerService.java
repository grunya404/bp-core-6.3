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
package de.hybris.platform.commerceservices.setup;

/**
 * Service to set up solr indexes.
 */
public interface SetupSolrIndexerService
{
	/**
	 * Create the update and rebuild solr index cron jobs for the specified solrFacetSearchConfigName.
	 * 
	 * @param solrFacetSearchConfigName
	 *           the solrFacetSearchConfigName
	 */
	void createSolrIndexerCronJobs(String solrFacetSearchConfigName);

	/**
	 * Run a solr indexer cron job.
	 * 
	 * @param solrFacetSearchConfigName
	 *           the solrFacetSearchConfigName
	 * @param fullReIndex
	 *           true to rebuild the index, false to update it
	 */
	void executeSolrIndexerCronJob(String solrFacetSearchConfigName, boolean fullReIndex);

	/**
	 * Activate the solr index cron jobs for the specified solrFacetSearchConfigName
	 * 
	 * @param solrFacetSearchConfigName
	 *           the solrFacetSearchConfigName
	 */
	void activateSolrIndexerCronJobs(String solrFacetSearchConfigName);
}
