package br.com.PrevDent.PrevDent.usecase.ports.in;

public interface DentistaPortin<Entrada, Saida> {

    Saida cadastrarDentista(Entrada entrada);

    Saida listarDentistas();

    Saida atualizarDentista(String id, Entrada obj);

    Saida deletarDentista(String id);

    Saida detalharDentista(String id);

}
