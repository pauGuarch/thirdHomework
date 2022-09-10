package com.ironhack.crm.dao.repositories;

import com.ironhack.crm.domain.enums.OpportunityStatus;
import com.ironhack.crm.domain.models.Opportunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OpportunityRepository extends JpaRepository<Opportunity, Integer> {
    Opportunity findByUuid(UUID opportunityId);

    Long countBySalesRepIdAndStatus(Integer salesRepId, OpportunityStatus status);

    Long countByProductName(String productName);

    Long countByStatusAndProductName(OpportunityStatus status, String productName);






}