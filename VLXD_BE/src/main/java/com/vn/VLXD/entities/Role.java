package com.vn.VLXD.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import com.vn.VLXD.common.ERole;

@Entity
@Table(name = "ROLES")
@Getter
@Setter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20,name = "ROLE")
    private ERole name;

    public Role() {
    }

    public Role(ERole name) {
        this.name = name;
    }
}
