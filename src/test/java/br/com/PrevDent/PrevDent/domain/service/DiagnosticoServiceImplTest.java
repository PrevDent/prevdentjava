package br.com.PrevDent.PrevDent.domain.service;

import br.com.PrevDent.PrevDent.adapter.repository.entity.DiagnosticoEntity;
import br.com.PrevDent.PrevDent.adapter.repository.mapper.DiagnosticoMapper;
import br.com.PrevDent.PrevDent.domain.exception.DiagnosticoNotFoundException;
import br.com.PrevDent.PrevDent.domain.model.Diagnostico;
import br.com.PrevDent.PrevDent.usecase.ports.out.DiagnosticoPortOut;
import br.com.PrevDent.PrevDent.usecase.service.serviceImpl.DiagnosticoServiceImpl;
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

class DiagnosticoServiceImplTest {

    @InjectMocks
    private DiagnosticoServiceImpl diagnosticoService;

    @Mock
    private DiagnosticoPortOut diagnosticoPortOut;

    @Mock
    private DiagnosticoMapper diagnosticoMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCadastrarDiagnostico() {
        // Arrange
        Diagnostico diagnostico = new Diagnostico();
        DiagnosticoEntity diagnosticoEntity = new DiagnosticoEntity();

        when(diagnosticoMapper.converteDiagnosticoEntity(diagnostico)).thenReturn(diagnosticoEntity);

        // Act
        diagnosticoService.cadastrarDiagnostico(diagnostico);

        // Assert
        assertNotNull(diagnostico.getIdDiagnostico()); // Verificar se o UUID foi gerado
        verify(diagnosticoPortOut, times(1)).save(diagnosticoEntity);
    }

    @Test
    void testListarDiagnosticos() {
        // Arrange
        List<DiagnosticoEntity> diagnosticoEntities = new ArrayList<>();
        DiagnosticoEntity diagnosticoEntity = new DiagnosticoEntity();
        diagnosticoEntities.add(diagnosticoEntity);

        List<Diagnostico> diagnosticosMock = new ArrayList<>();
        Diagnostico diagnostico = new Diagnostico();
        diagnosticosMock.add(diagnostico);

        when(diagnosticoPortOut.findAll()).thenReturn(diagnosticoEntities);
        when(diagnosticoMapper.converteDiagnostico(diagnosticoEntity)).thenReturn(diagnostico);

        // Act
        List<Diagnostico> diagnosticos = diagnosticoService.listarDiagnosticos();

        // Assert
        assertFalse(diagnosticos.isEmpty());
        verify(diagnosticoPortOut, times(1)).findAll();
        verify(diagnosticoMapper, times(1)).converteDiagnostico(diagnosticoEntity);
    }

    @Test
    void testAtualizarDiagnosticoSucesso() {
        // Arrange
        String id = "1";
        Diagnostico diagnostico = new Diagnostico();
        DiagnosticoEntity diagnosticoEntity = new DiagnosticoEntity();

        when(diagnosticoPortOut.findById(id)).thenReturn(Optional.of(diagnosticoEntity));
        when(diagnosticoPortOut.save(any())).thenReturn(diagnosticoEntity);
        when(diagnosticoMapper.converterAtualizacaoDoDiagnostico(diagnosticoEntity)).thenReturn(diagnostico);

        // Act
        Optional<Diagnostico> diagnosticoAtualizado = diagnosticoService.atualizarDiagnostico(id, diagnostico);

        // Assert
        assertTrue(diagnosticoAtualizado.isPresent());
        verify(diagnosticoPortOut, times(1)).findById(id);
        verify(diagnosticoPortOut, times(1)).save(any());
    }

    @Test
    void testAtualizarDiagnosticoNaoEncontrado() {
        // Arrange
        String id = "1";
        Diagnostico diagnostico = new Diagnostico();

        when(diagnosticoPortOut.findById(id)).thenReturn(Optional.empty());

        // Act
        Optional<Diagnostico> diagnosticoAtualizado = diagnosticoService.atualizarDiagnostico(id, diagnostico);

        // Assert
        assertFalse(diagnosticoAtualizado.isPresent());
        verify(diagnosticoPortOut, times(1)).findById(id);
        verify(diagnosticoPortOut, never()).save(any());
    }

    @Test
    void testExcluirDiagnosticoSucesso() {
        // Arrange
        String id = "1";
        DiagnosticoEntity diagnosticoEntity = new DiagnosticoEntity();

        when(diagnosticoPortOut.findById(id)).thenReturn(Optional.of(diagnosticoEntity));

        // Act
        boolean resultado = diagnosticoService.excluirDiagnostico(id);

        // Assert
        assertTrue(resultado);
        verify(diagnosticoPortOut, times(1)).deleteById(id);
    }

    @Test
    void testExcluirDiagnosticoNaoEncontrado() {
        // Arrange
        String id = "1";

        when(diagnosticoPortOut.findById(id)).thenReturn(Optional.empty());

        // Act
        boolean resultado = diagnosticoService.excluirDiagnostico(id);

        // Assert
        assertFalse(resultado);
        verify(diagnosticoPortOut, times(1)).findById(id);
        verify(diagnosticoPortOut, never()).deleteById(id);
    }

    @Test
    void testBuscarDiagnosticoSucesso() {
        // Arrange
        String id = "1";
        DiagnosticoEntity diagnosticoEntity = new DiagnosticoEntity();
        Diagnostico diagnostico = new Diagnostico();

        when(diagnosticoPortOut.findById(id)).thenReturn(Optional.of(diagnosticoEntity));
        when(diagnosticoMapper.converteDiagnostico(diagnosticoEntity)).thenReturn(diagnostico);

        // Act
        Diagnostico diagnosticoEncontrado = diagnosticoService.buscarDiagnostico(id);

        // Assert
        assertNotNull(diagnosticoEncontrado);
        verify(diagnosticoPortOut, times(1)).findById(id);
    }

    @Test
    void testBuscarDiagnosticoNaoEncontrado() {
        // Arrange
        String id = "1";

        when(diagnosticoPortOut.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(DiagnosticoNotFoundException.class, () -> diagnosticoService.buscarDiagnostico(id));
        verify(diagnosticoPortOut, times(1)).findById(id);
    }
}
