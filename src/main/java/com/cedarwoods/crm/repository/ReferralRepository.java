package com.cedarwoods.crm.repository;

import com.cedarwoods.crm.domain.Referral;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Referral entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReferralRepository extends JpaRepository<Referral, Long> {

}
