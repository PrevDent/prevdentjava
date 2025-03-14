package br.com.PrevDent.PrevDent.domain.service;

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
import br.com.PrevDent.PrevDent.domain.model.Dentista;
import br.com.PrevDent.PrevDent.domain.model.Diagnostico;
import br.com.PrevDent.PrevDent.domain.model.Paciente;
import br.com.PrevDent.PrevDent.usecase.ports.out.ConsultaPortOut;
import br.com.PrevDent.PrevDent.usecase.ports.out.DentistaPortOut;
import br.com.PrevDent.PrevDent.usecase.ports.out.DiagnosticoPortOut;
import br.com.PrevDent.PrevDent.usecase.ports.out.PacientePortOut;
import br.com.PrevDent.PrevDent.usecase.service.serviceImpl.ConsultaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ConsultaServiceImplTest {

    @InjectMocks
    private ConsultaServiceImpl consultaService;

    @Mock
    private ConsultaPortOut consultaPortOut;

    @Mock
    private ConsultaMapper consultaMapper;

    @Mock
    private PacienteMapper pacienteMapper;

    @Mock
    private DentistaMapper dentistaMapper;

    @Mock
    private PacientePortOut pacientePortOut;

    @Mock
    private DentistaPortOut dentistaPortOut;

    @Mock
    private DiagnosticoPortOut diagnosticoPortOut;

    @Mock
    private DiagnosticoMapper diagnosticoMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCadastrarConsulta() {
        // Arrange
        Consulta consulta = new Consulta();
        PacienteEntity pacienteEntity = new PacienteEntity();
        DentistaEntity dentistaEntity = new DentistaEntity();
        DiagnosticoEntity diagnosticoEntity = new DiagnosticoEntity();
        ConsultaEntity consultaEntity = new ConsultaEntity();

        Paciente paciente = new Paciente();
        paciente.setCpf("12345678901");
        consulta.setPaciente(paciente);

        Dentista dentista = new Dentista();
        dentista.setDocumento("123456789");
        consulta.setDentista(dentista);


        Diagnostico diagnostico = new Diagnostico();
        diagnostico.setIdDiagnostico("diagnostico123");
        consulta.setDiagnostico(diagnostico);

        // Simular as interações do Mock
        when(pacientePortOut.findByCpf(anyString())).thenReturn(pacienteEntity);
        when(pacienteMapper.converterPaciente(any())).thenReturn(consulta.getPaciente());
        when(dentistaPortOut.findByDocumento(anyString())).thenReturn(dentistaEntity);
        when(dentistaMapper.converteDentista(any())).thenReturn(consulta.getDentista());
        when(diagnosticoPortOut.findById(anyString())).thenReturn(Optional.of(diagnosticoEntity));
        when(diagnosticoMapper.converteDiagnostico(any())).thenReturn(consulta.getDiagnostico());
        when(consultaMapper.converteConsultaEntity(any())).thenReturn(consultaEntity);

        // Act
        consultaService.cadastrarConsulta(consulta);

        // Assert
        verify(consultaPortOut, times(1)).save(consultaEntity);
    }

    @Test
    void testListarConsultas() {
        // Arrange
        List<ConsultaEntity> consultaEntities = new ArrayList<>();
        ConsultaEntity consultaEntity = new ConsultaEntity();
        consultaEntities.add(consultaEntity);

        when(consultaPortOut.findAll()).thenReturn(consultaEntities);
        when(consultaMapper.converteConsulta(any())).thenReturn(new Consulta());

        // Act
        List<Consulta> consultas = consultaService.listarConsultas();

        // Assert
        assertFalse(consultas.isEmpty());
        verify(consultaPortOut, times(1)).findAll();
    }

    @Test
    void testAtualizarConsulta() {
        // Arrange
        String id = "1";
        Consulta consulta = new Consulta();
        ConsultaEntity consultaEntity = new ConsultaEntity();

        when(consultaPortOut.findById(id)).thenReturn(Optional.of(consultaEntity));
        when(consultaPortOut.save(any())).thenReturn(consultaEntity);
        when(consultaMapper.converterAtualizacaoDaConsulta(any())).thenReturn(consulta);

        // Act
        Optional<Consulta> consultaAtualizada = consultaService.atualizarConsulta(id, consulta);

        // Assert
        assertTrue(consultaAtualizada.isPresent());
        verify(consultaPortOut, times(1)).findById(id);
        verify(consultaPortOut, times(1)).save(any());
    }

    @Test
    void testExcluirConsulta() {
        // Arrange
        String id = "1";
        when(consultaPortOut.findById(id)).thenReturn(Optional.of(new ConsultaEntity()));

        // Act
        boolean result = consultaService.excluirConsulta(id);

        // Assert
        assertTrue(result);
        verify(consultaPortOut, times(1)).deleteById(id);
    }

    @Test
    void testBuscarConsultaSuccess() {
        // Arrange
        String id = "1";
        ConsultaEntity consultaEntity = new ConsultaEntity();
        when(consultaPortOut.findById(id)).thenReturn(Optional.of(consultaEntity));
        when(consultaMapper.converteConsulta(consultaEntity)).thenReturn(new Consulta());

        // Act
        Consulta consulta = consultaService.buscarConsulta(id);

        // Assert
        assertNotNull(consulta);
        verify(consultaPortOut, times(1)).findById(id);
    }

    @Test
    void testBuscarConsultaNotFound() {
        // Arrange
        String id = "1";
        when(consultaPortOut.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ConsultaNotFoudException.class, () -> consultaService.buscarConsulta(id));
        verify(consultaPortOut, times(1)).findById(id);
    }

}