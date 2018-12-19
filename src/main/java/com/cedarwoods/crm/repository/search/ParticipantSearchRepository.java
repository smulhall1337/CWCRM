package com.cedarwoods.crm.repository.search;

import com.cedarwoods.crm.domain.Participant;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Participant entity.
 */
public interface ParticipantSearchRepository extends ElasticsearchRepository<Participant, Long> {
}
