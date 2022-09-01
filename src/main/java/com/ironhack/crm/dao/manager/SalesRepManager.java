package com.ironhack.crm.dao.manager;

import com.ironhack.crm.domain.models.SalesRep;

import java.util.List;
import java.util.UUID;

public interface SalesRepManager {
    void createNewSalesRep(SalesRep salesRep);
    List<SalesRep> removeSalesRep(UUID uuid);

    SalesRep lookUpSalesRep(UUID uuid);
}
