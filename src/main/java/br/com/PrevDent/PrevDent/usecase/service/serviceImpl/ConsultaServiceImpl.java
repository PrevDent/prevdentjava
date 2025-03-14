package br.com.PrevDent.PrevDent.usecase.service.serviceImpl;

import br.com.PrevDent.PrevDent.adapter.repository.entity.ConsultaEntity;
import br.com.PrevDent.PrevDent.adapter.repository.entity.DentistaEntity;
import br.com.PrevDent.PrevDent.adapter.repository.entity.DiagnosticoEntity;
import br.com.PrevDent.PrevDent.adapter.repository.entity.PacienteEntity;
import br.com.PrevDent.PrevDent.adapter.repository.mapper.ConsultaMapper;
import br.com.PrevDent.PrevDent.adapter.repository.mapper.DentistaMapper;
import br.com.PrevDent.PrevDent.adapter.repository.mapper.DiagnosticoMapper;
import br.com.PrevDent.PrevDent.adapter.repository.mapper.PacienteMapper;
import br.com.PrevDent.PrevDent.domain.exception.ConsultaNotFoudException;
import br.com.PrevDent.PrevDent.domain.model.Consulta;
import br.com.PrevDent.PrevDent.usecase.ports.out.ConsultaPortOut;
import br.com.PrevDent.PrevDent.usecase.ports.out.DentistaPortOut;
import br.com.PrevDent.PrevDent.usecase.ports.out.DiagnosticoPortOut;
import br.com.PrevDent.PrevDent.usecase.ports.out.PacientePortOut;
import br.com.PrevDent.PrevDent.usecase.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConsultaServiceImpl implements ConsultaService {

    @Autowired
    private ConsultaPortOut consultaPortOut;

    @Autowired
    private ConsultaMapper consultaMapper;

    @Autowired
    private PacienteMapper pacienteMapper;

    @Autowired
    private DentistaMapper dentistaMapper;

    @Autowired
    private PacientePortOut pacientePortOut;

    @Autowired
    private DentistaPortOut dentistaPortOut;

    @Autowired
    private DiagnosticoPortOut diagnosticoPortOut;

    @Autowired
    private DiagnosticoMapper diagnosticoMapper;

    @Override
    public void cadastrarConsulta(Consulta consulta) {

        PacienteEntity paciente = pacientePortOut.findByCpf(consulta.getPaciente().getCpf());
        consulta.setPaciente(pacienteMapper.converterPaciente(paciente));

        DentistaEntity dentista = dentistaPortOut.findByDocumento(consulta.getDentista().getDocumento());
        consulta.setDentista(dentistaMapper.converteDentista(dentista));

        DiagnosticoEntity diagnotico = diagnosticoPortOut.findById(consulta.getDiagnostico().getIdDiagnostico()).get();
        consulta.setDiagnostico(diagnosticoMapper.converteDiagnostico(diagnotico));

        ConsultaEntity consultaEntity = consultaMapper.converteConsultaEntity(consulta);

        consultaPortOut.save(consultaEntity);
    }

    @Override
    public List<Consulta> listarConsultas() {

        List<ConsultaEntity> consultasExistentes = consultaPortOut.findAll();

        List<Consulta> consultas = new ArrayList<>();

        for (ConsultaEntity consultaEntity : consultasExistentes) {

            Consulta consulta = consultaMapper.converteConsulta(consultaEntity);

            consultas.add(consulta);
        }

        return consultas;
    }

    @Override
    public Optional<Consulta> atualizarConsulta(String id, Consulta consulta) {
        Optional<ConsultaEntity> consultaExistente = consultaPortOut.findById(id);

        if (consultaExistente.isPresent()) {

            ConsultaEntity consultaParaAtualizar = consultaExistente.get();
            consultaMapper.atualizarConsultaEntity(consultaParaAtualizar, consulta);

            ConsultaEntity consultaAtualizada = consultaPortOut.save(consultaParaAtualizar);
            Consulta consultaAtualizadaDto = consultaMapper.converterAtualizacaoDaConsulta(consultaAtualizada);

            return Optional.of(consultaAtualizadaDto);

        } else {
            return Optional.empty();
        }
    }

    @Override
    public boolean excluirConsulta(String id) {
        Optional<ConsultaEntity> consultaExistente = consultaPortOut.findById(id);

        if (consultaExistente.isPresent()) {

            consultaPortOut.deleteById(id);

            return true;

        } else {
            return false;
        }
    }

    @Override
    public Consulta buscarConsulta(String id) {
        Optional<ConsultaEntity> consultaDetalhada = consultaPortOut.findById(id);

        if(consultaDetalhada.isPresent()){

            Consulta consulta = consultaMapper.converteConsulta(consultaDetalhada.get());

            return consulta;

        } else {
            throw new ConsultaNotFoudException();
        }
    }

}
