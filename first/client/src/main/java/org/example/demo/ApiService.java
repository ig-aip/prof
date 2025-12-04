package org.example.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import data.AuthenticationResponse;
import data.JwtPair;
import data.Workers;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Map;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

public class ApiService {
    private static final ApiService SINGLTON = new ApiService();
    private HttpClient client;
    private ObjectMapper objectMapper = new ObjectMapper();
    private String url = "http://localhost:8080";
    private Preferences preferences = Preferences.userRoot().node("org.example.auth");
    Workers currentWorker;

    ApiService() {
        client = HttpClient.newBuilder().
                connectTimeout(Duration.ofSeconds(10))
                .build();
    }

    static public ApiService getSINGLTON(){
        return SINGLTON;
    }

    public AuthenticationResponse signIn(String email, String password) throws Exception {
        String json = objectMapper.writeValueAsString(Map.of("email", email, "password", password));
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(url + "/auth/sign-in"))
                .header("content-type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> resp = authenticationRequest(req);
        if(resp.statusCode() != 200){
            throw new Exception("Sign in Failed: "+ resp.statusCode() + ": "+ resp.body());
        }

        AuthenticationResponse tokens = objectMapper.readValue(resp.body(), AuthenticationResponse.class);
        saveTokens(tokens.getJwtPair().getAccessToken(), tokens.getJwtPair().getRefreshToken());
        currentWorker = tokens.getWorker();
        return tokens;
    }


    public String getRole() throws Exception {
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(url + "/auth/role"))
                .GET()
                .build();

        HttpResponse<String> resp = authenticationRequest(req);
        if(resp.statusCode() != 200) {
            throw new Exception("Get role failed: "+ resp.statusCode() + ": "+ resp.body());
        }

        return  resp.body();
    }

    private void saveTokens(String accessToken, String refreshToken){
        if(accessToken != null){ preferences.put("accessToken", accessToken);}
        if(refreshToken != null){ preferences.put("refreshToken", accessToken); }
    }

    private HttpResponse<String> authenticationRequest(HttpRequest original) throws IOException, InterruptedException {
        String accessToken = preferences.get("accessToken", null);

        HttpRequest.Builder build =  HttpRequest.newBuilder()
                .uri(original.uri())
                .method(original.method(), original.bodyPublisher().orElse(HttpRequest.BodyPublishers.noBody()));

        original.headers().map().forEach((key, valueList) -> {
            valueList.forEach(value -> build.header(key, value));
        });

        if(accessToken != null){
            build.header("Authorization", "Bearer " + accessToken);
        }

        HttpRequest req = build.build();

        HttpResponse<String> resp = client.send(req, HttpResponse.BodyHandlers.ofString());

        if(resp.statusCode() == 401){
            boolean refreshed = refresh();
            if(!refreshed){ return  resp; }

            accessToken = preferences.get("accessToken", null);
            if(accessToken == null) { return resp; }

            HttpRequest.Builder build2 =  HttpRequest.newBuilder()
                    .uri(original.uri())
                    .method(original.method(), original.bodyPublisher().orElse(HttpRequest.BodyPublishers.noBody()));

            original.headers().map().forEach((key, valueList) -> {
                valueList.forEach(value -> build.header(key, value));
            });

            if(accessToken != null){
                build.header("Authorization", "Bearer " + accessToken);
            }

            return client.send(build2.build(), HttpResponse.BodyHandlers.ofString());
        }
        return resp;

    }

    private boolean refresh() throws JsonProcessingException {
        String regreshToken = preferences.get("refreshToken", null);
        if(regreshToken == null){ return false; }


        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(url + "/auth/refresh"))
                .header("contentType", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(Map.of("refreshToken", regreshToken))))
                .build();

        System.out.println(objectMapper.writeValueAsString(Map.of("refreshToken", regreshToken)));

        try{
            HttpResponse<String> resp = client.send(req, HttpResponse.BodyHandlers.ofString());
            if(resp.statusCode() == 200){
                JwtPair newTokens = objectMapper.readValue(resp.body(), JwtPair.class);
                String accessToken = newTokens.getAccessToken();
                String refrshToken = newTokens.getRefreshToken();

                preferences.put("accessToken", accessToken);
                if(refrshToken != null){
                    preferences.put("refreshToken", refrshToken);
                }else{
                    return false;
                }
                return true;
            }


        } catch (Exception e) {
            System.out.println("Refresh error: "  + e.getMessage());
        }
        return false;


    }


    public Workers getCurrentWorker() {
        return currentWorker;
    }

    public void clear() throws BackingStoreException {
        this.currentWorker = null;
        preferences.clear();
    }

}

