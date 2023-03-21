package org.academiadecodigo.javabank.services;

import org.academiadecodigo.javabank.model.account.AbstractAccount;

/**
 * Common interface for account services, provides methods to manage accounts and perform account transactions
 */
public interface AccountService {

    /**
     * Perform an {@link AbstractAccount} deposit
     *
     * @param id     the id of the account
     * @param amount the amount to deposit
     */
    void deposit(Integer id, double amount);

    /**
     * Perform an {@link AbstractAccount} withdrawal
     *
     * @param id     the id of the account
     * @param amount the amount to withdraw
     */
    void withdraw(Integer id, double amount);

    /**
     * Performs a transfer between two {@link AbstractAccount} if possible
     *
     * @param srcId  the source account id
     * @param dstId  the destination account id
     * @param amount the amount to transfer
     */
    void transfer(Integer srcId, Integer dstId, double amount);

    AbstractAccount save(AbstractAccount account);
}
