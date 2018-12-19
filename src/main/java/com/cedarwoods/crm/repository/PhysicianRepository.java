package com.cedarwoods.crm.repository;

import com.cedarwoods.crm.domain.Physician;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Physician entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PhysicianRepository extends JpaRepository<Physician, Long> {

}
