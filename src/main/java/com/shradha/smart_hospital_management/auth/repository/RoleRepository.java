package com.shradha.smart_hospital_management.auth.repository;

import com.shradha.smart_hospital_management.auth.entity.Role;
import com.shradha.smart_hospital_management.common.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {

    Optional<Role> findByName(RoleType name);
}
