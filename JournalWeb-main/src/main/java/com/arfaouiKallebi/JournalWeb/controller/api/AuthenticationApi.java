package com.arfaouiKallebi.JournalWeb.controller.api;

import com.arfaouiKallebi.JournalWeb.dto.auth.AuthenticationResponse;
import com.arfaouiKallebi.JournalWeb.dto.RegisterDto;
import com.arfaouiKallebi.JournalWeb.dto.auth.AuthenticationRequest;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Api("authentication")
public interface AuthenticationApi {

  @PostMapping(value = "/auth/register", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> register(@RequestBody RegisterDto registerDto) ;
  @PostMapping(value = "/auth/login", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request);


}
