package com.ironhack.crm.dao.repositories;

import com.ironhack.crm.domain.models.SalesRep;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SalesRepRepositoryTest {

    @Autowired
    private SalesRepRepository salesRepRepository;

    private SalesRep salesRep1;


    @BeforeEach
    void setUp() {
        salesRep1 =salesRepRepository.save(new SalesRep("Ramoner"));
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createNewSalesRepTest(){
        assertEquals(salesRep1.getName() , salesRepRepository.findAll().get(0).getName());
    }
}