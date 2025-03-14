package br.com.PrevDent.PrevDent.adapter.repository.entity;

import br.com.PrevDent.PrevDent.domain.user.PacienteUserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity(name = "paciente")
@Table(name = "tbl_paciente")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PacienteEntity implements UserDetails {

    @Id
    @Column(name = "id_paciente")
    private String idPaciente;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "cpf", nullable = false )
    private String cpf;

    @Column(name = "data_nascimento", nullable = false)
    private String dataNascimento;

    @Column(name = "senha", nullable = false)
    private String senha;

    private PacienteUserRole role;

    @OneToMany(mappedBy = "paciente")
    private List<ConsultaEntity> consultas;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == PacienteUserRole.Admin)
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return cpf;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
