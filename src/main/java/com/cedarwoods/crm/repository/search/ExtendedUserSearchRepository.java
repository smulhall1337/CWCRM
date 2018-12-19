package com.cedarwoods.crm.repository.search;

import com.cedarwoods.crm.domain.ExtendedUser;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the ExtendedUser entity.
 */
public interface ExtendedUserSearchRepository extends ElasticsearchRepository<ExtendedUser, Long> {
}
