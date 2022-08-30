package com.ironhack.crm.dao.manager.implementation;
import com.ironhack.crm.dao.manager.AccountManager;
import com.ironhack.crm.domain.models.Account;
import com.ironhack.crm.domain.models.Contact;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.ironhack.crm.utils.Utils.*;

public class AccountManagerImpl implements AccountManager {
    private static AccountManagerImpl accountManager;
    private List<Account> accounts;

    private AccountManagerImpl() {
        this.accounts = checkAccounts();
        if(accounts == null){
            accounts = new ArrayList<>();
        }
    }

    public static AccountManagerImpl getInstance() {
        if (accountManager == null) {
            accountManager = new AccountManagerImpl();
        }
        return accountManager;
    }


    @Override
    public void createAccount(Account account) {
        accounts.add(account);
        try {
            writeAccountsJSON(this.accounts);
        } catch (IOException e) {
            e.printStackTrace();
        }
        checkAccounts();
    }


    @Override
    public List<Account> checkAccounts() {
        return this.accounts = readAccounts();
    }

    @Override
    public List<Account> deleteAccount(UUID id) {
        try {
            Account accountDel = accounts.stream()
                    .filter(account -> account.getId().equals(id)).findFirst().get();
            accounts.remove(accountDel);
            writeAccountsJSON(accounts);
        } catch (IOException e) {
            e.printStackTrace();
        }
        checkAccounts();
        return accounts;
    }
}
