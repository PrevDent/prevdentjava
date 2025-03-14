package br.com.PrevDent.PrevDent.adapter.repository.mapper;

import br.com.PrevDent.PrevDent.adapter.repository.entity.NovosRegistroEntity;
import br.com.PrevDent.PrevDent.domain.model.NovosRegistro;
import org.springframework.stereotype.Component;

@Component
public class NovosRegistroMapper {

    public NovosRegistroEntity converteNovosRegistroEntity(NovosRegistro novosRegistro) {
        NovosRegistroEntity novosRegistroEntity = new NovosRegistroEntity();
        novosRegistroEntity.setIdRegistro(novosRegistro.getIdRegistro());
        novosRegistroEntity.setTipo(novosRegistro.getTipo());
        novosRegistroEntity.setOcorrencia(novosRegistro.getOcorrencia());
        novosRegistroEntity.setIntensidade(novosRegistro.getIntensidade());
        novosRegistroEntity.setInformacoesAdicionais(novosRegistro.getInformacoesAdicionais());
        return novosRegistroEntity;
    }

    public NovosRegistro converteNovosRegistro(NovosRegistroEntity novosRegistroEntity) {
        NovosRegistro novosRegistro = new NovosRegistro();
        novosRegistro.setIdRegistro(novosRegistroEntity.getIdRegistro());
        novosRegistro.setTipo(novosRegistroEntity.getTipo());
        novosRegistro.setOcorrencia(novosRegistroEntity.getOcorrencia());
        novosRegistro.setIntensidade(novosRegistroEntity.getIntensidade());
        novosRegistro.setInformacoesAdicionais(novosRegistroEntity.getInformacoesAdicionais());
        return novosRegistro;
    }

    public void atualizarNovosRegistroEntity(NovosRegistroEntity novosRegistroEntity, NovosRegistro novosRegistro) {
        novosRegistroEntity.setTipo(novosRegistro.getTipo());
        novosRegistroEntity.setOcorrencia(novosRegistro.getOcorrencia());
        novosRegistroEntity.setIntensidade(novosRegistro.getIntensidade());
        novosRegistroEntity.setInformacoesAdicionais(novosRegistro.getInformacoesAdicionais());
    }
}
