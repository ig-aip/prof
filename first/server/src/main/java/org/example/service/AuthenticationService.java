package org.example.service;

import org.example.authentication.SignInRequest;
import org.example.authentication.SignUpRequest;
import org.example.db.Workers;
import org.example.details.CustomDetailsWorkers;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final CustomDetailsWorkerService workerService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(CustomDetailsWorkerService workerService, JwtService jwtService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.workerService = workerService;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public String signUp(SignUpRequest request){

        CustomDetailsWorkers worker = new CustomDetailsWorkers(new Workers(request.getFirstName(),
                request.getSecondName(),
                request.getThirdName(),
                request.getEmail(),
                request.getPhone(),
                request.getPassword(),
                request.getRole()));

        workerService.create(worker);
        String jwt = jwtService.generateAccessToken(worker.getWorker());
        return jwt;
    }

    public String signIn(SignInRequest request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        ));

        UserDetails worker = workerService.loadUserByUsername(request.getEmail());

        String jwt = jwtService.generateAccessToken(workerService.findWorkerByEmail(request.getEmail()));
        return jwt;
    }
}
