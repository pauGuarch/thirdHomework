package com.ironhack.crm.dao.manager;
import com.ironhack.crm.domain.models.Opportunity;
import java.util.List;
import java.util.UUID;

public interface OpportunityManager {
    void createNewOpportunity(Opportunity lead);
    List<Opportunity> checkOpportunities();
    Opportunity lookUpOpportunity(UUID opportunityId);
    List<Opportunity> removeOpportunity(UUID id);
}
