package org.academiadecodigo.javabank.model.account;

import javax.persistence.Entity;

/**
 * A savings account model entity which requires a minimum balance
 * and can only be used for transferring money, not for debiting
 * @see AbstractAccount
 * @see AccountType#SAVINGS
 */
@Entity
public class SavingsAccount extends AbstractAccount {

    public static final double MIN_BALANCE = 100;

    /**
     * @see AbstractAccount#getAccountType()
     */
    @Override
    public AccountType getAccountType() {
        return AccountType.SAVINGS;
    }

    /**
     * @see AbstractAccount#canDebit(double)
     */
    @Override
    public boolean canDebit(double amount) {
        return super.canDebit(amount) && (getBalance() - amount) >= MIN_BALANCE;
    }

    /**
     * @see AbstractAccount#canWithdraw()
     */
    @Override
    public boolean canWithdraw() {
        return false;
    }
}
