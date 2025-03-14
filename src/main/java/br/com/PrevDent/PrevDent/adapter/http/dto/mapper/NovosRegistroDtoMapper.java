package br.com.PrevDent.PrevDent.adapter.http.dto.mapper;

import br.com.PrevDent.PrevDent.adapter.http.dto.request.NovosRegistroCreatRequest;
import br.com.PrevDent.PrevDent.adapter.http.dto.request.NovosRegistroUpdateRequest;
import br.com.PrevDent.PrevDent.domain.model.NovosRegistro;
import org.springframework.stereotype.Component;

@Component
public class NovosRegistroDtoMapper {

    public NovosRegistro converteCreateNovosRegistro(NovosRegistroCreatRequest novosRegistroCreateDto) {
        NovosRegistro novosRegistro = new NovosRegistro();
        novosRegistro.setIdRegistro(novosRegistroCreateDto.getIdRegistro());
        novosRegistro.setTipo(novosRegistroCreateDto.getTipo());
        novosRegistro.setOcorrencia(novosRegistroCreateDto.getOcorrencia());
        novosRegistro.setIntensidade(novosRegistroCreateDto.getIntensidade());
        novosRegistro.setInformacoesAdicionais(novosRegistroCreateDto.getInformacoesAdicionais());
        return novosRegistro;
    }

    public  NovosRegistro converterNovosRegistroDto(NovosRegistroUpdateRequest novosRegistroUpdateRequest) {
        NovosRegistro novosRegistro = new NovosRegistro();
        novosRegistro.setTipo(novosRegistroUpdateRequest.getTipo());
        novosRegistro.setOcorrencia(novosRegistroUpdateRequest.getOcorrencia());
        novosRegistro.setIntensidade(novosRegistroUpdateRequest.getIntensidade());
        novosRegistro.setInformacoesAdicionais(novosRegistroUpdateRequest.getInformacoesAdicionais());
        return novosRegistro;
    }
}
