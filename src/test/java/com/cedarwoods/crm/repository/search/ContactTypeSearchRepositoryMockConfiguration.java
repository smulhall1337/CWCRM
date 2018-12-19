package com.cedarwoods.crm.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of ContactTypeSearchRepository to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class ContactTypeSearchRepositoryMockConfiguration {

    @MockBean
    private ContactTypeSearchRepository mockContactTypeSearchRepository;

}
