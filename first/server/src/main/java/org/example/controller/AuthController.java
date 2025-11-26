package org.example.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.example.authentication.AuthenticationResponse;
import org.example.authentication.SignInRequest;
import org.example.authentication.SignUpRequest;
import org.example.service.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("/auth")
@Tag(name = "Аутентификация")
public class AuthController {
    private final AuthenticationService authService;

    public AuthController(AuthenticationService authService) {
        this.authService = authService;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<AuthenticationResponse> signUp(@RequestBody @Valid SignUpRequest request){
        String jwt = authService.signUp(request);
        AuthenticationResponse resp = new AuthenticationResponse(jwt, "Bearer");
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@RequestBody @Valid SignInRequest request){
        try{
            String jwt = authService.signIn(request);
            AuthenticationResponse resp = new AuthenticationResponse(jwt, "Bearer");
            return ResponseEntity.ok(resp);
        }catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("signIn error: " + ex.getMessage());
        }
    }


}
