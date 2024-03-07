package com.example.demo.controller;

import com.example.demo.dto.AuthRequestDto;
import com.example.demo.dto.JwtResponseDto;
import com.example.demo.dto.RefreshTokenRequestDto;
import com.example.demo.dto.UserDto;
import com.example.demo.model.RefreshToken;
import com.example.demo.model.User;
import com.example.demo.service.JwtService;
import com.example.demo.service.RefreshTokenService;
import com.example.demo.service.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    @Autowired
    private final JwtService jwtService;
    @Autowired
    private final RefreshTokenService refreshTokenService;
    @Autowired
    private final UserDetailsServiceImpl userDetailsServiceImpl;
    @Autowired
    private final AuthenticationManager authenticationManager;

    @PostMapping("/test")
    public String handleRequest() {
        return "hello world";
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> signup(@RequestBody UserDto userDto) {
        System.out.println(userDto);
        try {
            Boolean isSignedUp = userDetailsServiceImpl.signupUser(userDto);
            if (Boolean.FALSE.equals(isSignedUp)) {
                return new ResponseEntity<>("Already Exists ", HttpStatus.BAD_REQUEST);
            }
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDto.getUsername());
            String jwtToken = jwtService.generateToken(userDto.getUsername());
            return ResponseEntity.ok(JwtResponseDto.builder()
                                                   .accessToken(jwtToken)
                                                   .token(refreshToken.getToken())
                                                   .build());
        } catch (Exception ex) {
            return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody AuthRequestDto authRequestDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequestDto.getUsername(), authRequestDto.getPassword()));
            if (authentication.isAuthenticated()) {
                RefreshToken refreshToken = refreshTokenService.createRefreshToken(authRequestDto.getUsername());
                return ResponseEntity.ok(JwtResponseDto.builder()
                                                       .accessToken(jwtService.generateToken(authRequestDto.getUsername()))
                                                       .token(refreshToken.getToken())
                                                       .build());
            } else {
                return new ResponseEntity<>("User not authenticated", HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("Exception while logging in", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<JwtResponseDto> refreshToken(@RequestBody RefreshTokenRequestDto refreshTokenRequestDto) {
        return refreshTokenService.findByToken(refreshTokenRequestDto.getToken())
                                  .map(refreshTokenService::verifyExpiration)
                                  .map(RefreshToken::getUserId)
                                  .map(userId -> JwtResponseDto.builder()
                                                               .accessToken(jwtService.generateToken(userId.getUsername()))
                                                               .token(refreshTokenRequestDto.getToken())
                                                               .build())
                                  .map(ResponseEntity::ok)
                                  .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }
}