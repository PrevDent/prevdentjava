package br.com.PrevDent.PrevDent.usecase.ports.out;

import br.com.PrevDent.PrevDent.adapter.repository.entity.DiagnosticoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiagnosticoPortOut extends JpaRepository<DiagnosticoEntity, String> {

    public DiagnosticoEntity findByIdDiagnostico(String idDiagnostico);
}
