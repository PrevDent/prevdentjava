package br.com.PrevDent.PrevDent.infra.listener;


import br.com.PrevDent.PrevDent.domain.model.Consulta;
import br.com.PrevDent.PrevDent.infra.config.RabbitMQConstantes;
import br.com.PrevDent.PrevDent.usecase.service.serviceImpl.ConsultaServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CadastroConsultaListener {

    @Autowired
    private ConsultaServiceImpl consultaService;

    @RabbitListener(queues = RabbitMQConstantes.QUEUE_CADASTRO_CONSULTA)
    public void consumidorCadastroConsulta(Consulta consulta) {

        log.info("Recebido evento de nova consulta via RabbitMQ: {}", consulta);

        consultaService.cadastrarConsulta(consulta);
    }
}
