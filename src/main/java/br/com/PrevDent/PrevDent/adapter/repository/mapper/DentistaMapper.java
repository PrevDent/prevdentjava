package br.com.PrevDent.PrevDent.adapter.repository.mapper;

import br.com.PrevDent.PrevDent.adapter.repository.entity.DentistaEntity;
import br.com.PrevDent.PrevDent.domain.model.Dentista;
import org.springframework.stereotype.Component;

@Component
public class DentistaMapper {

    public DentistaEntity converteDentistaEntity(Dentista dentista) {
        DentistaEntity dentistaEntity = new DentistaEntity();
        dentistaEntity.setIdDentista(dentista.getIdDentista());
        dentistaEntity.setNome(dentista.getNome());
        dentistaEntity.setDocumento(dentista.getDocumento());
        dentistaEntity.setEspecializacao(dentista.getEspecializacao());
        return dentistaEntity;
    }

    public Dentista converteDentista(DentistaEntity dentistaEntity) {
        Dentista dentista = new Dentista();
        dentista.setIdDentista(dentistaEntity.getIdDentista());
        dentista.setNome(dentistaEntity.getNome());
        dentista.setDocumento(dentistaEntity.getDocumento());
        dentista.setEspecializacao(dentistaEntity.getEspecializacao());
        return dentista;
    }

    public Dentista converterAtualizacaoDoDentista(DentistaEntity dentistaEntity){
        Dentista dentista = new Dentista();
        dentista.setNome(dentistaEntity.getNome());
        dentista.setEspecializacao(dentistaEntity.getEspecializacao());
        return dentista;
    }

    public void atualizarDentistaEntity(DentistaEntity dentistaEntity, Dentista dentista){
        dentistaEntity.setNome(dentista.getNome());
        dentistaEntity.setEspecializacao(dentista.getEspecializacao());
    }
}
