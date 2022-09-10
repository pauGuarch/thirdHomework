package com.ironhack.crm.dao.manager;
import com.ironhack.crm.domain.enums.OpportunityStatus;
import com.ironhack.crm.domain.models.Opportunity;
import com.ironhack.crm.domain.models.SalesRep;

import java.util.List;
import java.util.UUID;

public interface OpportunityManager {
    void createNewOpportunity(Opportunity lead);
    List<Opportunity> checkOpportunities();
    Opportunity lookUpOpportunity(Integer id);

    void updateOpportunity(Integer id, int statusId);

    Long countBySalesRepIdAndStatus(Integer salesRepId, int status);


    Long getMeanQuantity();

    Long getMaxQuantity();

    List<Opportunity> getOpportunitiesBySalesRep(Integer id);

    //List<Opportunity> removeOpportunity(Integer id);


}
