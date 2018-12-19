package com.cedarwoods.crm.repository;

import com.cedarwoods.crm.domain.ReferralSource;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ReferralSource entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReferralSourceRepository extends JpaRepository<ReferralSource, Long> {

}
