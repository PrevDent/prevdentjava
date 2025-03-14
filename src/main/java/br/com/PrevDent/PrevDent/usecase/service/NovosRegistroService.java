package br.com.PrevDent.PrevDent.usecase.service;

import br.com.PrevDent.PrevDent.domain.model.NovosRegistro;

import java.util.List;
import java.util.Optional;

public interface NovosRegistroService {

    void cadastrarRegistro(NovosRegistro novosRegistro);

    List<NovosRegistro> listarRegistros();

    Optional<NovosRegistro> atualizarRegistro(String id, NovosRegistro novosRegistro);

    boolean deletarRegistro(String id);

    NovosRegistro detalharRegistro(String id);
}
