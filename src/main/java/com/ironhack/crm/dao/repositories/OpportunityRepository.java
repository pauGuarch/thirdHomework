package com.ironhack.crm.dao.repositories;

import com.ironhack.crm.domain.enums.OpportunityStatus;
import com.ironhack.crm.domain.models.Opportunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OpportunityRepository extends JpaRepository<Opportunity, Integer> {
    Opportunity findByUuid(UUID opportunityId);

    List<Opportunity> findAllBySalesRepIdAndStatus(Integer salesRepId, OpportunityStatus status);

    Long countAllBySalesRepAndStatus(Integer salesRepId, OpportunityStatus status);
}