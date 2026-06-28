package com.shradha.smart_hospital_management.config;

import com.shradha.smart_hospital_management.auth.entity.Role;
import com.shradha.smart_hospital_management.auth.repository.RoleRepository;
import com.shradha.smart_hospital_management.common.enums.RoleType;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;


    @Override
    public void run(String... args) throws Exception {

        if(roleRepository.count()==0){
           for(RoleType roleType:RoleType.values()){

               Role role = new Role();
               role.setName(roleType);

               roleRepository.save(role);
           }
        }



    }
}
