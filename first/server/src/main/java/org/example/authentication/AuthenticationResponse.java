package org.example.authentication;

public class AuthenticationResponse {
    private String accessToken;
    private String typeToken;

    public AuthenticationResponse() {}

    public AuthenticationResponse(String accessToken, String typeToken) {
        this.accessToken = accessToken;
        this.typeToken = typeToken;
    }

    public String getAccessToken() {
        return accessToken;
    }
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTypeToken() {
        return typeToken;
    }
    public void setTypeToken(String typeToken) {
        this.typeToken = typeToken;
    }
}
