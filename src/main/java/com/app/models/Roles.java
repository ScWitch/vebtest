package com.app.models;

import com.app.enums.Role;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "roles")
public class Roles{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected int id;

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private Role name;

    @OneToMany(mappedBy = "role")
    private Set<Users> user;
    protected Roles() {}

}
