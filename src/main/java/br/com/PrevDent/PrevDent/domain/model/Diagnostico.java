package br.com.PrevDent.PrevDent.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Diagnostico {

    private String idDiagnostico;
    private String descricao;
    private String recomendacao;

}
