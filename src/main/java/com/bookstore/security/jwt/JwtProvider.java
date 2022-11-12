package com.bookstore.security.jwt;

import com.bookstore.payload.response.OAuthResponse;
import com.bookstore.payload.response.TokenModel;
import com.bookstore.security.jwt.JwtRequestFilter;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.security.GeneralSecurityException;
import java.util.Collections;

@Component
public class JwtProvider implements AuthenticationProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtRequestFilter.class);

    @Value("${google.clientId}")
    private String clientId;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        TokenModel tokenModel = (TokenModel) authentication;
        NetHttpTransport transport = new NetHttpTransport();
        GsonFactory gsonFactory = GsonFactory.getDefaultInstance();
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, gsonFactory)
                .setAudience(Collections.singletonList(clientId))
                .build();
        try {
            GoogleIdToken idToken = verifier.verify(tokenModel.getToken());
            if(idToken != null) {
                GoogleIdToken.Payload payload = idToken.getPayload();
                String name = (String) payload.get("name");
                OAuthResponse oAuthResponse = new OAuthResponse(name);
                return (Authentication) oAuthResponse;
            }
        } catch (Exception e) {
            logger.error("fail", e.getMessage());
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(TokenModel.class);
    }
}