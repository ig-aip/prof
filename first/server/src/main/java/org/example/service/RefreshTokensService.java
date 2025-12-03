package org.example.service;

import jakarta.transaction.Transactional;
import org.example.db.RefreshTokens;
import org.example.db.Workers;
import org.example.repositories.RefreshTokensRepo;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HexFormat;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokensService {
    private RefreshTokensRepo refreshRepo;
    private SecureRandom secureRandom;

    public RefreshTokensService(RefreshTokensRepo refreshRepo) {
        this.refreshRepo = refreshRepo;
    }

    @Transactional
    public String createRefrshToken(Workers worker, int days){
        String iternal = generateToken();
        String hash = sha256(iternal);

        RefreshTokens refTokens = new RefreshTokens();
        refTokens.setTokenHash(hash);
        refTokens.setWorker(worker);
        refTokens.setEndDate(Instant.now().plus(days, ChronoUnit.DAYS));
        refTokens.setBeginDate(Instant.now());
        refTokens.setEnabled(true);

        refreshRepo.save(refTokens);
        return iternal;

    }

    @Transactional
    public Optional<Workers> verifyAndConsume(String iternalToken, boolean rotate){
        String hash = sha256(iternalToken);
        Optional<RefreshTokens> mayBeToken = refreshRepo.findByTokenHash(hash);
        if(mayBeToken.isEmpty()){ return Optional.empty(); }

        RefreshTokens token = mayBeToken.get();
        if(!token.isEnabled()) { return Optional.empty(); }
        if(token.getEndDate().isBefore(Instant.now())) { return Optional.empty(); }

        Workers worker = token.getWorker();

        refreshRepo.delete(token);
        return Optional.of(worker);

    }

    public void removeAllForUser(Workers worker){
        refreshRepo.deleteByWorker(worker);
    }


    private String sha256(String iternal){
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest(iternal.getBytes(StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(digest);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }


    private String generateToken(){
        return UUID.randomUUID().toString();
    }
}
