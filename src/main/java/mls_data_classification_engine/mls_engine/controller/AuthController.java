package mls_data_classification_engine.mls_engine.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import mls_data_classification_engine.mls_engine.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import mls_data_classification_engine.mls_engine.DTO.AuthRequest;
import mls_data_classification_engine.mls_engine.DTO.AuthResponse;
import mls_data_classification_engine.mls_engine.model.User;

@RestController
// sets shared url prefix for every endpoint
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    // register users, creates a hashedpassword
    // gives default classification and saves user
    @PostMapping("/register")
    public String register(@RequestBody AuthRequest request) {
        // TODO: process POST request
        User savedUser = authService.registUser(request.getUsername(), request.getRawpassword());
        return savedUser.getUsername();
    }

    // on login looks up user, comapres raw to hash
    // if correct, generates token, controoller get back token
    // and saves token in dto authresponse
    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        // TODO: process POST request

        String token = authService.login(request.getUsername(), request.getRawpassword());
        AuthResponse response = new AuthResponse();
        response.setToken(token);

        return response;
    }

    public AuthController(AuthService authService) {
        this.authService = authService;
    }
}
