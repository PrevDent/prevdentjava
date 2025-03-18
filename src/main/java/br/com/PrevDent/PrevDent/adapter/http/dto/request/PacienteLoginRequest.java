package br.com.PrevDent.PrevDent.adapter.http.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PacienteLoginRequest {

    @JsonProperty("email")
    @NotBlank(message = "E-mail não pode estar vazio")
    private String email;

    @JsonProperty("senha")
    @NotBlank(message = "Senha não pode estar vazia")
    private String senha;
}
