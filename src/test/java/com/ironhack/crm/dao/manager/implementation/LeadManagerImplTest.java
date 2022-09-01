package com.ironhack.crm.dao.manager.implementation;

import com.ironhack.crm.domain.models.Lead;
import com.ironhack.crm.domain.models.SalesRep;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LeadManagerImplTest {
    private Lead lead1;
    private Lead lead2;

    private SalesRep salesRep;

    private LeadManagerImpl leadManager;

    @BeforeEach
    void setUp() {
        leadManager = LeadManagerImpl.getInstance();
        salesRep = new SalesRep();
        lead1 = new Lead("Julian", "Ironhack", "juli@gjasof.com", "125252152",salesRep);
        lead2 = new Lead("Ramona", "Ironhack", "Ramona@gjasof.com", "125125252152",salesRep);
    }

    @Test
    void testCreateNewLeadAndCheckLeads(){
        leadManager.createNewLead(lead1);
        Lead testLead = leadManager.checkLeads().stream()
                .filter(lead -> lead.getUuid().equals(lead1.getUuid())).findFirst().get();
        assertEquals("Julian", testLead.getName());
        leadManager.removeLead(lead1.getUuid());
    }

    @Test
    void testLookUpLead(){
        leadManager.createNewLead(lead2);
        Lead lookedLead = leadManager.lookUpLead(lead2.getUuid());
        assertEquals("125125252152", lookedLead.getPhoneNumber());
        leadManager.removeLead(lead2.getUuid());
    }

}