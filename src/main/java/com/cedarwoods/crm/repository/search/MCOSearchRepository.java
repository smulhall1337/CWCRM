package com.cedarwoods.crm.repository.search;

import com.cedarwoods.crm.domain.MCO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the MCO entity.
 */
public interface MCOSearchRepository extends ElasticsearchRepository<MCO, Long> {
}
