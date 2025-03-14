package br.com.PrevDent.PrevDent.usecase.ports.out;

import br.com.PrevDent.PrevDent.adapter.repository.entity.NovosRegistroEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NovosRegistroPortOut extends JpaRepository<NovosRegistroEntity, String> {


}
