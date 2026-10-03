package br.edu.infnet.al.integracao_service.mensageria;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class JogoEventConsumer {

    @RabbitListener(queues = RabbitMQConfig.FILA_JOGO_CADASTRADO)
    public void consumirEventoJogoCadastrado(String mensagem) {
        System.out.println(">>> [RABBITMQ - INTEGRACAO SERVICE] Evento assíncrono recebido: " + mensagem);
    }
}