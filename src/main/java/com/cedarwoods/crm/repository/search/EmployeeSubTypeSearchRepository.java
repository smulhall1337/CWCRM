package com.cedarwoods.crm.repository.search;

import com.cedarwoods.crm.domain.EmployeeSubType;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the EmployeeSubType entity.
 */
public interface EmployeeSubTypeSearchRepository extends ElasticsearchRepository<EmployeeSubType, Long> {
}
