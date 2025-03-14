package br.com.PrevDent.PrevDent.usecase.service.serviceImpl;

import br.com.PrevDent.PrevDent.adapter.repository.entity.NovosRegistroEntity;
import br.com.PrevDent.PrevDent.adapter.repository.mapper.NovosRegistroMapper;
import br.com.PrevDent.PrevDent.domain.exception.NovosRegistroNotFoundException;
import br.com.PrevDent.PrevDent.domain.model.NovosRegistro;
import br.com.PrevDent.PrevDent.usecase.ports.out.NovosRegistroPortOut;
import br.com.PrevDent.PrevDent.usecase.service.NovosRegistroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NovosRegistroImpl implements NovosRegistroService {

    @Autowired
    private NovosRegistroPortOut novosRegistroPortOut;

    @Autowired
    private NovosRegistroMapper novosRegistroMapper;

    @Override
    public void cadastrarRegistro(NovosRegistro novosRegistro) {
        novosRegistro.setIdRegistro(UUID.randomUUID().toString());
        NovosRegistroEntity novosRegistroEntity = novosRegistroMapper.converteNovosRegistroEntity(novosRegistro);
        novosRegistroPortOut.save(novosRegistroEntity);
    }

    @Override
    public List<NovosRegistro> listarRegistros() {
        List<NovosRegistroEntity> registrosExistentes = novosRegistroPortOut.findAll();
        List<NovosRegistro> registros = new ArrayList<>();

        for (NovosRegistroEntity registroEntity : registrosExistentes) {
            NovosRegistro registro = novosRegistroMapper.converteNovosRegistro(registroEntity);
            registros.add(registro);
        }

        return registros;
    }

    @Override
    public Optional<NovosRegistro> atualizarRegistro(String id, NovosRegistro novosRegistro) {
        Optional<NovosRegistroEntity> registroExistente = novosRegistroPortOut.findById(id);

        if (registroExistente.isPresent()) {
            NovosRegistroEntity atualizar = registroExistente.get();
            novosRegistroMapper.atualizarNovosRegistroEntity(atualizar, novosRegistro);

            NovosRegistroEntity registroAtualizado = novosRegistroPortOut.save(atualizar);
            NovosRegistro registroAtualiza = novosRegistroMapper.converteNovosRegistro(registroAtualizado);

            return Optional.of(registroAtualiza);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public boolean deletarRegistro(String id) {
        Optional<NovosRegistroEntity> registroExistente = novosRegistroPortOut.findById(id);

        if (registroExistente.isPresent()) {
            novosRegistroPortOut.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public NovosRegistro detalharRegistro(String id) {
        Optional<NovosRegistroEntity> registroDetalhado = novosRegistroPortOut.findById(id);

        if (registroDetalhado.isPresent()) {
            NovosRegistro registro = novosRegistroMapper.converteNovosRegistro(registroDetalhado.get());
            return registro;
        } else {
            throw new NovosRegistroNotFoundException();
        }
    }
}