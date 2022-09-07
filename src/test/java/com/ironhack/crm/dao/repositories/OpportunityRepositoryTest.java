package com.ironhack.crm.dao.repositories;

import com.ironhack.crm.domain.enums.OpportunityStatus;
import com.ironhack.crm.domain.enums.ProductType;
import com.ironhack.crm.domain.models.Contact;
import com.ironhack.crm.domain.models.Opportunity;
import com.ironhack.crm.domain.models.Product;
import com.ironhack.crm.domain.models.SalesRep;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OpportunityRepositoryTest {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private  ContactRepository contactRepository;
    @Autowired
    private SalesRepRepository salesRepRepository;
    @Autowired
    private OpportunityRepository opportunityRepository;

    private Contact contact1;
    private Contact contact2;
    private Product product;
    private SalesRep salesRep;
    private Opportunity opportunity1;
    private Opportunity opportunity2;


    @BeforeEach
    void setUp() {
        contact1 = contactRepository.save(new Contact("Manolo", "manolo@gmail", "212512525", "RamonerCompany"));
        product = productRepository.save(new Product("Julian3", ProductType.HYBRID));
        salesRep = salesRepRepository.save(new SalesRep("JrRep"));
        opportunity1 = opportunityRepository.save(new Opportunity(contact1, 40, OpportunityStatus.CLOSED_WON, product, salesRep));
        opportunity2 = opportunityRepository.save(new Opportunity(contact1, 40, OpportunityStatus.CLOSED_WON, product, salesRep));
    }

    @AfterEach
    void tearDown() {
        opportunityRepository.delete(opportunity1);
        opportunityRepository.delete(opportunity2);
        contactRepository.delete(contact1);
        productRepository.delete(product);
        salesRepRepository.delete(salesRep);
    }


    @Test
    void countOpportunitiesByStatusAndSalesRepTest() {
        System.out.println("SalesRep ID: " + salesRep.getId());
        Long counts = opportunityRepository.countBySalesRepIdAndStatus(salesRep.getId(), OpportunityStatus.values()[1]);
        System.out.println("SalesRep : " + salesRep.getName() + " Opportunities Close-Won : " + counts);
        assertEquals(2, counts);
    }
}