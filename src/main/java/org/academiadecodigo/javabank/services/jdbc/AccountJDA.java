package org.academiadecodigo.javabank.services.jdbc;

import org.academiadecodigo.javabank.model.account.Account;
import org.academiadecodigo.javabank.services.AccountService;

public class AccountJDA implements AccountService {
    @Override
    public Account get(Integer id) {
        return null;
    }

    @Override
    public void add(Account account) {

    }

    @Override
    public void deposit(int id, double amount) {

    }

    @Override
    public void withdraw(int id, double amount) {

    }

    @Override
    public void transfer(int srcId, int dstId, double amount) {

    }
}
