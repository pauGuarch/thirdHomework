package com.ironhack.crm.dao.manager.implementation;
import com.ironhack.crm.dao.manager.OpportunityManager;
import com.ironhack.crm.dao.repositories.OpportunityRepository;
import com.ironhack.crm.domain.enums.OpportunityStatus;
import com.ironhack.crm.domain.models.Opportunity;
import com.ironhack.crm.domain.models.SalesRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.ironhack.crm.utils.Utils.*;

@Controller
public class OpportunityManagerImpl implements OpportunityManager {

    @Autowired
    private OpportunityRepository opportunityRepository;

    private List<Opportunity> opportunities;


    @Override
    public void createNewOpportunity(Opportunity opportunity) {
        opportunityRepository.save(opportunity);
    }

    @Override
    public List<Opportunity> checkOpportunities() {
        return opportunityRepository.findAll();
    }

    @Override
    public Opportunity lookUpOpportunity(Integer id) {
        Optional<Opportunity> opportunityOptional = opportunityRepository.findById((id));
        if (opportunityOptional.isPresent()){
            return opportunityOptional.get();
        }else{
            throw new RuntimeException();
        }
    }

    public void updateOpportunity(Integer id, int i) {
        Opportunity newOpportunity = lookUpOpportunity(id);
        newOpportunity.setStatus(OpportunityStatus.values()[i]);
        opportunityRepository.save(newOpportunity);
    }


    public Long countBySalesRepIdAndStatus(Integer salesRepId, int status){
        return opportunityRepository.countBySalesRepIdAndStatus(salesRepId, OpportunityStatus.values()[status]);
    }

    public Long getMeanQuantity() {
        return opportunityRepository.meanQuantity();
    }

    public Long getMaxQuantity() {
        return opportunityRepository.getMaxQuantity();
    }

    public Long getMinQuantity(){
        return opportunityRepository.getMinQuantity();
    }


    @Override
    public List<Opportunity> getOpportunitiesBySalesRep(Integer id) {
        return opportunityRepository.findOpportunityBySalesRepId(id);
    }

    /*
    @Override
    public List<Opportunity> removeOpportunity(Integer id) {
        opportunityRepository.delete(lookUpOpportunity(id));
        checkOpportunities();
        return opportunities;
    }*/


}
