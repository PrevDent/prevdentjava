package br.com.PrevDent.PrevDent.adapter.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity(name = "consulta")
@Table(name = "tbl_consulta")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ConsultaEntity {

    @Id
    @Column(name = "consulta_id")
    private String idConsulta;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private PacienteEntity paciente;

    @ManyToOne
    @JoinColumn(name = "dentista_id")
    private DentistaEntity dentista;

    @Column(name = "data_consulta")
    private Date data;

    @Column(name = "tipo_tratamento")
    private String tipoTratamento;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "diagnostico_id", referencedColumnName = "diagnostico_id")
    private DiagnosticoEntity diagnostico;

}