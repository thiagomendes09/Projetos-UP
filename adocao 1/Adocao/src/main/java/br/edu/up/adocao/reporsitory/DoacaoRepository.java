package br.edu.up.adocao.reporsitory;

import br.edu.up.adocao.model.Doacao;

import java.util.List;

public interface DoacaoRepository {


    List<Doacao> getAll();

    void save(Doacao doacao);
}

