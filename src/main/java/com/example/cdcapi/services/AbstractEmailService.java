package com.example.cdcapi.services;

import com.example.cdcapi.entities.Usuarios;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import java.util.Date;

public abstract class AbstractEmailService implements EmailService {

    @Value("${default.sender}")
    private String sender;

    @Override
    public void sendNewPasswordEmail(Usuarios usuarios, String newPass) {
        SimpleMailMessage sm = prepareNewPasswordEmail(usuarios, newPass);
        sendEmail(sm);
    }

    protected SimpleMailMessage prepareNewPasswordEmail(Usuarios usuarios, String newPass) {
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(usuarios.getEmail());
        sm.setFrom(sender);
        sm.setSubject("Solicitação de nova senha");
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText("Nova senha: " + newPass);
        return sm;
    }
}
