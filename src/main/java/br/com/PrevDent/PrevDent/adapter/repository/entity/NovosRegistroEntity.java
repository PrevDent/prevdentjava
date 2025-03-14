package br.com.PrevDent.PrevDent.adapter.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity(name = "novos_registro")
@Table(name = "tbl_novos_registro")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NovosRegistroEntity {

    @Id
    @Column(name = "id_registro")
    private String idRegistro;

    @Column(name = "tipo", nullable = false)
    private String tipo;

    @Column(name = "ocorrencia", nullable = false)
    private String ocorrencia;

    @Column(name = "intensidade", nullable = false)
    private String intensidade;

    @Column(name = "informacoes_adicionais", nullable = false)
    private String informacoesAdicionais;

}
