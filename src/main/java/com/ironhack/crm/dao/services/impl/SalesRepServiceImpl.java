package com.ironhack.crm.dao.services.impl;

import com.ironhack.crm.dao.repositories.SalesRepRepository;
import com.ironhack.crm.dao.services.interfaces.SalesRepService;
import com.ironhack.crm.domain.models.SalesRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalesRepServiceImpl implements SalesRepService {
    @Autowired
    private SalesRepRepository salesRepRepository;

    public void createNewSalesRep(SalesRep salesRep) {
        salesRepRepository.save(salesRep);
    }
}
