package br.edu.up.adocao.reporsitory;

import br.edu.up.adocao.model.Adotante;

import javax.persistence.EntityManager;
import java.util.List;

public class AdotanteRepositoryJpa implements AdotanteRepository{

    private final EntityManager entityManager;

    public AdotanteRepositoryJpa(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void update(Adotante adotante) {
        entityManager.getTransaction().begin();
        entityManager.merge(adotante);
        entityManager.getTransaction().commit();
    }

    @Override
    public void save(Adotante adotante) {
        entityManager.getTransaction().begin();
        entityManager.persist(adotante);
        entityManager.getTransaction().commit();
    }

    @Override
    public List<Adotante> getAll() {
        return entityManager.createQuery("select a from Adotante a", Adotante.class).getResultList();
    }

    @Override
    public void delete(Long id) {
         entityManager.getTransaction().begin();
         Adotante adotante = entityManager.find(Adotante.class,id);
         entityManager.remove(adotante);
         entityManager.getTransaction().commit();
    }
}
