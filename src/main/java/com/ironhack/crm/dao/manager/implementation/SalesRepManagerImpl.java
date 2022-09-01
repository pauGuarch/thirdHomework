package com.ironhack.crm.dao.manager.implementation;

import com.ironhack.crm.dao.manager.SalesRepManager;
import com.ironhack.crm.dao.repositories.SalesRepRepository;
import com.ironhack.crm.domain.models.Product;
import com.ironhack.crm.domain.models.SalesRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.ironhack.crm.utils.Utils.writeProductJSON;
import static com.ironhack.crm.utils.Utils.writeSalesRepJSON;


public class SalesRepManagerImpl implements  SalesRepManager{
    @Autowired
    private SalesRepRepository salesRepRepository;

    private static SalesRepManagerImpl salesRepManager;
    private List<SalesRep> salesReps;



    private SalesRepManagerImpl() {
        this.salesReps = checkSalesReps();
        if(salesReps == null){
            salesReps = new ArrayList<>();
        }
    }

    public static SalesRepManagerImpl getInstance() {
        if (salesRepManager == null) {
            salesRepManager = new SalesRepManagerImpl();
        }
        return salesRepManager;
    }

    public List<SalesRep> checkSalesReps() {
        return this.salesReps = salesRepRepository.findAll();
    }

    public void createNewSalesRep(SalesRep salesRep) {
        System.out.println("Wait here");
        salesRepRepository.save(salesRep);
        checkSalesReps();
    }

    public List<SalesRep> removeSalesRep(UUID id) {
        salesRepRepository.delete(lookUpSalesRep(id));
        checkSalesReps();
        return salesReps;
    }

    public SalesRep lookUpSalesRep(UUID uuid) {
        return salesReps.stream().filter(sl->sl.getUuid().equals(uuid)).findFirst().get();
    }
}
