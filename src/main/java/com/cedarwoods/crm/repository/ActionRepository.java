package com.cedarwoods.crm.repository;

import com.cedarwoods.crm.domain.Action;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Action entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ActionRepository extends JpaRepository<Action, Long> {

    @Query("select action from Action action where action.user.login = ?#{principal.username}")
    List<Action> findByUserIsCurrentUser();

}
