package com.cedarwoods.crm.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of ReferralTypeSearchRepository to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class ReferralTypeSearchRepositoryMockConfiguration {

    @MockBean
    private ReferralTypeSearchRepository mockReferralTypeSearchRepository;

}
