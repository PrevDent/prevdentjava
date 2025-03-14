package br.com.PrevDent.PrevDent.usecase.service.serviceImpl;

import br.com.PrevDent.PrevDent.adapter.repository.entity.DentistaEntity;
import br.com.PrevDent.PrevDent.adapter.repository.mapper.DentistaMapper;
import br.com.PrevDent.PrevDent.domain.exception.DentistaNotFoudException;
import br.com.PrevDent.PrevDent.domain.model.Dentista;
import br.com.PrevDent.PrevDent.usecase.ports.out.DentistaPortOut;
import br.com.PrevDent.PrevDent.usecase.service.DentistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DentistaServiceImpl implements DentistaService {

    @Autowired
    private DentistaPortOut dentistaPortOut;

    @Autowired
    private DentistaMapper dentistaMapper;

    @Override
    public void cadastrarDentista(Dentista dentista) {
        DentistaEntity dentistaEntity = dentistaMapper.converteDentistaEntity(dentista);
        dentistaPortOut.save(dentistaEntity);
    }

    @Override
    public List<Dentista> listarDentistas() {

        List<DentistaEntity> dentistaExistente = dentistaPortOut.findAll();

        List<Dentista> dentistas = new ArrayList<>();

        for (DentistaEntity dentistaEntity : dentistaExistente) {

            Dentista dentista = dentistaMapper.converteDentista(dentistaEntity);

            dentistas.add(dentista);
        }

        return dentistas;
    }

    @Override
    public Optional<Dentista> atualizarDentista(String id, Dentista dentista) {
        Optional<DentistaEntity> pacienteExistente = dentistaPortOut.findById(id);

        if (pacienteExistente.isPresent()) {

            DentistaEntity autualizar = pacienteExistente.get();
            dentistaMapper.atualizarDentistaEntity(autualizar, dentista);

            DentistaEntity dentistaAtualizado = dentistaPortOut.save(autualizar);
            Dentista dentistaAtualiza = dentistaMapper.converterAtualizacaoDoDentista(dentistaAtualizado);

            return Optional.of(dentistaAtualiza);

        } else {
            return Optional.empty();
        }
    }

    @Override
    public boolean removerDentista(String id) {
        Optional<DentistaEntity> dentistaExistente = dentistaPortOut.findById(id);

        if (dentistaExistente.isPresent()) {

            dentistaPortOut.deleteById(id);

            return true;

        } else {
            return false;
        }
    }

    @Override
    public Dentista buscarDentista(String id) {
        Optional<DentistaEntity> dentistaDetalhado = dentistaPortOut.findById(id);

        if(dentistaDetalhado.isPresent()){

            Dentista dentista = dentistaMapper.converteDentista(dentistaDetalhado.get());

            return dentista;

        } else {
            throw new DentistaNotFoudException();
        }
    }
}
