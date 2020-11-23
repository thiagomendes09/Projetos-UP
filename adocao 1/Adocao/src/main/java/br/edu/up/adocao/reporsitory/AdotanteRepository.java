package br.edu.up.adocao.reporsitory;

import br.edu.up.adocao.model.Adotante;

import java.util.List;

public interface AdotanteRepository {


    void update(Adotante adotante);

    void save(Adotante adotante);

    List<Adotante> getAll();

    void delete(Long id);
}
