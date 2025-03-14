package br.com.PrevDent.PrevDent.usecase.ports.in;

public interface ConsultaPortIn<Entrada, Saida> {

    Saida cadastrarConsulta(Entrada entrada);

    Saida listarConsultas();

    Saida atualizarConsulta(String id, Entrada obj);

    Saida deletarConsulta(String id);

    Saida detalharConsulta(String id);
}