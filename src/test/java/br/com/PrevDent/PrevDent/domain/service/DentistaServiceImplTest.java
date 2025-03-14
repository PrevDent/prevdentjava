package br.com.PrevDent.PrevDent.domain.service;

import br.com.PrevDent.PrevDent.adapter.repository.entity.DentistaEntity;
import br.com.PrevDent.PrevDent.adapter.repository.mapper.DentistaMapper;
import br.com.PrevDent.PrevDent.domain.exception.DentistaNotFoudException;
import br.com.PrevDent.PrevDent.domain.model.Dentista;
import br.com.PrevDent.PrevDent.usecase.ports.out.DentistaPortOut;
import br.com.PrevDent.PrevDent.usecase.service.serviceImpl.DentistaServiceImpl;
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

class DentistaServiceImplTest {

    @InjectMocks
    private DentistaServiceImpl dentistaService;

    @Mock
    private DentistaPortOut dentistaPortOut;

    @Mock
    private DentistaMapper dentistaMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCadastrarDentista() {
        // Arrange
        Dentista dentista = new Dentista();
        DentistaEntity dentistaEntity = new DentistaEntity();

        when(dentistaMapper.converteDentistaEntity(dentista)).thenReturn(dentistaEntity);

        // Act
        dentistaService.cadastrarDentista(dentista);

        // Assert
        verify(dentistaPortOut, times(1)).save(dentistaEntity);
    }

    @Test
    void testListarDentistas() {
        // Arrange
        List<DentistaEntity> dentistaEntities = new ArrayList<>();
        DentistaEntity dentistaEntity = new DentistaEntity();
        dentistaEntities.add(dentistaEntity);

        List<Dentista> dentistasMock = new ArrayList<>();
        Dentista dentista = new Dentista();
        dentistasMock.add(dentista);

        when(dentistaPortOut.findAll()).thenReturn(dentistaEntities);
        when(dentistaMapper.converteDentista(dentistaEntity)).thenReturn(dentista);

        // Act
        List<Dentista> dentistas = dentistaService.listarDentistas();

        // Assert
        assertFalse(dentistas.isEmpty());
        verify(dentistaPortOut, times(1)).findAll();
        verify(dentistaMapper, times(1)).converteDentista(dentistaEntity);
    }

    @Test
    void testAtualizarDentistaSucesso() {
        // Arrange
        String id = "1";
        Dentista dentista = new Dentista();
        DentistaEntity dentistaEntity = new DentistaEntity();

        when(dentistaPortOut.findById(id)).thenReturn(Optional.of(dentistaEntity));
        when(dentistaPortOut.save(any())).thenReturn(dentistaEntity);
        when(dentistaMapper.converterAtualizacaoDoDentista(dentistaEntity)).thenReturn(dentista);

        // Act
        Optional<Dentista> dentistaAtualizado = dentistaService.atualizarDentista(id, dentista);

        // Assert
        assertTrue(dentistaAtualizado.isPresent());
        verify(dentistaPortOut, times(1)).findById(id);
        verify(dentistaPortOut, times(1)).save(any());
    }

    @Test
    void testAtualizarDentistaNaoEncontrado() {
        // Arrange
        String id = "1";
        Dentista dentista = new Dentista();

        when(dentistaPortOut.findById(id)).thenReturn(Optional.empty());

        // Act
        Optional<Dentista> dentistaAtualizado = dentistaService.atualizarDentista(id, dentista);

        // Assert
        assertFalse(dentistaAtualizado.isPresent());
        verify(dentistaPortOut, times(1)).findById(id);
        verify(dentistaPortOut, never()).save(any());
    }

    @Test
    void testRemoverDentistaSucesso() {
        // Arrange
        String id = "1";
        DentistaEntity dentistaEntity = new DentistaEntity();

        when(dentistaPortOut.findById(id)).thenReturn(Optional.of(dentistaEntity));

        // Act
        boolean resultado = dentistaService.removerDentista(id);

        // Assert
        assertTrue(resultado);
        verify(dentistaPortOut, times(1)).deleteById(id);
    }

    @Test
    void testRemoverDentistaNaoEncontrado() {
        // Arrange
        String id = "1";

        when(dentistaPortOut.findById(id)).thenReturn(Optional.empty());

        // Act
        boolean resultado = dentistaService.removerDentista(id);

        // Assert
        assertFalse(resultado);
        verify(dentistaPortOut, times(1)).findById(id);
        verify(dentistaPortOut, never()).deleteById(id);
    }

    @Test
    void testBuscarDentistaSucesso() {
        // Arrange
        String id = "1";
        DentistaEntity dentistaEntity = new DentistaEntity();
        Dentista dentista = new Dentista();

        when(dentistaPortOut.findById(id)).thenReturn(Optional.of(dentistaEntity));
        when(dentistaMapper.converteDentista(dentistaEntity)).thenReturn(dentista);

        // Act
        Dentista dentistaEncontrado = dentistaService.buscarDentista(id);

        // Assert
        assertNotNull(dentistaEncontrado);
        verify(dentistaPortOut, times(1)).findById(id);
    }

    @Test
    void testBuscarDentistaNaoEncontrado() {
        // Arrange
        String id = "1";

        when(dentistaPortOut.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(DentistaNotFoudException.class, () -> dentistaService.buscarDentista(id));
        verify(dentistaPortOut, times(1)).findById(id);
    }
}
