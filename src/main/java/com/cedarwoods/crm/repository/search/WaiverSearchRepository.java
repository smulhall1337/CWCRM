package com.cedarwoods.crm.repository.search;

import com.cedarwoods.crm.domain.Waiver;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Waiver entity.
 */
public interface WaiverSearchRepository extends ElasticsearchRepository<Waiver, Long> {
}
