package org.academiadecodigo.javabank.services.mock;

import org.academiadecodigo.javabank.model.account.AbstractAccount;
import org.academiadecodigo.javabank.services.AccountService;

import java.util.HashMap;
import java.util.Map;

/**
 * A mock {@link AccountService} implementation
 */
public class MockAccountService implements AccountService {

    private int currentId = 0;
    private Map<Integer, AbstractAccount> modelMap = new HashMap<>();

    @Override
    public AbstractAccount save(AbstractAccount account) {

        if(account.getId() == null){
            account.setId(++currentId);
        }

        return modelMap.put(account.getId(), account);
    }

    /**
     * @see AccountService#deposit(Integer, double)
     */
    public void deposit(Integer id, double amount) {
        modelMap.get(id).credit(amount);
    }

    /**
     * @see AccountService#withdraw(Integer, double)
     */
    public void withdraw(Integer id, double amount) {

        AbstractAccount account = modelMap.get(id);

        if (!account.canWithdraw()) {
            return;
        }

        modelMap.get(id).debit(amount);
    }

    /**
     * @see AccountService#transfer(Integer, Integer, double)
     */
    public void transfer(Integer srcId, Integer dstId, double amount) {

        AbstractAccount srcAccount = modelMap.get(srcId);
        AbstractAccount dstAccount = modelMap.get(dstId);

        // make sure transaction can be performed
        if (srcAccount.canDebit(amount) && dstAccount.canCredit(amount)) {
            srcAccount.debit(amount);
            dstAccount.credit(amount);
        }
    }
}
