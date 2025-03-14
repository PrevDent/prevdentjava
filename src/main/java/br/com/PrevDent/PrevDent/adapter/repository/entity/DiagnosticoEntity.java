package br.com.PrevDent.PrevDent.adapter.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "diagnostico")
@Table(name = "T_PD_CH_DIAGNOSTICOS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiagnosticoEntity {

    @Id
    @Column(name = "diagnostico_id")
    private String idDiagnostico;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "recomendacao")
    private String recomendacao;

    @OneToOne(mappedBy = "diagnostico")
    private ConsultaEntity consulta;
}
