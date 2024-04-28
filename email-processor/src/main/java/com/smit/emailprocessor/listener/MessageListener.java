package com.smit.emailprocessor.listener;

import com.smit.emailprocessor.config.type.ProcedureQueue;
import com.smit.emailprocessor.entity.Procedure;
import com.smit.emailprocessor.service.EmailService;
import com.smit.emailprocessor.service.ProcedureMessagingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class MessageListener {

    private static final Logger logger = LoggerFactory.getLogger(MessageListener.class);

    @Autowired
    EmailService emailService;

    @Autowired
    ProcedureMessagingService procedureMessagingService;

    @RabbitListener(queues = ProcedureQueue.PROCEDURE_EMAIL_QUEUE)
    public void receiveProcedureStartMessage(Procedure procedure) {
        logger.info(String.format("Received from queue %s -> %s", ProcedureQueue.PROCEDURE_EMAIL_QUEUE, procedure.toString()));

        try {
            emailService.sendEmail();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        procedureMessagingService.sendProcedureEmailConfirmationMessage(procedure.getId().toString());
    }
}
