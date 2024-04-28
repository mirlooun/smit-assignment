package com.smit.emailprocessor.service;

import com.smit.emailprocessor.common.MessagingService;
import com.smit.emailprocessor.config.type.ProcedureQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ProcedureMessagingService extends MessagingService {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    private static final String routingKey = ProcedureQueue.PROCEDURE_EMAIL_CONFIRMATION_QUEUE + "-routing-key";

    public void sendProcedureEmailConfirmationMessage(String id) {
        sendMessage(exchange, routingKey, id);
    }
}
