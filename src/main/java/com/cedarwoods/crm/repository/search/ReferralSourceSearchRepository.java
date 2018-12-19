package com.cedarwoods.crm.repository.search;

import com.cedarwoods.crm.domain.ReferralSource;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the ReferralSource entity.
 */
public interface ReferralSourceSearchRepository extends ElasticsearchRepository<ReferralSource, Long> {
}
