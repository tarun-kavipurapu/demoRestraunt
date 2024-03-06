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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthController {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private RefreshTokenService refreshTokenService;
    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("auth/v1/signup")
    public ResponseEntity signup(@RequestBody UserDto userDto){
        try{
            Boolean isSignUped = userDetailsServiceImpl.signupUser(userDto);
            if(Boolean.FALSE.equals(isSignUped)){
                return new ResponseEntity<>("Already Exists ",HttpStatus.BAD_REQUEST);
            }
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDto.getUsername());
            String jwtToken = jwtService.generateToken(userDto.getUsername());
            return new ResponseEntity<>(JwtResponseDto.builder().accessToken(jwtToken).
                                                      token(refreshToken.getToken()).build(), HttpStatus.OK);
        }
        catch (Exception ex){
            return  new ResponseEntity<>("Exception in User Services Implementation", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("auth/v1/login")
    public ResponseEntity login (@RequestBody AuthRequestDto authRequestDto){
        try{
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDto.getUsername(),authRequestDto.getPassword()));
            if(authentication.isAuthenticated()){
                RefreshToken refreshToken = refreshTokenService.createRefreshToken(authRequestDto.getUsername());
                return new ResponseEntity<>(JwtResponseDto.builder()
                                                          .accessToken(jwtService.generateToken(authRequestDto.getUsername()))
                                                          .token(refreshToken.getToken())
                                                          .build(), HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>("User not Auuthentication ", HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }
        catch (Exception ex){
            return new ResponseEntity<>("Exception while logging in ",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public JwtResponseDto refreshToken(@RequestBody RefreshTokenRequestDto refreshTokenRequestDto){
        return refreshTokenService.findByToken(refreshTokenRequestDto.getToken())
                                  .map(refreshTokenService::verifyExpiration)
                                  .map(RefreshToken::getUserId)
                                  .map(userId -> {
                                      String accessToken = jwtService.generateToken(userId.getUsername());
                                      return JwtResponseDto.builder()
                                                           .accessToken(accessToken)
                                                           .token(refreshTokenRequestDto.getToken()).build();
                                  }).orElseThrow(() ->new RuntimeException("Refresh Token is not in DB..!!"));
    }

}
