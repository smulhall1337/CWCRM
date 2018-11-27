package com.cedarwoods.crm.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import io.github.jhipster.config.jcache.BeanClassLoaderAwareJCacheRegionFactory;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        BeanClassLoaderAwareJCacheRegionFactory.setBeanClassLoader(this.getClass().getClassLoader());
        JHipsterProperties.Cache.Ehcache ehcache =
            jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            cm.createCache(com.cedarwoods.crm.repository.UserRepository.USERS_BY_LOGIN_CACHE, jcacheConfiguration);
            cm.createCache(com.cedarwoods.crm.repository.UserRepository.USERS_BY_EMAIL_CACHE, jcacheConfiguration);
            cm.createCache(com.cedarwoods.crm.domain.User.class.getName(), jcacheConfiguration);
            cm.createCache(com.cedarwoods.crm.domain.Authority.class.getName(), jcacheConfiguration);
            cm.createCache(com.cedarwoods.crm.domain.User.class.getName() + ".authorities", jcacheConfiguration);
            cm.createCache(com.cedarwoods.crm.domain.Participant.class.getName(), jcacheConfiguration);
            cm.createCache(com.cedarwoods.crm.domain.Participant.class.getName() + ".referrals", jcacheConfiguration);
            cm.createCache(com.cedarwoods.crm.domain.Referral.class.getName(), jcacheConfiguration);
            cm.createCache(com.cedarwoods.crm.domain.Action.class.getName(), jcacheConfiguration);
            cm.createCache(com.cedarwoods.crm.domain.SupportCoordinator.class.getName(), jcacheConfiguration);
            cm.createCache(com.cedarwoods.crm.domain.Waiver.class.getName(), jcacheConfiguration);
            cm.createCache(com.cedarwoods.crm.domain.Department.class.getName(), jcacheConfiguration);
            cm.createCache(com.cedarwoods.crm.domain.EmployeeType.class.getName(), jcacheConfiguration);
            cm.createCache(com.cedarwoods.crm.domain.EmployeeSubType.class.getName(), jcacheConfiguration);
            cm.createCache(com.cedarwoods.crm.domain.ContactStatus.class.getName(), jcacheConfiguration);
            cm.createCache(com.cedarwoods.crm.domain.ContactSubStatus.class.getName(), jcacheConfiguration);
            cm.createCache(com.cedarwoods.crm.domain.Priority.class.getName(), jcacheConfiguration);
            cm.createCache(com.cedarwoods.crm.domain.MCO.class.getName(), jcacheConfiguration);
            cm.createCache(com.cedarwoods.crm.domain.Physician.class.getName(), jcacheConfiguration);
            cm.createCache(com.cedarwoods.crm.domain.EnrollmentAgency.class.getName(), jcacheConfiguration);
            cm.createCache(com.cedarwoods.crm.domain.Participant.class.getName() + ".participantNotes", jcacheConfiguration);
            cm.createCache(com.cedarwoods.crm.domain.ContactHistory.class.getName(), jcacheConfiguration);
            cm.createCache(com.cedarwoods.crm.domain.ParticipantNotes.class.getName(), jcacheConfiguration);
            cm.createCache(com.cedarwoods.crm.domain.ContactType.class.getName(), jcacheConfiguration);
            cm.createCache(com.cedarwoods.crm.domain.ExtendedUser.class.getName(), jcacheConfiguration);
            // jhipster-needle-ehcache-add-entry
        };
    }
}
