package br.edu.up.adocao.service;

import br.edu.up.adocao.model.Doacao;
import java.util.List;

public interface DoacaoService {

    List<Doacao> listAll();

    Doacao create(Doacao doacao);

}
