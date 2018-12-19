package com.cedarwoods.crm.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of EnrollmentAgencySearchRepository to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class EnrollmentAgencySearchRepositoryMockConfiguration {

    @MockBean
    private EnrollmentAgencySearchRepository mockEnrollmentAgencySearchRepository;

}
