package org.academiadecodigo.javabank.persistence.jpa;

import org.academiadecodigo.javabank.persistence.TransactionManager;

public class JpaTransactionManager implements TransactionManager {

    JpaSessionManager jsm;
    @Override
    public void beginRead() {
        jsm.startSession();
    }

    @Override
    public void beginWrite() {
        jsm.getCurrentSession().getTransaction().begin();
    }

    @Override
    public void commit() {
       if (jsm.getCurrentSession().getTransaction().isActive()){
           jsm.getCurrentSession().getTransaction().commit();
       }
        jsm.stopSession();
    }

    @Override
    public void rollback() {
        if(jsm.getCurrentSession().getTransaction().isActive()){
            jsm.getCurrentSession().getTransaction().rollback();
        }
        jsm.stopSession();
    }
}
