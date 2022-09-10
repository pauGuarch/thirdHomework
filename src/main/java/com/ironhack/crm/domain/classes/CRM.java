package com.ironhack.crm.domain.classes;

import com.ironhack.crm.dao.manager.implementation.*;
import com.ironhack.crm.domain.enums.OpportunityStatus;
import com.ironhack.crm.domain.models.*;
import com.ironhack.crm.utils.Utils;
import com.ironhack.crm.utils.UtilsUserInputs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CRM {
    @Autowired
    private AccountManagerImpl accountManager;
    @Autowired
    private ContactManagerImpl contactManager;
    @Autowired
    private LeadManagerImpl leadManager;
    @Autowired
    private OpportunityManagerImpl opportunityManager;
    @Autowired
    private ProductManagerImpl productManager;
    @Autowired
    private SalesRepManagerImpl salesRepManagerImpl;


    public CRM() {

    }

    public void createNewLead(Lead lead){
        leadManager.createNewLead(lead);
    }

    public void createNewSalesRep(SalesRep salesRep){
        salesRepManagerImpl.createNewSalesRep(salesRep);}

    public List<Lead> checkLeads(){
        return leadManager.checkLeads();
    }

    public Lead lookUpLead(Integer leadId) throws RuntimeException{
        return leadManager.lookUpLead(leadId);
    }

    public SalesRep lookUpSalesRep(Integer id){
        return salesRepManagerImpl.lookUpSalesRep(id);
    }

    public List<Account> checkAccounts(){
        return accountManager.checkAccounts();
    }


    public void convertLeadToOpportunity(Lead lead, Product product, Integer productQuantity,  String accountIndustry,
                                         Integer accountEmployees, String accountCity, String accountCountry){

        if(lead!=null) {
            Contact contact = new Contact(lead.getName(), lead.getEmail(), lead.getPhoneNumber(), lead.getCompanyName());
            Opportunity opportunity = new Opportunity(contact, productQuantity, OpportunityStatus.OPEN, product, lead.getSalesRep());
            List<Contact> contacts = new ArrayList<Contact>();
            contacts.add(contact);
            List<Opportunity> opportunities = new ArrayList<Opportunity>();
            opportunities.add(opportunity);
            Account account = new Account(accountIndustry, accountEmployees, accountCity, accountCountry, contacts, opportunities);
            productManager.createProduct(product);
            contactManager.createNewContact(contact);
            opportunityManager.createNewOpportunity(opportunity);
            accountManager.createAccount(account);
            leadManager.removeLead(lead.getId());
        }
    }

    public void convertLeadToOpportunity(String leadId){
        Lead lead = null;
        try{
        lead = leadManager.lookUpLead(Integer.parseInt(leadId));
        }catch (RuntimeException e){
            System.out.println("DATA-ERROR(The lead Id that you introduced was not found)");
        }
        if(lead!=null) {
            Contact contact = new Contact(lead.getName(), lead.getEmail(), lead.getPhoneNumber(), lead.getCompanyName());
            //List <Contact> contacts = new ArrayList<Contact>();
            contactManager.createNewContact(contact);
            Product product = UtilsUserInputs.createProduct();
            Integer productQuantity = UtilsUserInputs.getProductQuantityInput();
            List<Opportunity> opportunities = new ArrayList<Opportunity>();
            productManager.createProduct(product);
            Account account = accountManager.getAccount(UtilsUserInputs.getAccountId());
            Opportunity opportunity = new Opportunity(contact, productQuantity, OpportunityStatus.OPEN, product, lead.getSalesRep());
            opportunity.setAccount(account);
            //opportunities.add(opportunity);
            opportunityManager.createNewOpportunity(opportunity);
            leadManager.removeLead(Integer.parseInt(leadId));
        }
    }

    public void editOpportunityStatus(String opportunityId, int statusId){
        /*List<Opportunity> opportunities = opportunityManager.checkOpportunities();
        for (Opportunity opportunity : opportunities) {
            if (opportunity.getId().toString().equals(opportunityId)){
                opportunity.setStatus(OpportunityStatus.values()[status-1]);
            }
        }*/
        opportunityManager.updateOpportunity(Integer.parseInt(opportunityId), statusId -1);

        try {
            Utils.writeOpportunityJSON(opportunityManager.checkOpportunities());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public List<Opportunity> checkOpportunities(){
        return opportunityManager.checkOpportunities();
    }

    public Opportunity lookUpOpportunity(Integer id){
        return opportunityManager.lookUpOpportunity(id);
    }

    public List<Contact> checkContacts(){
        return contactManager.checkContacts();
    }

    public void crateNewContact(Contact contact) {
        contactManager.createNewContact(contact);
    }

    public List<SalesRep> checkSalesReps() {
        return salesRepManagerImpl.checkSalesReps();
    }

    //public List<Opportunity> getStatusBySalesRep(Integer salesRepId, int status) { return opportunityManager.getStatusBySalesRep(salesRepId, status);
    public Long countByStatusAndSalesRep(Integer salesRepId, int status) { return opportunityManager.countBySalesRepIdAndStatus(salesRepId, status);
    }

    public Long meanQuantity() {
        return opportunityManager.getMeanQuantity();
    }

    public Long maxQuantity() {
        return opportunityManager.getMaxQuantity();
    }

    public Long minQuantity() {
        return opportunityManager.getMinQuantity();
    }
}
