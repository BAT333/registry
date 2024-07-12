package com.veloxium.registry.infra.ampq;

import com.veloxium.registry.model.DataLoginsDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class UserListener {
    @RabbitListener(queues = "registered.completed")
    public void  receivingMessage(@Payload DataLoginsDTO dto){
        String message = """
           ID: %s
           LOGIN: %s
           PASSWORD: %s
                """.formatted(dto.id(), dto.login(),dto.password());

        System.out.println("RECEBI TAL MENSAGEM "+ message);
    }
}
