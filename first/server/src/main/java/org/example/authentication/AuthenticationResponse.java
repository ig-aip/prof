package org.example.authentication;

import org.example.db.Workers;

public class AuthenticationResponse {
    private String accessToken;
    private String refreshToken;
    private String tokenType;

    private Workers worker;

    private float networkEffectPercent;

    public AuthenticationResponse(String accessToken, String refreshToken, String tokenType, Workers worker, float networkEffectPercent) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.tokenType = tokenType;
        this.worker = worker;
        this.networkEffectPercent = networkEffectPercent;
    }


    public AuthenticationResponse(String accessToken, String refreshToken, String tokenType) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.tokenType = tokenType;
    }

    public AuthenticationResponse(String accessToken, String refreshToken, String tokenType, Workers worker) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.tokenType = tokenType;
        this.worker = worker;
    }

    public Workers getWorker() {
        return worker;
    }

    public void setWorker(Workers worker) {
        this.worker = worker;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }


    public float getNetworkEffectPercent() {
        return networkEffectPercent;
    }

    public void setNetworkEffectPercent(float networkEffectProcent) {
        this.networkEffectPercent = networkEffectProcent;
    }
}

