package com.ironhack.crm.dao.controllers.impl;

import com.ironhack.crm.dao.controllers.interfaces.SalesRepController;
import com.ironhack.crm.dao.manager.SalesRepManager;
import com.ironhack.crm.dao.services.interfaces.SalesRepService;
import com.ironhack.crm.domain.models.SalesRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class SalesRepControllerImpl implements SalesRepController {
    @Autowired
    private SalesRepService salesRepService;


    public void createNewSalesRep(SalesRep salesRep) {
        salesRepService.createNewSalesRep(salesRep);
    }
}
