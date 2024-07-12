package com.veloxium.registry.infra.ampq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RegistryAMPQconfig {
    @Bean
    public Jackson2JsonMessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }
    @Bean
    public RabbitTemplate template(ConnectionFactory connectionFactory, Jackson2JsonMessageConverter messageConverter){
        var rabbit = new RabbitTemplate(connectionFactory);
        rabbit.setMessageConverter(messageConverter);
        return rabbit;
    }
    @Bean
    public Queue creatingQueue(){
        //return new Queue("registered.completed",false);
        return QueueBuilder.nonDurable("registered.completed").build();
    }
    @Bean
    public FanoutExchange fanoutExchange(){
        return ExchangeBuilder
                .fanoutExchange("login.ex").build();
    }
    @Bean
    public Binding binding(FanoutExchange exchange){
        return BindingBuilder.bind(creatingQueue()).to(fanoutExchange());
    }
    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory){
        return new RabbitAdmin(connectionFactory);
    }
    @Bean
    public ApplicationListener<ApplicationReadyEvent> startingRabbitAdimin(RabbitAdmin rabbitAdmin){
        return event -> rabbitAdmin.initialize();
    }
}
