package com.shradha.smart_hospital_management.auth.controller;


import com.shradha.smart_hospital_management.auth.dto.LoginRequest;
import com.shradha.smart_hospital_management.auth.dto.LoginResponse;
import com.shradha.smart_hospital_management.auth.dto.RegisterRequest;
import com.shradha.smart_hospital_management.auth.dto.RegisterResponse;
import com.shradha.smart_hospital_management.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {


    private  final AuthService authService;



    @PostMapping("/register")
    public ResponseEntity<RegisterResponse>register(@Valid @RequestBody RegisterRequest registerRequest){
        RegisterResponse response =authService.register(registerRequest);


        return  ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


   @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request){
        LoginResponse loginResponse=authService.login(request);
        return  ResponseEntity.ok(loginResponse);
    }
}
