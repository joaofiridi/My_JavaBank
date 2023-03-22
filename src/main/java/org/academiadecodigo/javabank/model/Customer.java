package org.academiadecodigo.javabank.model;

import org.academiadecodigo.javabank.model.account.AbstractAccount;
import org.academiadecodigo.javabank.model.account.Account;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The customer model entity
 */
@Entity
@Table(name = "customer")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Customer extends AbstractModel {

    private String first_Name;
    private String last_Name;
    private String email;
    private String phone;
    @OneToMany (targetEntity = AbstractAccount.class)
    private List<Account> accounts = new ArrayList<>();
    public Customer(){}
    public Customer(String firstName, String lastName, String email, String phone){
        this.first_Name = firstName;
        this.last_Name = lastName;
        this.email = email;
        this.phone = phone;
    }

    /**
     * Gets the firstName of the customer
     *
     * @return the customer firstName
     */
    public String getFirst_Name() {
        return first_Name;
    }

    /**
     * Sets the firstName of the customer
     *
     * @param firstName the firstName to set
     */
    public void setFirst_Name(String firstName) {
        this.first_Name = firstName;
    }

    public String getLast_Name() {
        return last_Name;
    }

    public void setLast_Name(String lastName) {
        this.last_Name = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Gets the customer accounts
     *
     * @return the accounts
     */
    public List<Account> getAccounts() {
        return accounts;
    }

    /**
     * Adds a new account to the customer
     *
     * @param account the account to add
     */
    public void addAccount(Account account) {
        account.setCustomerId(getId());
        accounts.add(account);
    }

    /**
     * Removes an account from the customer
     *
     * @param account the account to remove
     */
    public void removeAccount(Account account) {
        accounts.remove(account);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "firstName='" + first_Name + '\'' +
                ", lastName='" + last_Name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}


