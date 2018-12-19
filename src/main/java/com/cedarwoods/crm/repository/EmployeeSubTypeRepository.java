package com.cedarwoods.crm.repository;

import com.cedarwoods.crm.domain.EmployeeSubType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EmployeeSubType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EmployeeSubTypeRepository extends JpaRepository<EmployeeSubType, Long> {

}
