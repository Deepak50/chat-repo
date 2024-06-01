package com.prj.chatapp.controller;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.prj.chatapp.model.Token;

@RestController
@RequestMapping("/token")
public class TokenController {
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	@Autowired
	private final RestTemplate restTemplate;

	private String accessToken;
	
    public TokenController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
	
    @GetMapping("/getAccTok")
    public String getToken() {
    	return accessToken;
    }
    
	@GetMapping("")
    public String handleOAuthResponse(
            @RequestParam("code") String authorizationCode,
            @RequestParam("state") String state,
            @RequestParam("scope") String scope,
            @RequestParam("authuser") String authUser,
            @RequestParam("prompt") String prompt) {
        
        // Handle the authorization code and any additional parameters here
        System.out.println("Authorization Code: " + authorizationCode);
        System.out.println("State: " + state);
        System.out.println("Scope: " + scope);
        System.out.println("Auth User: " + authUser);
        System.out.println("Prompt: " + prompt);
        
//        WebClient webClient = WebClient.create();
//
//        // Define the request body
        System.out.println("authCode: "+ authorizationCode);
        String clientId = "49280116826-5j8ia9avme7fhj4eu456mvqlafm3qmss.apps.googleusercontent.com";
        String clientSecret = "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";
        String code = authorizationCode;
        String grantType = "authorization_code";
        String redirectUri = "http://localhost:8080/token";

        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // Set the request body
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("client_id", clientId);
        body.add("client_secret", clientSecret);
        body.add("code", code);
        body.add("grant_type", grantType);
        body.add("redirect_uri", redirectUri);

        // Create the request entity
        HttpEntity<Response> requestEntity = new HttpEntity(body, headers);
        
        ResponseEntity<Token> responseEntity = restTemplate.exchange(
                "https://oauth2.googleapis.com/token",
                HttpMethod.POST,
                requestEntity,
                Token.class
        );

        // Extract the response body
        Token token = responseEntity.getBody();
        
        accessToken= token.getId_token().toString();
        
        // Return the token as a string representation
        return token.getId_token().toString();

        
    }
}
