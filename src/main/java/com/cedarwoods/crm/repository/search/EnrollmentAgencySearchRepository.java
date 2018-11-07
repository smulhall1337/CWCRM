package com.cedarwoods.crm.repository.search;

import com.cedarwoods.crm.domain.EnrollmentAgency;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the EnrollmentAgency entity.
 */
public interface EnrollmentAgencySearchRepository extends ElasticsearchRepository<EnrollmentAgency, Long> {
}
