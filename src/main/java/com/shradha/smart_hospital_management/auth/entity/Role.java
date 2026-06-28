package com.shradha.smart_hospital_management.auth.entity;

import com.shradha.smart_hospital_management.common.entity.BaseEntity;
import com.shradha.smart_hospital_management.common.enums.RoleType;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role extends BaseEntity {


    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private RoleType name;
}
