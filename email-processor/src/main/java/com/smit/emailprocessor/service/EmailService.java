package com.smit.emailprocessor.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    public String sendEmail() throws InterruptedException {
        logger.info("Sending email");
        Thread.sleep(5000);
        return "Email sent succesfully";
    }
}
