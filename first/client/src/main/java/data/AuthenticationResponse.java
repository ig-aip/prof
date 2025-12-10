package data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthenticationResponse {
    @JsonProperty("worker")
    Workers worker;
    JwtPair jwtPair = new JwtPair("", "", "");
    float networkEffectPercent;


    public AuthenticationResponse(){}

    public Workers getWorker() {
        return worker;
    }

    public void setWorker(Workers worker) {
        this.worker = worker;
    }

    public JwtPair getJwtPair() {
        return jwtPair;
    }
    public void setJwtPair(JwtPair jwtPair) {
        this.jwtPair = jwtPair;
    }

    public void setAccessToken(String accessToken){
        this.jwtPair.setAccessToken(accessToken);
    }

    public String getAccessToken(){
        return jwtPair.getAccessToken();
    }

    public void setRefreshToken(String refreshToken){
        this.jwtPair.setRefreshToken(refreshToken);
    }

    public String getRefreshToken(){
        return jwtPair.getRefreshToken();
    }

    public float getNetworkEffectPercent() {
        return networkEffectPercent;
    }

    public void setNetworkEffectPercent(float networkEffectyvness) {
        this.networkEffectPercent = networkEffectyvness;
    }
}
