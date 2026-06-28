package com.shradha.smart_hospital_management.auth.dto;


import com.shradha.smart_hospital_management.auth.entity.Role;
import com.shradha.smart_hospital_management.common.enums.AccountStatus;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterResponse {

    private Long id;

    private String email;

    private Role role;

    private String message;

    private AccountStatus accountStatus;

}