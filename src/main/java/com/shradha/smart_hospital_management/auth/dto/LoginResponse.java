package com.shradha.smart_hospital_management.auth.dto;



import lombok.*;

import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponse {

    private Long id;

    private String email;

    private Set<String> roles;

    private String message;


    private String accessToken;
    //private String refreshToken;

}