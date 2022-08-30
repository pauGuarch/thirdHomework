package com.ironhack.crm.dao.manager.implementation;

import com.ironhack.crm.domain.models.Contact;
import com.ironhack.crm.domain.models.Lead;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LeadManagerImplTest {
    private Lead lead1;
    private Lead lead2;

    private LeadManagerImpl leadManager;

    @BeforeEach
    void setUp() {
        leadManager = LeadManagerImpl.getInstance();
        lead1 = new Lead("Julian", "Ironhack", "juli@gjasof.com", "125252152");
        lead2 = new Lead("Ramona", "Ironhack", "Ramona@gjasof.com", "125125252152");
    }

    @Test
    void testCreateNewLeadAndCheckLeads(){
        leadManager.createNewLead(lead1);
        Lead testLead = leadManager.checkLeads().stream()
                .filter(lead -> lead.getId().equals(lead1.getId())).findFirst().get();
        assertEquals("Julian", testLead.getName());
        leadManager.removeLead(lead1.getId());
    }

    @Test
    void testLookUpLead(){
        leadManager.createNewLead(lead2);
        Lead lookedLead = leadManager.lookUpLead(lead2.getId());
        assertEquals("125125252152", lookedLead.getPhoneNumber());
        leadManager.removeLead(lead2.getId());
    }

}