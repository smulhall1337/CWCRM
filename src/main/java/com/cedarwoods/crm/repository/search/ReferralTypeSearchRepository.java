package com.cedarwoods.crm.repository.search;

import com.cedarwoods.crm.domain.ReferralType;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the ReferralType entity.
 */
public interface ReferralTypeSearchRepository extends ElasticsearchRepository<ReferralType, Long> {
}
