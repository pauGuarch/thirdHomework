package com.ironhack.crm.dao.manager.implementation;

import com.ironhack.crm.domain.enums.OpportunityStatus;
import com.ironhack.crm.domain.enums.ProductType;
import com.ironhack.crm.domain.models.Contact;
import com.ironhack.crm.domain.models.Opportunity;
import com.ironhack.crm.domain.models.Product;
import com.ironhack.crm.domain.models.SalesRep;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OpportunityManagerImplTest {
    private Contact contact1;
    private Contact contact2;
    private Product product;
    private Opportunity opportunity1;
    private Opportunity opportunity2;
    private OpportunityManagerImpl opportunityManager;

    private SalesRep salesRep;

    @BeforeEach
    void setUp() {
        contact1 = new Contact("Manolo", "manolo@gmail", "212512525", "RamonerCompany");
        contact2 = new Contact("Rubi", "rubi@gmail", "212512525", "RamonerCompany");
        product = new Product(ProductType.HYBRID);
        salesRep = new SalesRep();
        opportunity1 = new Opportunity(contact1, 40, OpportunityStatus.OPEN, product, salesRep);
        opportunity2 = new Opportunity(contact2, 10, OpportunityStatus.CLOSED_LOST, product, salesRep);
    }

    @Test
    void testCreateNewAndCheckOpportunity() {
        opportunityManager.createNewOpportunity(opportunity1);
        Opportunity testOpportunity = opportunityManager.lookUpOpportunity(opportunity1.getId());
        assertEquals("Manolo", testOpportunity.getDecisionMaker().getName());
        //opportunityManager.removeOpportunity(opportunity1.getId());
    }

    @Test
    void lookUpOpportunity() {
        opportunityManager.createNewOpportunity(opportunity1);
        Opportunity lookedOpportunity = opportunityManager.lookUpOpportunity(opportunity1.getId());
        assertEquals(40, lookedOpportunity.getQuantity());
        assertTrue(lookedOpportunity.getUuid().equals(opportunity1.getUuid()));
    }
}