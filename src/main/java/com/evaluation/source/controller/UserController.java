package com.evaluation.source.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.evaluation.source.base.BaseController;
import com.evaluation.source.dto.base.ResultResponse;
import com.evaluation.source.dto.user.UserDTO;
import com.evaluation.source.model.User;
import com.evaluation.source.service.i.UserService;
import org.springframework.security.core.Authentication;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController extends BaseController{

    private final UserService userService;

    @PostMapping("/change-password")
    public ResponseEntity<ResultResponse> changePassword(
            Authentication authentication,
            @RequestParam String oldPassword,
            @RequestParam String newPassword
    ) {
        var currentUser = (User) authentication.getPrincipal();
        userService.changePassword(currentUser, oldPassword, newPassword);
        return buildResponse("Password has been changed");
    }

    @PostMapping("/register")
    public ResponseEntity<ResultResponse> register(
            @RequestBody UserDTO registerRequest) {
        userService.register(registerRequest);
        return buildResponse("Register successfully");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<ResultResponse> resetPassword(@RequestParam String email) {
        userService.resetPassword(email);
        return buildResponse("Reset password request has been sent to your email");
    }
    
}
