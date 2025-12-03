package org.example.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.example.authentication.AuthenticationResponse;
import org.example.authentication.SignInRequest;
import org.example.authentication.SignUpRequest;
import org.example.db.Workers;
import org.example.service.AuthenticationService;
import org.example.service.RefreshTokensService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@Tag(name = "Аутентификация")
public class AuthController {
    private AuthenticationService authService;
    private RefreshTokensService refreshTokensService;

    public AuthController(AuthenticationService authService, RefreshTokensService refreshTokensService) {
        this.authService = authService;
        this.refreshTokensService = refreshTokensService;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<AuthenticationResponse> signUp(@RequestBody @Valid SignUpRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.signUp(request));
    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@RequestBody @Valid SignInRequest request){
        try{
            return ResponseEntity.ok(authService.signIn(request));
        }catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("signIn error: " + ex.getMessage());
        }
    }


    @PostMapping("refresh")
    public ResponseEntity<AuthenticationResponse> refresh(@RequestBody Map<String, String> body){
        String token = body.get("refreshToken");
        if(token == null) { ResponseEntity.badRequest().body("NO REFRESH TOKEN"); }

        Optional<Workers> workerO = refreshTokensService.verifyAndConsume(token, true);
        if(workerO.isEmpty()){ return ResponseEntity.status(401).build(); }

        return ResponseEntity.ok(authService.refresh(body, workerO.get()));

    }


}
