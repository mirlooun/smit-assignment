package com.smit.api.service;

import com.smit.api.common.MessagingService;
import com.smit.api.config.type.ProcedureQueue;
import com.smit.api.entity.Procedure;
import com.smit.api.repository.IProcedureRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ProcedureMessagingService extends MessagingService {

    private static final Logger logger = LoggerFactory.getLogger(ProcedureMessagingService.class);

    @Autowired
    IProcedureRepository procedureRepository;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    private final String routingKey = ProcedureQueue.PROCEDURE_EMAIL_QUEUE + "-routing-key";

    public void sendConfirmationEmail(Procedure procedure) {
        logger.info(String.format("Sending confirmation email to queue for procedure -> %s", procedure.toString()));
        sendMessage(exchange, routingKey, procedure);
    }

    @RabbitListener(queues = ProcedureQueue.PROCEDURE_EMAIL_CONFIRMATION_QUEUE)
    public void handleProcedureEmailSendConfirmation(String id) {
        logger.info(String.format("Received message from queue %s -> %s", ProcedureQueue.PROCEDURE_EMAIL_CONFIRMATION_QUEUE, id));
        // TODO: receive procedureId and update isEmailConfirmed field
        Long parsedId = Long.parseLong(id);

        Optional<Procedure> procedure = procedureRepository.findById(parsedId);

        if (procedure.isPresent()) {
            Procedure p = procedure.get();
            p.setEmailConfirmationSent(true);
            procedureRepository.save(p);
            logger.info(String.format("Procedure confirmation email sent for procedure %d", p.getId()));
        } else {
            logger.error(String.format("Received confirmation email event for unknown procedure -> %s", id));
        }
    }
}
