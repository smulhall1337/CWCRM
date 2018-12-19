package com.cedarwoods.crm.repository.search;

import com.cedarwoods.crm.domain.ContactType;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the ContactType entity.
 */
public interface ContactTypeSearchRepository extends ElasticsearchRepository<ContactType, Long> {
}
