package com.cedarwoods.crm.repository.search;

import com.cedarwoods.crm.domain.EmployeeType;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the EmployeeType entity.
 */
public interface EmployeeTypeSearchRepository extends ElasticsearchRepository<EmployeeType, Long> {
}
