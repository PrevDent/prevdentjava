package br.com.PrevDent.PrevDent.usecase.service;

import br.com.PrevDent.PrevDent.domain.model.Consulta;

import java.util.List;

public interface ConsultaIAService {

    String gerarResumoConsulta(Consulta consulta);

    String classificarPrioridade(Consulta consulta);

    String responderPergunta(String pergunta, List<Consulta> consultas);

    String traduzirConsulta(Consulta consulta, String idiomaDestino);


}
