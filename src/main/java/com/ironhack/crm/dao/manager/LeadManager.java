package com.ironhack.crm.dao.manager;

import com.ironhack.crm.domain.models.Lead;

import java.util.List;
import java.util.UUID;

public interface LeadManager {
    void createNewLead(Lead lead);
    List<Lead> checkLeads();
    Lead lookUpLead(Integer leadId);
    List<Lead> removeLead(Integer leadId);
}
