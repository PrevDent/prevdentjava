package br.com.PrevDent.PrevDent.usecase.ports.out;

import br.com.PrevDent.PrevDent.adapter.repository.entity.PacienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacientePortOut extends JpaRepository<PacienteEntity, String> {

     PacienteEntity findByCpf(String cpf);
}
