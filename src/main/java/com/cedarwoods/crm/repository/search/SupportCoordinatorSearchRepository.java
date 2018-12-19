package com.cedarwoods.crm.repository.search;

import com.cedarwoods.crm.domain.SupportCoordinator;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the SupportCoordinator entity.
 */
public interface SupportCoordinatorSearchRepository extends ElasticsearchRepository<SupportCoordinator, Long> {
}
