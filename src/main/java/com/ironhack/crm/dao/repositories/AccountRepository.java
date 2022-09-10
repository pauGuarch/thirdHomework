package com.ironhack.crm.dao.repositories;

import com.ironhack.crm.domain.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    @Query("SELECT AVG(a.employeeCount) FROM Account a")
    Long getAccountEmployeesAverage();

    @Query("SELECT MAX(a.employeeCount) FROM Account a")
    Long getAccountEmployeesMax();

    @Query("SELECT MIN(a.employeeCount) FROM Account a")
    Long getAccountEmployeesMin();
}