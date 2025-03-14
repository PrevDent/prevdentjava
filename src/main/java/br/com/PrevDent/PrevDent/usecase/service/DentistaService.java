package br.com.PrevDent.PrevDent.usecase.service;

import br.com.PrevDent.PrevDent.domain.model.Dentista;

import java.util.List;
import java.util.Optional;

public interface DentistaService {

    void cadastrarDentista(Dentista dentista);

    List<Dentista> listarDentistas();

    Optional<Dentista> atualizarDentista(String id, Dentista dentista);

    boolean removerDentista(String id);

    Dentista buscarDentista(String id);
}
