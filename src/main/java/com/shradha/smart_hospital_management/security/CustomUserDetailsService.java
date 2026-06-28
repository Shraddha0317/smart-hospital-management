package com.shradha.smart_hospital_management.security;


import com.shradha.smart_hospital_management.auth.entity.User;
import com.shradha.smart_hospital_management.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        User user= userRepository.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("User Not Found"));

        return new CustomUserDetails(user);
    }
}
