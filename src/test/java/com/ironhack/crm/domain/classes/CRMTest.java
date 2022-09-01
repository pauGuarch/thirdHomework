package com.ironhack.crm.domain.classes;
import com.ironhack.crm.dao.manager.implementation.AccountManagerImpl;
import com.ironhack.crm.dao.manager.implementation.ContactManagerImpl;
import com.ironhack.crm.dao.manager.implementation.LeadManagerImpl;
import com.ironhack.crm.dao.manager.implementation.OpportunityManagerImpl;
import com.ironhack.crm.domain.enums.OpportunityStatus;
import com.ironhack.crm.domain.enums.ProductType;
import com.ironhack.crm.domain.models.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

class CRMTest {

    private CRM crm;
    private Lead lead1;
    private Lead lead2;
    private OpportunityManagerImpl opportunityManager;
    private Contact contact1;
    private Contact contact2;
    private Product product;
    private ContactManagerImpl contactManager;
    private Opportunity opportunity1;
    private Opportunity opportunity2;

    private SalesRep salesRep;

    @BeforeEach
    void setUp() {

        crm = new CRM();
        contactManager = ContactManagerImpl.getInstance();
        opportunityManager = OpportunityManagerImpl.getInstance();
        salesRep = new SalesRep();
        lead1 = new Lead("Silvia", "Fagor", "silviafagor@gmail.com", "677222444", salesRep);
        lead2 = new Lead("María", "Ikea", "mariavikea@gmail.com", "625888444", salesRep);
        contact1 = new Contact(lead1.getName(), lead1.getCompanyName(), lead1.getEmail(), lead1.getPhoneNumber());
        contact2 = new Contact(lead2.getName(), lead2.getCompanyName(), lead2.getEmail(), lead2.getPhoneNumber());
        contactManager.createNewContact(contact1);
        contactManager.createNewContact(contact2);
        product = new Product(ProductType.HYBRID);
        opportunity1 = new Opportunity(contact1, 40, OpportunityStatus.OPEN, product, salesRep);
        opportunity2 = new Opportunity(contact2, 10, OpportunityStatus.CLOSED_LOST, product, salesRep);
        crm.createNewLead(lead1);
        crm.createNewLead(lead2);
        opportunityManager.createNewOpportunity(opportunity1);
        opportunityManager.createNewOpportunity(opportunity2);
    }

    @AfterEach
    void tearDown(){
        LeadManagerImpl.getInstance().removeLead(lead1.getUuid());
        LeadManagerImpl.getInstance().removeLead(lead2.getUuid());
        opportunityManager.removeOpportunity(opportunity1.getUuid());
        opportunityManager.removeOpportunity(opportunity2.getUuid());
        contactManager.deleteContact(contact1.getUuid());
        contactManager.deleteContact(contact2.getUuid());
    }


    @Test
    void lookUpLeadTest() {

        Lead leadId1 = crm.lookUpLead(lead1.getUuid());
        Lead leadId2 = crm.lookUpLead(lead2.getUuid());
        assertEquals(lead1.getUuid(), leadId1.getUuid());
        assertEquals(lead2.getUuid(), leadId2.getUuid());
    }

    @Test
    void lookUpOpportunityTest() {
        Opportunity opportunityId1 = crm.lookUpOpportunity(opportunity1.getUuid());
        Opportunity opportunityId2 = crm.lookUpOpportunity(opportunity2.getUuid());
        assertEquals(opportunity1.getUuid(), opportunityId1.getUuid());
        assertEquals(opportunity2.getUuid(), opportunityId2.getUuid());
    }

    @Test
    void editOpportunityStatusToClosedWonTest() {
        crm.editOpportunityStatus(opportunity1.getUuid().toString(), 2);
        assertEquals(OpportunityStatus.CLOSED_WON, opportunityManager.lookUpOpportunity(opportunity1.getUuid()).getStatus());
    }

    @Test
    void editOpportunityStatusToClosedLostTest() {
        crm.editOpportunityStatus(opportunity1.getUuid().toString(), 3);
        assertEquals(OpportunityStatus.CLOSED_LOST, opportunityManager.lookUpOpportunity(opportunity1.getUuid()).getStatus());
    }

    @Test
    void convertLeadToOpportunityTest() {
        Lead leadToConvert = crm.lookUpLead(lead1.getUuid());
        crm.convertLeadToOpportunity(lead1.getUuid().toString(), product, 2, "ES000222", 002, "0023", "001", salesRep);
        int contactsLength = contactManager.checkContacts().stream().filter(contact -> contact.getName().equals(leadToConvert.getName()) && contact.getCompanyName().equals(leadToConvert.getCompanyName())).toList().size();
        assertEquals(1, contactsLength);
        Contact contact =  contactManager.checkContacts().stream().filter(cont -> cont.getName().equals(leadToConvert.getName()) && cont.getCompanyName().equals(leadToConvert.getCompanyName())).toList().get(0);
        Opportunity opportunity = opportunityManager.checkOpportunities().stream().filter(opport-> opport.getDecisionMaker().getUuid().toString().equals(contact.getUuid().toString())).toList().get(0);
        Account account = AccountManagerImpl.getInstance().checkAccounts().stream().filter(acc->acc.getIndustry().equals("ES000222")).toList().get(0);
        AccountManagerImpl.getInstance().deleteAccount(account.getUuid());
        opportunityManager.removeOpportunity(opportunity.getUuid());
        contactManager.deleteContact(contact.getUuid());
    }
}
