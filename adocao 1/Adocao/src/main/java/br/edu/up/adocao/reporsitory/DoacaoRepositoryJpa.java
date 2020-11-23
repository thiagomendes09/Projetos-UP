package br.edu.up.adocao.reporsitory;

import br.edu.up.adocao.model.Animal;
import br.edu.up.adocao.model.Doacao;

import javax.persistence.EntityManager;
import java.util.List;

public class DoacaoRepositoryJpa implements DoacaoRepository{

    private final EntityManager entityManager;

    public DoacaoRepositoryJpa(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public List<Doacao> getAll() {
        return entityManager.createQuery("select a from Doacao a", Doacao.class).getResultList();
    }

    @Override
    public void save(Doacao doacao) {
        entityManager.getTransaction().begin();
        entityManager.persist(doacao);
        entityManager.getTransaction().commit();
    }
}
