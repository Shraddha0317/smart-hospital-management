package com.shradha.smart_hospital_management.auth.service;


import com.shradha.smart_hospital_management.auth.dto.LoginRequest;
import com.shradha.smart_hospital_management.auth.dto.LoginResponse;
import com.shradha.smart_hospital_management.auth.dto.RegisterRequest;
import com.shradha.smart_hospital_management.auth.dto.RegisterResponse;
import com.shradha.smart_hospital_management.auth.entity.Role;
import com.shradha.smart_hospital_management.auth.entity.User;
import com.shradha.smart_hospital_management.auth.repository.RoleRepository;
import com.shradha.smart_hospital_management.auth.repository.UserRepository;
import com.shradha.smart_hospital_management.common.enums.AccountStatus;
import com.shradha.smart_hospital_management.common.enums.RoleType;
import com.shradha.smart_hospital_management.common.exception.EmailAlreadyExistsException;
import com.shradha.smart_hospital_management.common.exception.ResourceNotFoundException;
import com.shradha.smart_hospital_management.config.PasswordConfig;
import com.shradha.smart_hospital_management.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final  PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private  final JwtService jwtService;



    @Transactional
    public RegisterResponse register(RegisterRequest registerRequest){

        if(userRepository.findByEmail(registerRequest.getEmail()).isPresent()){
            throw new EmailAlreadyExistsException("User already exists with email: " + registerRequest.getEmail());
        }

        Role patientRole=roleRepository.findByName(RoleType.PATIENT)
                .orElseThrow(()->new RuntimeException("Patient role not found"));

        String encodedPassword = passwordEncoder.encode(registerRequest.getPassword());

        User user =userMapper.toEntity(registerRequest,encodedPassword,patientRole,AccountStatus.ACTIVE);
        User SaveUser =userRepository.save(user);

        return userMapper.toResponse(SaveUser,patientRole,AccountStatus.ACTIVE);


    }


    @Transactional(readOnly = true)
    public LoginResponse login(LoginRequest request) {


    User user = userRepository.findByEmail(request.getEmail())
            .orElseThrow(()->new ResourceNotFoundException("Invalid email or password"));


    if(user.getAccountStatus()!= AccountStatus.ACTIVE){
        throw new RuntimeException("Account is not Active");
    }
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        String token = jwtService.generateToken(user);

        return userMapper.toLoginResponse(user,token);

    }


}
