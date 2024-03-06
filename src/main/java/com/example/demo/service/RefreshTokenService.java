package com.example.demo.service;

import com.example.demo.model.RefreshToken;
import com.example.demo.model.User;
import com.example.demo.repository.RefreshTokenRepo;
import com.example.demo.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {
    @Autowired
    RefreshTokenRepo refreshTokenRepo;

    UserRepo userRepo;

    /*
     * createRefreshtoken
     *
     * */
    public RefreshToken createRefreshToken(String username) {
        User userInfoExtracted = userRepo.findByUsername(username);
        RefreshToken refreshToken = RefreshToken
                .builder()
                .userId(userInfoExtracted)
                .token(UUID.randomUUID().toString()).expiryDate(Instant.now().plusMillis(600000)).build();

        return refreshTokenRepo.save(refreshToken);
    }

    public Optional<RefreshToken> findByToken(String token){
        return refreshTokenRepo.findByToken(token);
    }
    public RefreshToken verifyExpiration(RefreshToken token){
        if(token.getExpiryDate().compareTo(Instant.now())<0){
            refreshTokenRepo.delete(token);
            throw new RuntimeException(token.getToken() + " Refresh token is expired. Please make a new login..!");
        }
        return token;
    }
}
