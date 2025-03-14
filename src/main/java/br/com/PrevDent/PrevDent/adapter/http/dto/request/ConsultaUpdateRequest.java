package br.com.PrevDent.PrevDent.adapter.http.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsultaUpdateRequest {

    @JsonIgnoreProperties
    private PacienteCreateRequest paciente;

    @JsonIgnoreProperties
    private DentistaCreatRequest dentista;

    @JsonProperty("data_consulta")
    @NotNull(message = "A data da consulta é obrigatória.")
    private Date data;

    @JsonProperty("tipo_tratamento")
    @NotBlank(message = "O tipo de tratamento é obrigatório.")
    private String tipoTratamento;


    @JsonIgnoreProperties
    private DiagnosticoCreateDto diagnostico;

}
