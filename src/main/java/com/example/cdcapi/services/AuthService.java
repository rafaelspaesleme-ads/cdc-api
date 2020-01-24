package com.example.cdcapi.services;

import com.example.cdcapi.entities.Usuarios;
import com.example.cdcapi.exceptions.ObjectNotFindException;
import com.example.cdcapi.repositories.UsuariosRepository;
import com.github.javafaker.Faker;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;
import java.util.Random;

@Service
public class AuthService {

    private final UsuariosRepository usuariosRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final EmailService emailService;

    private Random random = new Random();
    private Faker faker = new Faker();

    public AuthService(UsuariosRepository usuariosRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder, EmailService emailService) {
        this.usuariosRepository = usuariosRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.emailService = emailService;
    }

    public void sendNewPassword(String email){
        Optional<Usuarios> byEmail = usuariosRepository.findByEmail(email);
        if (!byEmail.isPresent()){
            throw new ObjectNotFindException("E-mail não existe em nossa base de dados!");
        }

        String newPassword = newPassword();
        byEmail.get().setSenha(bCryptPasswordEncoder.encode(newPassword));
        usuariosRepository.save(byEmail.get());
        emailService.sendNewPasswordEmail(byEmail.get(), newPassword);

    }

    private String newPassword() {
        char[] vetor = new char[10];
        for (int i=0; i<10; i++){
            vetor[i] = randomChar();
        }

        return faker.dragonBall().character().concat("_".concat(Arrays.toString(vetor))).replace(" ", "");
    }

    private char randomChar() {
        int opt = random.nextInt(3);
        if (opt == 0) { // gera um digito
            return (char) (random.nextInt(10) + 48);
        }
        else if (opt == 1) { // gera letra maiuscula
            return (char) (random.nextInt(26) + 65);
        }
        else { // gera letra minuscula
            return (char) (random.nextInt(26) + 97);
        }
    }

}
