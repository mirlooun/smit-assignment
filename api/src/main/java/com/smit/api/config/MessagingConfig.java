package com.smit.api.config;

import com.smit.api.config.type.ProcedureQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {

    private static final Logger logger = LoggerFactory.getLogger(MessagingConfig.class);

    @Value("${rabbitmq.exchange.name}")
    private String topicExchange;

    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange(topicExchange);
    }

    @Bean
    Queue procedureEmailSendQueue() {
        return new Queue(ProcedureQueue.PROCEDURE_EMAIL_QUEUE, false);
    }

    @Bean
    Queue procedureConfirmationQueue() {
        return new Queue(ProcedureQueue.PROCEDURE_EMAIL_CONFIRMATION_QUEUE, false);
    }

    @Bean
    public Binding bindingA() {
        return BindingBuilder
                .bind(procedureEmailSendQueue())
                .to(topicExchange()).with(ProcedureQueue.PROCEDURE_EMAIL_QUEUE + "-routing-key");
    }

    @Bean
    public Binding bindingB() {
        return BindingBuilder
                .bind(procedureConfirmationQueue())
                .to(topicExchange()).with(ProcedureQueue.PROCEDURE_EMAIL_CONFIRMATION_QUEUE + "-routing-key");
    }

    // Support json
    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}
