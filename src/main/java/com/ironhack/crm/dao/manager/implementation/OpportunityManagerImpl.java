package com.ironhack.crm.dao.manager.implementation;
import com.ironhack.crm.dao.manager.OpportunityManager;
import com.ironhack.crm.domain.models.Account;
import com.ironhack.crm.domain.models.Opportunity;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.ironhack.crm.utils.Utils.*;

public class OpportunityManagerImpl implements OpportunityManager {
    private static OpportunityManagerImpl opportunityManager;
    private List<Opportunity> opportunities;

    private OpportunityManagerImpl() {
        this.opportunities = checkOpportunities();
        if (opportunities == null){
            opportunities = new ArrayList<>();
        }
    }

    public static OpportunityManagerImpl getInstance() {
        if (opportunityManager == null) {
            opportunityManager = new OpportunityManagerImpl();
        }
        return opportunityManager;
    }

    @Override
    public void createNewOpportunity(Opportunity opportunity) {
        opportunities.add(opportunity);
        try {
            writeOpportunityJSON(opportunities);
        } catch (IOException e) {
            e.printStackTrace();
        }
        checkOpportunities();
    }

    @Override
    public List<Opportunity> checkOpportunities() {
        return this.opportunities = readOpportunities();
    }

    @Override
    public Opportunity lookUpOpportunity(UUID opportunityId) {
        Opportunity opportunity = opportunities.stream()
                .filter(o -> o.getId().equals(opportunityId)).findFirst().get();
        return opportunity;
    }

    @Override
    public List<Opportunity> removeOpportunity(UUID id) {
        try {
            Opportunity opportunityDel = lookUpOpportunity(id);
            opportunities.remove(opportunityDel);
            writeOpportunityJSON(opportunities);
        } catch (IOException e) {
            e.printStackTrace();
        }
        checkOpportunities();
        return opportunities;
    }

    public List<Opportunity> getOpportunities() {
        return opportunities;
    }

    public void setOpportunities(List<Opportunity> opportunities) {
        this.opportunities = opportunities;
    }
}
