package br.com.PrevDent.PrevDent.adapter.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "dentista")
@Table(name = "tbl_Dentista")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DentistaEntity {

    @Id
    @Column(name = "id_dentista")
    private String idDentista;

    @Column(name = "nome_dentista", nullable = false)
    private String nome;

    @Column(name = "documento", nullable = false)
    private String documento;

    @Column(name = "especializacao", nullable = false)
    private String especializacao;

    @OneToMany(mappedBy = "dentista")
    private List<ConsultaEntity> consultas;

}
