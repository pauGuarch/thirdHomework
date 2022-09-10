package com.ironhack.crm.dao.repositories;

import com.ironhack.crm.domain.enums.OpportunityStatus;
import com.ironhack.crm.domain.models.Opportunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OpportunityRepository extends JpaRepository<Opportunity, Integer> {
    Opportunity findByUuid(UUID opportunityId);

    Long countBySalesRepIdAndStatus(Integer salesRepId, OpportunityStatus status);

    @Query("SELECT AVG(quantity) FROM Opportunity")
    Long meanQuantity();

    @Query("SELECT MAX(quantity) FROM Opportunity")
    Long getMaxQuantity();

    @Query("SELECT MIN(quantity) FROM Opportunity")
    Long getMinQuantity();
}

    List<Opportunity> findOpportunityBySalesRepId(Integer id);
}

