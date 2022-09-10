package com.ironhack.crm.dao.manager;
import com.ironhack.crm.domain.models.Account;
import java.util.List;
import java.util.UUID;

public interface AccountManager {
    void createAccount(Account account);
    List<Account> checkAccounts();
    Account getAccount(Integer id);
    //List<Account> deleteAccount(UUID id);
    Long getAccountsEmployeesMean();
}
