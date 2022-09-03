package com.ironhack.crm.dao.manager.implementation;

import com.ironhack.crm.dao.manager.SalesRepManager;
import com.ironhack.crm.dao.repositories.SalesRepRepository;
import com.ironhack.crm.domain.models.Product;
import com.ironhack.crm.domain.models.SalesRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.ironhack.crm.utils.Utils.writeProductJSON;
import static com.ironhack.crm.utils.Utils.writeSalesRepJSON;

@Controller
public class SalesRepManagerImpl implements  SalesRepManager{

    @Autowired
    private SalesRepRepository salesRepRepository;
    private List<SalesRep> salesReps;


    public List<SalesRep> checkSalesReps() {
        salesReps = salesRepRepository.findAll();
        return this.salesReps;
    }

    public void createNewSalesRep(SalesRep salesRep) {
        salesRepRepository.save(salesRep);
        checkSalesReps();
    }

    public List<SalesRep> removeSalesRep(Integer id) {
        salesRepRepository.delete(lookUpSalesRep(id));
        checkSalesReps();
        return salesReps;
    }

    public SalesRep lookUpSalesRep(Integer id) {
        Optional<SalesRep> salesRepOptional = salesRepRepository.findById(id);
        if (salesRepOptional.isPresent()){
            return salesRepOptional.get();
        }else{
            throw new RuntimeException();
        }
    }
}
