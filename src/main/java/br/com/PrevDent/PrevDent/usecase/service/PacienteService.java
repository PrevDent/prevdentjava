package br.com.PrevDent.PrevDent.usecase.service;

import br.com.PrevDent.PrevDent.domain.model.Paciente;

import java.util.List;
import java.util.Optional;

public interface PacienteService {

    void cadastarPaciente(Paciente paciente);

    List<Paciente> listarPacientes();

    Optional<Paciente> atualizarPaciente(String id, Paciente paciente);

    boolean excluirPaciente(String id);

    Paciente buscarPaciente(String id);

    String ValidarLogin(String cpf, String senhaDigitada);

}
