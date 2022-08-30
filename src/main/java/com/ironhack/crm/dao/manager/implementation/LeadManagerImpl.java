package com.ironhack.crm.dao.manager.implementation;
import com.ironhack.crm.dao.manager.LeadManager;
import com.ironhack.crm.domain.models.Account;
import com.ironhack.crm.domain.models.Lead;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.ironhack.crm.utils.Utils.*;

public class LeadManagerImpl implements LeadManager {
    private static LeadManagerImpl leadManager;
    private List<Lead> leads;

    private LeadManagerImpl() {
        this.leads = checkLeads();
        if (leads == null){
            leads = new ArrayList<>();
        }
    }

    public static LeadManagerImpl getInstance() {
        if (leadManager == null) {
            leadManager = new LeadManagerImpl();
        }
        return leadManager;
    }

    @Override
    public void createNewLead(Lead lead) {
        leads.add(lead);
        try {
            writeLeadsJSON(leads);
        } catch (IOException e) {
            e.printStackTrace();
        }
        checkLeads();
    }

    public List<Lead> checkLeads(){
        return this.leads = readLeads();
    }

    @Override
    public Lead lookUpLead(UUID leadId) {
        return leads.stream().filter(l->l.getId().equals(leadId)).findFirst().get();
    }

    @Override
    public List<Lead> removeLead(UUID leadId) {
        try {
            Lead leadDel = leads.stream()
                    .filter(account -> account.getId().equals(leadId)).findFirst().get();
            leads.remove(leadDel);
            writeLeadsJSON(leads);
        } catch (IOException e) {
            e.printStackTrace();
        }
        checkLeads();
        return leads;
    }
}
