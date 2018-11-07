package com.cedarwoods.crm.repository.search;

import com.cedarwoods.crm.domain.Physician;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Physician entity.
 */
public interface PhysicianSearchRepository extends ElasticsearchRepository<Physician, Long> {
}
