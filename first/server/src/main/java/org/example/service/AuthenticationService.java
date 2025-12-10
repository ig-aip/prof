package org.example.service;

import org.example.authentication.AuthenticationResponse;
import org.example.authentication.SignInRequest;
import org.example.authentication.SignUpRequest;
import org.example.db.Workers;
import org.example.details.CustomDetailsWorkers;
import org.example.security.SecurityConfig;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class AuthenticationService {
    private  CustomDetailsWorkerService workerService;
    private  JwtService jwtService;
    private  RefreshTokensService refreshTokensService;
    private  PasswordEncoder passwordEncoder;
    private  AuthenticationManager authenticationManager;
    private  VendingApparatesService vendingApparatesService;

    public AuthenticationService(CustomDetailsWorkerService workerService, JwtService jwtService, RefreshTokensService refreshTokensService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, VendingApparatesService vendingApparatesService) {
        this.workerService = workerService;
        this.jwtService = jwtService;
        this.refreshTokensService = refreshTokensService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.vendingApparatesService = vendingApparatesService;
    }


    public AuthenticationResponse signUp(SignUpRequest request){
        String hash_password = passwordEncoder.encode(request.getPassword());
        CustomDetailsWorkers worker = new CustomDetailsWorkers(new Workers(request.getFirstName(),
                request.getSecondName(),
                request.getThirdName(),
                request.getEmail(),
                request.getPhone(),
                hash_password,
                request.getRole())
        );

        workerService.create(worker);
        String accessToken = jwtService.generateAccessToken(worker.getWorker());
        String refreshToken = refreshTokensService.createRefrshToken(worker.getWorker(), 1);

        AuthenticationResponse resp = new AuthenticationResponse(accessToken, refreshToken, "Bearer");
        return resp;
    }

    public AuthenticationResponse signIn(SignInRequest request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        ));

        Workers worker = workerService.loadUserByUsername(request.getEmail()).getWorker();


        String accessToken = jwtService.generateAccessToken(workerService.findWorkerByEmail(request.getEmail()));
        String refreshToken = refreshTokensService.createRefrshToken(workerService.findWorkerByEmail(request.getEmail()), 1);
        AuthenticationResponse resp = new AuthenticationResponse(accessToken, refreshToken, "Bearer", worker, vendingApparatesService.getVendingApparatesNetworkEffectivnessPercent());
        System.out.printf("PERCENT IN SIGN - IN: " + resp.getNetworkEffectPercent());

        return resp;
    }

    public AuthenticationResponse refresh(Map<String, String> body, Workers worker){

            String newAccess = jwtService.generateAccessToken(worker);
            String newRefresh = refreshTokensService.createRefrshToken(worker, 1);

            AuthenticationResponse resp = new AuthenticationResponse(newAccess, newRefresh, "Bearer");

            return resp;
    }
}
