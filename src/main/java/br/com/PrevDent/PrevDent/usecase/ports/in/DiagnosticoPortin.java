package br.com.PrevDent.PrevDent.usecase.ports.in;

public interface DiagnosticoPortin <Entrada, Saida>{

    Saida cadastrarDiagnostico(Entrada entrada);

    Saida listarDiagnostico();

    Saida atualizarDiagnostico(String id, Entrada obj);

    Saida deletarDiagnostico(String id);

    Saida detalharDiagnostico(String id);
}
