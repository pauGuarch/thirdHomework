package com.ironhack.crm.dao.manager.implementation;

import com.ironhack.crm.dao.repositories.LeadRepository;
import com.ironhack.crm.domain.models.Lead;
import com.ironhack.crm.domain.models.SalesRep;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LeadManagerImplTest {
    @Autowired
    private LeadRepository leadRepository;

    private Lead lead1;
    private Lead lead2;

    private SalesRep salesRep;
    @Autowired
    private LeadManagerImpl leadManager;

    @BeforeEach
    void setUp() {
        salesRep = new SalesRep();
        lead1 = leadRepository.save (new Lead("Julian", "Ironhack", "juli@gjasof.com", "125252152",salesRep));
        lead2 = leadRepository.save (new Lead("Ramona", "Ironhack", "Ramona@gjasof.com", "125125252152",salesRep));
    }

    @AfterEach
    void tearDown(){
        leadRepository.deleteAll();
    }

    @Test
    void testCreateNewLeadAndCheckLeads(){
        leadManager.createNewLead(lead1);
        Lead testLead = leadManager.checkLeads().stream()
                .filter(lead -> lead.getUuid().equals(lead1.getUuid())).findFirst().get();
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