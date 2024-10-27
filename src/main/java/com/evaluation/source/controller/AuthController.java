package com.evaluation.source.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evaluation.source.base.BaseController;
import com.evaluation.source.dto.base.ResultResponse;
import com.evaluation.source.dto.user.UserDTO;
import com.evaluation.source.service.i.AuthService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
public class AuthController extends BaseController {
    
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ResultResponse> authenticate(
            @RequestBody UserDTO authRequest
    ) {
        String authResponse = authService.login(authRequest);
        return buildResponse("Login result", authResponse);
    }
}
