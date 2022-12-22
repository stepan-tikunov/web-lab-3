package edu.ifmo.tikunov.web.lab3;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.RollbackException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ApplicationScoped
public class ResultsManager implements Serializable {

    private List<Result> results;
    private EntityManagerFactory resultEntityManagers;

    public ResultsManager() {
        this.resultEntityManagers = Persistence.createEntityManagerFactory("result");
        this.results = new ArrayList<>();
    }

    public List<Result> getResults() {
        return results;
    }

    public boolean insert(Result result) {
        EntityManager entityManager = resultEntityManagers.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(result);
            entityManager.getTransaction().commit();

            this.results.add(result);
            return true;
        } catch (RollbackException e) {
            entityManager.getTransaction().rollback();
            return false;
        }
    }
}
