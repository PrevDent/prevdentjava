package br.com.PrevDent.PrevDent.adapter.repository.mapper;

import br.com.PrevDent.PrevDent.adapter.repository.entity.DiagnosticoEntity;
import br.com.PrevDent.PrevDent.domain.model.Diagnostico;
import org.springframework.stereotype.Component;

@Component
public class DiagnosticoMapper {

    public DiagnosticoEntity converteDiagnosticoEntity(Diagnostico diagnostico) {
        DiagnosticoEntity diagnosticoEntity = new DiagnosticoEntity();
        diagnosticoEntity.setIdDiagnostico(diagnostico.getIdDiagnostico());
        diagnosticoEntity.setDescricao(diagnostico.getDescricao());
        diagnosticoEntity.setRecomendacao(diagnostico.getRecomendacao());
        return diagnosticoEntity;
    }

    public Diagnostico converteDiagnostico(DiagnosticoEntity diagnosticoEntity) {
        Diagnostico diagnostico = new Diagnostico();
        diagnostico.setIdDiagnostico(diagnosticoEntity.getIdDiagnostico());
        diagnostico.setDescricao(diagnosticoEntity.getDescricao());
        diagnostico.setRecomendacao(diagnosticoEntity.getRecomendacao());
        return diagnostico;
    }

    public Diagnostico converterAtualizacaoDoDiagnostico(DiagnosticoEntity diagnosticoEntity) {
        Diagnostico diagnostico = new Diagnostico();
        diagnostico.setIdDiagnostico(diagnosticoEntity.getIdDiagnostico());
        diagnostico.setDescricao(diagnosticoEntity.getDescricao());
        diagnostico.setRecomendacao(diagnosticoEntity.getRecomendacao());
        return diagnostico;
    }

    public void atualizarDiagnosticoEntity(DiagnosticoEntity diagnosticoEntity, Diagnostico diagnostico) {
        diagnosticoEntity.setDescricao(diagnostico.getDescricao());
        diagnosticoEntity.setRecomendacao(diagnostico.getRecomendacao());
    }
}
