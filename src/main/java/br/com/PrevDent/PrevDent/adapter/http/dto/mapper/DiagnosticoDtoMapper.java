package br.com.PrevDent.PrevDent.adapter.http.dto.mapper;

import br.com.PrevDent.PrevDent.adapter.http.dto.request.DiagnosticoCreateDto;
import br.com.PrevDent.PrevDent.adapter.http.dto.request.DiagnosticoUpdateRequest;
import br.com.PrevDent.PrevDent.domain.model.Diagnostico;
import org.springframework.stereotype.Component;

@Component
public class DiagnosticoDtoMapper {

    public Diagnostico converteCreateDiagnostico(DiagnosticoCreateDto diagnosticoCreateDto) {
        Diagnostico diagnostico = new Diagnostico();
        diagnostico.setIdDiagnostico(diagnosticoCreateDto.getIdDiagnostico());
        diagnostico.setDescricao(diagnosticoCreateDto.getDescricao());
        diagnostico.setRecomendacao(diagnosticoCreateDto.getRecomendacao());
        return diagnostico;
    }

    public  Diagnostico converterDiagnosticoDto(DiagnosticoUpdateRequest diagnosticoUpdateRequest) {
        Diagnostico diagnostico = new Diagnostico();
        diagnostico.setDescricao(diagnosticoUpdateRequest.getDescricao());
        diagnostico.setRecomendacao(diagnosticoUpdateRequest.getRecomendacao());
        return diagnostico;
    }
}
