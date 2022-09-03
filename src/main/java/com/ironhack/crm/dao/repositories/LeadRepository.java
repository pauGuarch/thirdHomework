package com.ironhack.crm.dao.repositories;

import com.ironhack.crm.domain.models.Lead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LeadRepository extends JpaRepository<Lead, Integer>{
    Lead findByUuid(UUID leadId);
}