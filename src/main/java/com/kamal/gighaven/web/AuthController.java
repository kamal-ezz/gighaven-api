package com.kamal.gighaven.web;

import com.kamal.gighaven.dtos.LoginRequest;
import com.kamal.gighaven.dtos.LoginResponse;
import com.kamal.gighaven.dtos.SignupRequest;
import com.kamal.gighaven.security.UserPrincipal;
import com.kamal.gighaven.services.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest request) {

        try {
            LoginResponse loginResponse = service.signup(request);
            return ResponseEntity.ok(loginResponse);
        }catch (Exception e){
            return ResponseEntity.ok(e.getMessage());
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody LoginRequest request) {
        try {
            LoginResponse loginResponse= service.login(request);
            return ResponseEntity.ok(loginResponse);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.ok(e.getMessage());
        }
    }

    @PostMapping("/refresh-token")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        service.refreshToken(request, response);
    }


}