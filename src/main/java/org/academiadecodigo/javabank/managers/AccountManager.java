package org.academiadecodigo.javabank.managers;

import org.academiadecodigo.javabank.factories.AccountFactory;
import org.academiadecodigo.javabank.model.account.Account;
import org.academiadecodigo.javabank.model.account.AccountType;

import java.util.HashMap;
import java.util.Map;

/**
 * Responsible for managing accounts
 */
public class AccountManager {

    private AccountFactory accountFactory = new AccountFactory();
    private Map<Integer, Account> accountMap;

    /**
     * Creates a new {@code AccountManager}
     */
    public AccountManager() {
        accountMap = new HashMap<>();
    }

    /**
     * Creates a new {@link Account}
     *
     * @param accountType the account type
     * @return the new account
     * @see AccountFactory#createAccount(AccountType)
     */
    public Account openAccount(AccountType accountType) {
        Account newAccount = accountFactory.createAccount(accountType);
        accountMap.put(newAccount.getId(), newAccount);
        return newAccount;
    }

    /**
     * Perform an {@link Account} deposit if possible
     *
     * @param id     the id of the account
     * @param amount the amount to deposit
     */
    public void deposit(int id, double amount) {
        accountMap.get(id).credit(amount);
    }

    /**
     * Perform an {@link Account} withdrawal if possible
     *
     * @param id     the id of the account
     * @param amount the amount to withdraw
     */
    public void withdraw(int id, double amount) {

        Account account = accountMap.get(id);

        if (!account.canWithdraw()) {
            return;
        }

        accountMap.get(id).debit(amount);
    }

    /**
     * Performs a transfer between two {@link Account} if possible
     *
     * @param srcId  the source account id
     * @param dstId  the destination account id
     * @param amount the amount to transfer
     */
    public void transfer(int srcId, int dstId, double amount) {

        Account srcAccount = accountMap.get(srcId);
        Account dstAccount = accountMap.get(dstId);

        // make sure transaction can be performed
        if (srcAccount.canDebit(amount) && dstAccount.canCredit(amount)) {
            srcAccount.debit(amount);
            dstAccount.credit(amount);
        }
    }
}
