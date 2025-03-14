package br.com.PrevDent.PrevDent.adapter.http.dto.mapper;


import br.com.PrevDent.PrevDent.adapter.http.dto.request.ConsultaCreatRequest;
import br.com.PrevDent.PrevDent.adapter.http.dto.request.ConsultaUpdateRequest;
import br.com.PrevDent.PrevDent.adapter.http.dto.response.ConsultaListaResponse;
import br.com.PrevDent.PrevDent.domain.model.Consulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultaDtoMapper {

    @Autowired
    private PacienteDtoMapper pacienteDtoMapper;

    @Autowired
    private DentistaDtoMapper dentistaDtoMapper;

    @Autowired
    private DiagnosticoDtoMapper diagnosticoDtoMapper;

    public Consulta criandoDtoParaConsulta(ConsultaCreatRequest consultaCreatRequest) {
        Consulta consulta = new Consulta();
        consulta.setData(consultaCreatRequest.getData());
        consulta.setTipoTratamento(consultaCreatRequest.getTipoTratamento());

        consulta.setPaciente(pacienteDtoMapper.converterPacienteDto(consultaCreatRequest.getPaciente()));
        consulta.setDentista(dentistaDtoMapper.createDentista(consultaCreatRequest.getDentista()));

        consulta.setDiagnostico(diagnosticoDtoMapper.converteCreateDiagnostico(consultaCreatRequest.getDiagnostico()));
        return consulta;
    }

    public Consulta converterConsultaUpdateteDto(ConsultaUpdateRequest consultaUpdateRequest) {
        Consulta consulta = new Consulta();
        consulta.setData(consultaUpdateRequest.getData());
        consulta.setTipoTratamento(consultaUpdateRequest.getTipoTratamento());
        return consulta;
    }

    public  Consulta listarConsultaDto(ConsultaListaResponse consultaListaResponse) {
        Consulta consulta = new Consulta();
        consulta.setIdConsulta(consultaListaResponse.getIdConsulta());
        consulta.setPaciente(pacienteDtoMapper.converterPacienteDto(consultaListaResponse.getPaciente()));
        consulta.setDentista(dentistaDtoMapper.createDentista(consultaListaResponse.getDentista()));
        consulta.setData(consultaListaResponse.getData());
        return consulta;
    }
}
