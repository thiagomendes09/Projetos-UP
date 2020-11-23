package br.edu.up.adocao.service;

import br.edu.up.adocao.model.Adotante;
import java.util.List;

public interface AdotanteService {

    Adotante update(Adotante adotante);

    Adotante create(Adotante adotante);

    List<Adotante> listAll();

    void delete(Adotante adotante);
}
