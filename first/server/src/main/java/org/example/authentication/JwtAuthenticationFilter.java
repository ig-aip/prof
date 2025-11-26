package org.example.authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import org.example.db.Workers;
import org.example.repositories.WorkersRepo;
import org.example.service.JwtService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final WorkersRepo workersRepo;
    private final JwtService jwtService;

    public JwtAuthenticationFilter(WorkersRepo workersRepo, JwtService jwtService) {
        this.workersRepo = workersRepo;
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(
            @NotNull HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull FilterChain filterChain
    ) throws ServletException, IOException
    {
        String header = request.getHeader("Authorization");
        if(header != null && header.startsWith("Bearer ")){
            String token = header.substring(7);
            try {
                Jws<Claims> claimsJws = jwtService.parseClaims(token);
                String userId = claimsJws.getBody().getSubject();
                String role = claimsJws.getBody().get("role", String.class);

                Optional<Workers> worker = workersRepo.findById(Long.valueOf(userId));

                if(worker.isPresent()){
                    UsernamePasswordAuthenticationToken auth =
                            new UsernamePasswordAuthenticationToken(worker.get().getEmail(), null,
                                    List.of(new SimpleGrantedAuthority(role)));

                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }catch (JwtException jEx){
                logger.info("Invalid jwt: " + jEx.getMessage());
            }
        }
        filterChain.doFilter(request, response);
    }
}
