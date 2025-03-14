package br.com.PrevDent.PrevDent.usecase.service.serviceImpl;

import br.com.PrevDent.PrevDent.adapter.repository.entity.PacienteEntity;
import br.com.PrevDent.PrevDent.adapter.repository.mapper.PacienteMapper;
import br.com.PrevDent.PrevDent.domain.exception.PacienteNotFoundException;
import br.com.PrevDent.PrevDent.domain.model.Paciente;
import br.com.PrevDent.PrevDent.infra.security.SecurityConfiguration;
import br.com.PrevDent.PrevDent.infra.security.TokenService;
import br.com.PrevDent.PrevDent.usecase.ports.out.PacientePortOut;
import br.com.PrevDent.PrevDent.usecase.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PacienteServiceImpl implements PacienteService {

    @Autowired
    private PacientePortOut pacientePortOut;

    @Autowired
    private PacienteMapper pacienteMapper;

    @Autowired
    private SecurityConfiguration securityConfig;

    @Autowired
    private TokenService tokenService;


    @Override
    public void cadastarPaciente(Paciente paciente) {

        if(pacientePortOut.findByCpf(paciente.getCpf()) != null){
            throw new RuntimeException("CPF do Paciente já cadastrado");
        }

        PacienteEntity pacienteEntity = pacienteMapper.converterPacienteEntity(paciente);

        pacientePortOut.save(pacienteEntity);
    }

    @Override
    public List<Paciente> listarPacientes() {
        List<PacienteEntity> pacienteExistente = pacientePortOut.findAll();

        List<Paciente> pacientes = new ArrayList<>();

        for (PacienteEntity pacienteEntity : pacienteExistente) {

            Paciente paciente = pacienteMapper.converterPaciente(pacienteEntity);

            pacientes.add(paciente);
        }

        return pacientes;
    }


    @Override
    public Optional<Paciente> atualizarPaciente(String id, Paciente paciente) {

        Optional<PacienteEntity> pacienteExistente = pacientePortOut.findById(id);

        if (pacienteExistente.isPresent()) {

            PacienteEntity autualizar = pacienteExistente.get();
            pacienteMapper.atualizarPacienteEntity(autualizar, paciente);

            PacienteEntity pacienteAtualizada = pacientePortOut.save(autualizar);
            Paciente pacienteAtualizado = pacienteMapper.converterAtualizacaoDoPaciente(pacienteAtualizada);

            return Optional.of(pacienteAtualizado);

        } else {
            return Optional.empty();
        }

    }

    @Override
    public boolean excluirPaciente(String id) {

        Optional<PacienteEntity> pacienteExistente = pacientePortOut.findById(id);

        if (pacienteExistente.isPresent()) {

            pacientePortOut.deleteById(id);

            return true;

        } else {
            return false;
        }
    }

    @Override
    public Paciente buscarPaciente(String id) {
        Optional<PacienteEntity> pacienteDetalhado = pacientePortOut.findById(id);

        if(pacienteDetalhado.isPresent()){

            Paciente paciente = pacienteMapper.converterPaciente(pacienteDetalhado.get());

            return paciente;

        } else {
            throw new PacienteNotFoundException();
        }
    }


    @Override
    public String ValidarLogin(String cpf, String senhaDigitada) {
        PacienteEntity pacienteEntity = pacientePortOut.findByCpf(cpf);

        if (pacienteEntity == null) {
            throw new UsernameNotFoundException("Paciente não encontrado");
        }

        if (!securityConfig.passwordEncoder().matches(senhaDigitada, pacienteEntity.getSenha())) {
            throw new BadCredentialsException("Senha incorreta");
        }

        return tokenService.gerarToken(pacienteEntity);
    }



}
