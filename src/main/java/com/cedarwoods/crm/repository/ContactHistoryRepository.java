package com.cedarwoods.crm.repository;

import com.cedarwoods.crm.domain.ContactHistory;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the ContactHistory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ContactHistoryRepository extends JpaRepository<ContactHistory, Long> {

    @Query("select contact_history from ContactHistory contact_history where contact_history.user.login = ?#{principal.username}")
    List<ContactHistory> findByUserIsCurrentUser();

}
