package br.com.PrevDent.PrevDent.usecase.service.serviceImpl;

import br.com.PrevDent.PrevDent.adapter.repository.entity.DiagnosticoEntity;
import br.com.PrevDent.PrevDent.adapter.repository.mapper.DiagnosticoMapper;
import br.com.PrevDent.PrevDent.domain.exception.DiagnosticoNotFoundException;
import br.com.PrevDent.PrevDent.domain.model.Diagnostico;
import br.com.PrevDent.PrevDent.usecase.ports.out.DiagnosticoPortOut;
import br.com.PrevDent.PrevDent.usecase.service.DiagnosticoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DiagnosticoServiceImpl  implements DiagnosticoService {

    @Autowired
    private DiagnosticoPortOut diagnosticoPortOut;

    @Autowired
    private DiagnosticoMapper diagnosticoMapper;

    @Override
    public void cadastrarDiagnostico(Diagnostico diagnostico) {
        diagnostico.setIdDiagnostico(UUID.randomUUID().toString());
        DiagnosticoEntity diagnosticoEntity = diagnosticoMapper.converteDiagnosticoEntity(diagnostico);
        diagnosticoPortOut.save(diagnosticoEntity);

    }

    @Override
    public List<Diagnostico> listarDiagnosticos() {
        List<DiagnosticoEntity> diagnosticoExistente = diagnosticoPortOut.findAll();

        List<Diagnostico> diagnosticos = new ArrayList<>();

        for (DiagnosticoEntity diagnosticoEntity : diagnosticoExistente) {
            Diagnostico diagnostico = diagnosticoMapper.converteDiagnostico(diagnosticoEntity);
            diagnosticos.add(diagnostico);
        }

        return diagnosticos;
    }

    @Override
    public Optional<Diagnostico> atualizarDiagnostico(String id, Diagnostico diagnostico) {

        Optional<DiagnosticoEntity> diagnosticoExistente = diagnosticoPortOut.findById(id);

        if (diagnosticoExistente.isPresent()) {

            DiagnosticoEntity atualizar = diagnosticoExistente.get();
            diagnosticoMapper.atualizarDiagnosticoEntity(atualizar, diagnostico);

            DiagnosticoEntity diagnosticoAtualizado = diagnosticoPortOut.save(atualizar);
            Diagnostico diagnosticoAtualizadoModel = diagnosticoMapper.converterAtualizacaoDoDiagnostico(diagnosticoAtualizado);

            return Optional.of(diagnosticoAtualizadoModel);

        } else {
            return Optional.empty();
        }
    }

    @Override
    public boolean excluirDiagnostico(String id) {

        Optional<DiagnosticoEntity> diagnosticoExistente = diagnosticoPortOut.findById(id);

        if (diagnosticoExistente.isPresent()) {
            diagnosticoPortOut.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Diagnostico buscarDiagnostico(String id) {
        Optional<DiagnosticoEntity> diagnosticoDetalhado = diagnosticoPortOut.findById(id);

        if(diagnosticoDetalhado.isPresent()){
            Diagnostico diagnostico = diagnosticoMapper.converteDiagnostico(diagnosticoDetalhado.get());
            return diagnostico;
        } else {
            throw new DiagnosticoNotFoundException();
        }
    }
}
