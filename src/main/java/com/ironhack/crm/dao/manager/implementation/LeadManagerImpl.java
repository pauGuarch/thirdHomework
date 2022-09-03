package com.ironhack.crm.dao.manager.implementation;
import com.ironhack.crm.dao.manager.LeadManager;
import com.ironhack.crm.dao.repositories.LeadRepository;
import com.ironhack.crm.domain.models.Lead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.ironhack.crm.utils.Utils.*;
@Controller
public class LeadManagerImpl implements LeadManager {

    @Autowired
    private LeadRepository leadRepository;
    private List<Lead> leads;


    @Override
    public void createNewLead(Lead lead) {
        leadRepository.save(lead);
        checkLeads();
    }

    public List<Lead> checkLeads(){
        return leadRepository.findAll();
    }

    @Override
    public Lead lookUpLead(Integer leadId) {
        Optional<Lead> leadOptional = leadRepository.findById(leadId);
        if (leadOptional.isPresent()){
            return leadOptional.get();
        }else {
            throw new RuntimeException();
        }
    }

    @Override
    public List<Lead> removeLead(Integer leadId) {
        leadRepository.delete(lookUpLead(leadId));
        checkLeads();
        return leads;
    }
}
