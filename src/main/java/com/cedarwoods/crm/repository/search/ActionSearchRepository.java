package com.cedarwoods.crm.repository.search;

import com.cedarwoods.crm.domain.Action;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Action entity.
 */
public interface ActionSearchRepository extends ElasticsearchRepository<Action, Long> {
}
