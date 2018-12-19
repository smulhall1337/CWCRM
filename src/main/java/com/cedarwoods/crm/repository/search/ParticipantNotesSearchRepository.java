package com.cedarwoods.crm.repository.search;

import com.cedarwoods.crm.domain.ParticipantNotes;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the ParticipantNotes entity.
 */
public interface ParticipantNotesSearchRepository extends ElasticsearchRepository<ParticipantNotes, Long> {
}
