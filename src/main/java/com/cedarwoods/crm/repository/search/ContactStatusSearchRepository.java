package com.cedarwoods.crm.repository.search;

import com.cedarwoods.crm.domain.ContactStatus;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the ContactStatus entity.
 */
public interface ContactStatusSearchRepository extends ElasticsearchRepository<ContactStatus, Long> {
}
