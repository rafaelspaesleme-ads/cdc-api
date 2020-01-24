package com.example.cdcapi.services;

import com.example.cdcapi.entities.Usuarios;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface EmailService {

    void sendEmail(SimpleMailMessage msg);

    void sendNewPasswordEmail(Usuarios usuarios, String newPass);
}
