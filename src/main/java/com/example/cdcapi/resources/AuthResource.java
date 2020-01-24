package com.example.cdcapi.resources;

import com.example.cdcapi.dto.EmailDTO;
import com.example.cdcapi.security.JWTUtil;
import com.example.cdcapi.security.UsuariosSecurity;
import com.example.cdcapi.services.AuthService;
import com.example.cdcapi.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "api/auth")
public class AuthResource {

    private final JWTUtil jwtUtil;
    private final AuthService authService;

    public AuthResource(JWTUtil jwtUtil, AuthService authService) {
        this.jwtUtil = jwtUtil;
        this.authService = authService;
    }

    @CrossOrigin
    @PostMapping(value = "/refresh_token")
    public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
        UsuariosSecurity user = UserService.authenticated();
        String token = jwtUtil.generateToken(user.getUsername());
        response.addHeader("Authorization", "Bearer " + token);
        response.addHeader("access-control-expose-headers", "Authorization");
        return ResponseEntity.noContent().build();
    }

    @CrossOrigin
    @PostMapping(value = "/nova_senha")
    public ResponseEntity<Void> newPassword(@Valid @RequestBody EmailDTO emailDTO) {
        authService.sendNewPassword(emailDTO.getEmail());
        return ResponseEntity.ok().build();
    }
}
