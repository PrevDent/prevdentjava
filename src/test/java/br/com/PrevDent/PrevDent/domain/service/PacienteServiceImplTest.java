package br.com.PrevDent.PrevDent.domain.service;

import br.com.PrevDent.PrevDent.adapter.repository.entity.PacienteEntity;
import br.com.PrevDent.PrevDent.adapter.repository.mapper.PacienteMapper;
import br.com.PrevDent.PrevDent.domain.exception.PacienteNotFoundException;
import br.com.PrevDent.PrevDent.domain.model.Paciente;
import br.com.PrevDent.PrevDent.usecase.ports.out.PacientePortOut;
import br.com.PrevDent.PrevDent.usecase.service.serviceImpl.PacienteServiceImpl;
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

class PacienteServiceImplTest {

    @InjectMocks
    private PacienteServiceImpl pacienteService;

    @Mock
    private PacientePortOut pacientePortOut;

    @Mock
    private PacienteMapper pacienteMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCadastrarPaciente() {
        // Arrange
        Paciente paciente = new Paciente();
        PacienteEntity pacienteEntity = new PacienteEntity();

        when(pacienteMapper.converterPacienteEntity(paciente)).thenReturn(pacienteEntity);

        // Act
        pacienteService.cadastarPaciente(paciente);

        // Assert
        verify(pacientePortOut, times(1)).save(pacienteEntity);
    }

    @Test
    void testListarPacientes() {
        // Arrange
        List<PacienteEntity> pacienteEntities = new ArrayList<>();
        PacienteEntity pacienteEntity = new PacienteEntity();
        pacienteEntities.add(pacienteEntity);

        List<Paciente> pacientesMock = new ArrayList<>();
        Paciente paciente = new Paciente();
        pacientesMock.add(paciente);

        when(pacientePortOut.findAll()).thenReturn(pacienteEntities);
        when(pacienteMapper.converterPaciente(pacienteEntity)).thenReturn(paciente);

        // Act
        List<Paciente> pacientes = pacienteService.listarPacientes();

        // Assert
        assertFalse(pacientes.isEmpty());
        verify(pacientePortOut, times(1)).findAll();
        verify(pacienteMapper, times(1)).converterPaciente(pacienteEntity);
    }

    @Test
    void testAtualizarPacienteSucesso() {
        // Arrange
        String id = "1";
        Paciente paciente = new Paciente();
        PacienteEntity pacienteEntity = new PacienteEntity();

        when(pacientePortOut.findById(id)).thenReturn(Optional.of(pacienteEntity));
        when(pacientePortOut.save(any())).thenReturn(pacienteEntity);
        when(pacienteMapper.converterAtualizacaoDoPaciente(pacienteEntity)).thenReturn(paciente);

        // Act
        Optional<Paciente> pacienteAtualizado = pacienteService.atualizarPaciente(id, paciente);

        // Assert
        assertTrue(pacienteAtualizado.isPresent());
        verify(pacientePortOut, times(1)).findById(id);
        verify(pacientePortOut, times(1)).save(any());
    }

    @Test
    void testAtualizarPacienteNaoEncontrado() {
        // Arrange
        String id = "1";
        Paciente paciente = new Paciente();

        when(pacientePortOut.findById(id)).thenReturn(Optional.empty());

        // Act
        Optional<Paciente> pacienteAtualizado = pacienteService.atualizarPaciente(id, paciente);

        // Assert
        assertFalse(pacienteAtualizado.isPresent());
        verify(pacientePortOut, times(1)).findById(id);
        verify(pacientePortOut, never()).save(any());
    }

    @Test
    void testExcluirPacienteSucesso() {
        // Arrange
        String id = "1";
        PacienteEntity pacienteEntity = new PacienteEntity();

        when(pacientePortOut.findById(id)).thenReturn(Optional.of(pacienteEntity));

        // Act
        boolean resultado = pacienteService.excluirPaciente(id);

        // Assert
        assertTrue(resultado);
        verify(pacientePortOut, times(1)).deleteById(id);
    }

    @Test
    void testExcluirPacienteNaoEncontrado() {
        // Arrange
        String id = "1";

        when(pacientePortOut.findById(id)).thenReturn(Optional.empty());

        // Act
        boolean resultado = pacienteService.excluirPaciente(id);

        // Assert
        assertFalse(resultado);
        verify(pacientePortOut, times(1)).findById(id);
        verify(pacientePortOut, never()).deleteById(id);
    }

    @Test
    void testBuscarPacienteSucesso() {
        // Arrange
        String id = "1";
        PacienteEntity pacienteEntity = new PacienteEntity();
        Paciente paciente = new Paciente();

        when(pacientePortOut.findById(id)).thenReturn(Optional.of(pacienteEntity));
        when(pacienteMapper.converterPaciente(pacienteEntity)).thenReturn(paciente);

        // Act
        Paciente pacienteEncontrado = pacienteService.buscarPaciente(id);

        // Assert
        assertNotNull(pacienteEncontrado);
        verify(pacientePortOut, times(1)).findById(id);
    }

    @Test
    void testBuscarPacienteNaoEncontrado() {
        // Arrange
        String id = "1";

        when(pacientePortOut.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(PacienteNotFoundException.class, () -> pacienteService.buscarPaciente(id));
        verify(pacientePortOut, times(1)).findById(id);
    }
}
