package br.edu.up.adocao.reporsitory;

import br.edu.up.adocao.model.Adocao;

import javax.persistence.EntityManager;

public class AdocaoRepositoryJpa implements AdocaoRepository{

    private final EntityManager entityManager;

    public AdocaoRepositoryJpa(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(Adocao adocao) {
        entityManager.getTransaction().begin();
        entityManager.persist(adocao);
        entityManager.getTransaction().commit();
    }
}
