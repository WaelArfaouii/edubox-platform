package com.arfaouiKallebi.JournalWeb.controller;

import com.arfaouiKallebi.JournalWeb.controller.api.AuthenticationApi;
import com.arfaouiKallebi.JournalWeb.dto.auth.AuthenticationResponse;
import com.arfaouiKallebi.JournalWeb.dto.RegisterDto;
import com.arfaouiKallebi.JournalWeb.dto.auth.AuthenticationRequest;
import com.arfaouiKallebi.JournalWeb.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthenticationController implements AuthenticationApi {
    @Autowired
    private IUserService userService ;

    @Override
    public ResponseEntity<?> register(@RequestBody RegisterDto registerDto) {
        return userService.register(registerDto) ;
    }

    @Override
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        return userService.authenticate(request) ;
    }
}