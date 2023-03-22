package org.academiadecodigo.testinhos;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.RollbackException;

public class LeagueService {

    private EntityManagerFactory emf;

    public void add(League league) {

        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(league);
            em.getTransaction().commit();

        } catch (RollbackException exception) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }

    }

    public League get(int id) {
        EntityManager em = emf.createEntityManager();
        League league = null;
        try {
            league = em.find(League.class, id);

        } catch (RollbackException exception) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return league;
    }

    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }
}

