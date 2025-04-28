package br.com.PrevDent.PrevDent.infra.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConstantes {

    public static final String QUEUE_CADASTRO_CONSULTA = "filaCadastroConsulta";

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Queue queueCadastroConsulta() {
        return new Queue(QUEUE_CADASTRO_CONSULTA, true);
    }

}
