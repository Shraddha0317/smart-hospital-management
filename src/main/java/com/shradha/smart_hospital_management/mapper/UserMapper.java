package com.shradha.smart_hospital_management.mapper;


import com.shradha.smart_hospital_management.auth.dto.LoginResponse;
import com.shradha.smart_hospital_management.auth.dto.RegisterRequest;
import com.shradha.smart_hospital_management.auth.dto.RegisterResponse;
import com.shradha.smart_hospital_management.auth.entity.Role;
import com.shradha.smart_hospital_management.auth.entity.User;
import com.shradha.smart_hospital_management.common.enums.AccountStatus;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public User toEntity(RegisterRequest registerRequest, String encodedPassword, Role role, AccountStatus active){

      return User.builder().email(registerRequest.getEmail())
              .password(encodedPassword)
              .roles(Set.of(role))
              .accountStatus(AccountStatus.ACTIVE)
              .build();
    }


    public RegisterResponse toResponse(User user, Role patientRole, AccountStatus accountStatus) {

        return RegisterResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .role(patientRole)
                .message("You registered succesfully")
                .accountStatus(accountStatus)

                .build();


    }



    public LoginResponse toLoginResponse(User user, String token){

        return LoginResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .roles(
                        user.getRoles()
                                .stream()
                                .map(role -> role.getName().name())
                                .collect(Collectors.toSet())
                )
                .accessToken(token)
                .message("welcome "+user.getEmail())
                .build();

    }
}
