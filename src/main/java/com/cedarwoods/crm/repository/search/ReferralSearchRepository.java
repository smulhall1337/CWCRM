package com.cedarwoods.crm.repository.search;

import com.cedarwoods.crm.domain.Referral;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Referral entity.
 */
public interface ReferralSearchRepository extends ElasticsearchRepository<Referral, Long> {
}
