package com.cedarwoods.crm.repository.search;

import com.cedarwoods.crm.domain.ContactHistory;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the ContactHistory entity.
 */
public interface ContactHistorySearchRepository extends ElasticsearchRepository<ContactHistory, Long> {
}
