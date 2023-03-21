package org.academiadecodigo.javabank.model.account;

import javax.persistence.Entity;

/**
 * A checking account with no restrictions
 * @see AbstractAccount
 * @see AccountType#CHECKING
 */
@Entity
public class CheckingAccount extends AbstractAccount {

    /**
     * @see AbstractAccount#getAccountType()
     */
    @Override
    public AccountType getAccountType() {
        return AccountType.CHECKING;
    }
}
