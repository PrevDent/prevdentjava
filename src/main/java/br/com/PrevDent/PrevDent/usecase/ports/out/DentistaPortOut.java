package br.com.PrevDent.PrevDent.usecase.ports.out;

import br.com.PrevDent.PrevDent.adapter.repository.entity.DentistaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DentistaPortOut extends JpaRepository<DentistaEntity, String> {

    public DentistaEntity findByDocumento(String documento);
}
