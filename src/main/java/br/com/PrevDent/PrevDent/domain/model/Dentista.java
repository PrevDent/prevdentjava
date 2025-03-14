package br.com.PrevDent.PrevDent.domain.model;

import lombok.Data;
import java.util.UUID;


@Data
public class Dentista {

    private String idDentista;
    private String nome;
    private String documento;
    private String especializacao;

    public Dentista() {
        this.idDentista = UUID.randomUUID().toString();
    }
}
