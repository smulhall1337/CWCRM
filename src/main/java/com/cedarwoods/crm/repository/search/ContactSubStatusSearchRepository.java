package com.cedarwoods.crm.repository.search;

import com.cedarwoods.crm.domain.ContactSubStatus;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the ContactSubStatus entity.
 */
public interface ContactSubStatusSearchRepository extends ElasticsearchRepository<ContactSubStatus, Long> {
}
