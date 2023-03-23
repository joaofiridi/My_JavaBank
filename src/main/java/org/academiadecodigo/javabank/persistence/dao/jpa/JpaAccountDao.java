package org.academiadecodigo.javabank.persistence.dao.jpa;

import org.academiadecodigo.javabank.persistence.dao.AccountDao;
import org.academiadecodigo.javabank.persistence.jpa.JpaSessionManager;

public class JpaAccountDao implements AccountDao {

    JpaSessionManager jpaSessionManager;

    JpaAccountDao(JpaSessionManager jpaSessionManager){
     this.jpaSessionManager = jpaSessionManager;
    }

}
