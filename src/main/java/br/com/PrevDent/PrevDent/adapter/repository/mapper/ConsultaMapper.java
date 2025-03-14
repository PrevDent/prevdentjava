package br.com.PrevDent.PrevDent.adapter.repository.mapper;

import br.com.PrevDent.PrevDent.adapter.repository.entity.ConsultaEntity;
import br.com.PrevDent.PrevDent.domain.model.Consulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultaMapper {

    @Autowired
    private PacienteMapper pacienteMapper;

    @Autowired
    private DentistaMapper dentistaMapper;

   @Autowired
    private DiagnosticoMapper diagnosticoMapper;

    public ConsultaEntity converteConsultaEntity(Consulta consulta) {
        ConsultaEntity consultaEntity = new ConsultaEntity();
        consultaEntity.setIdConsulta(consulta.getIdConsulta());
        consultaEntity.setData(consulta.getData());
        consultaEntity.setTipoTratamento(consulta.getTipoTratamento());


        if (consulta.getPaciente() != null) {
            consultaEntity.setPaciente(pacienteMapper.converterPacienteEntity(consulta.getPaciente()));
        }

        if (consulta.getDentista() != null) {
            consultaEntity.setDentista(dentistaMapper.converteDentistaEntity(consulta.getDentista()));
        }

        if (consulta.getDiagnostico() != null) {
            consultaEntity.setDiagnostico(diagnosticoMapper.converteDiagnosticoEntity(consulta.getDiagnostico()));
        }


        return consultaEntity;
    }

    public Consulta converteConsulta(ConsultaEntity consultaEntity) {
        Consulta consulta = new Consulta();
        consulta.setIdConsulta(consultaEntity.getIdConsulta());
        consulta.setData(consultaEntity.getData());
        consulta.setTipoTratamento(consultaEntity.getTipoTratamento());


        consulta.setPaciente(pacienteMapper.converterPaciente(consultaEntity.getPaciente()));
        consulta.setDentista(dentistaMapper.converteDentista(consultaEntity.getDentista()));
        consulta.setDiagnostico(diagnosticoMapper.converteDiagnostico(consultaEntity.getDiagnostico()));

        return consulta;
    }


    public Consulta converterAtualizacaoDaConsulta(ConsultaEntity consultaEntity) {
        Consulta consulta = new Consulta();
        consulta.setData(consultaEntity.getData());
        consulta.setTipoTratamento(consultaEntity.getTipoTratamento());
        return consulta;
    }

    // Atualizar ConsultaEntity com dados de Consulta
    public void atualizarConsultaEntity(ConsultaEntity consultaEntity, Consulta consulta) {
        consultaEntity.setData(consulta.getData());
        consultaEntity.setTipoTratamento(consulta.getTipoTratamento());

    }
}
