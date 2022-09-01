package com.ironhack.crm.dao.repositories;

import com.ironhack.crm.domain.models.SalesRep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesRepRepository extends JpaRepository<SalesRep, Integer> {
}