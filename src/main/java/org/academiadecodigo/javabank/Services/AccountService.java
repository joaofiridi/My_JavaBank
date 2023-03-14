package org.academiadecodigo.javabank.Services;

public interface AccountService {

     void add(AccountServ account);

     void deposit (int id, double amount);

     void withdraw (int id, double amount);

     void transfer (int srcId, int dstId, double amount);
}
