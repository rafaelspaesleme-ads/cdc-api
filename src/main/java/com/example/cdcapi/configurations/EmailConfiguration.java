package com.example.cdcapi.configurations;

import com.example.cdcapi.services.EmailService;
import com.example.cdcapi.services.SmtpEmailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailConfiguration {
    @Bean
    public EmailService emailService() {
        return new SmtpEmailService();
    }
}
