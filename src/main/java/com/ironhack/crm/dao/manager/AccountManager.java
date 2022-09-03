package com.ironhack.crm.dao.manager;
import com.ironhack.crm.domain.models.Account;
import java.util.List;
import java.util.UUID;

public interface AccountManager {
    void createAccount(Account account);
    List<Account> checkAccounts();

    //List<Account> deleteAccount(UUID id);
}
