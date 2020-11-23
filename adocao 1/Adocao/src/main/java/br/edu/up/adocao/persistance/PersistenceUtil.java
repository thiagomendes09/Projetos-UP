package br.edu.up.adocao.persistance;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceUtil {

    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("br.edu.up.adocao");

    public static EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

}
