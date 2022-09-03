package com.ironhack.crm.dao.manager.implementation;
import com.ironhack.crm.dao.manager.AccountManager;
import com.ironhack.crm.dao.repositories.AccountRepository;
import com.ironhack.crm.domain.models.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.ironhack.crm.utils.Utils.*;

@Controller
public class AccountManagerImpl implements AccountManager {
    @Autowired
    private AccountRepository accountRepository;
    private List<Account> accounts;

    @Override
    public void createAccount(Account account) {
        accountRepository.save(account);
        checkAccounts();
    }


    @Override
    public List<Account> checkAccounts() {
        return accountRepository.findAll();
    }

    /*
    @Override
    public List<Account> deleteAccount(UUID id) {
        try {
            Account accountDel = accounts.stream()
                    .filter(account -> account.getUuid().equals(id)).findFirst().get();
            accountRepository.delete(accountDel);
            writeAccountsJSON(accounts);
        } catch (IOException e) {
            e.printStackTrace();
        }
        checkAccounts();
        return accounts;
    }*/
}
