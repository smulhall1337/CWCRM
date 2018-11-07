package com.cedarwoods.crm.repository.search;

import com.cedarwoods.crm.domain.Priority;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Priority entity.
 */
public interface PrioritySearchRepository extends ElasticsearchRepository<Priority, Long> {
}
