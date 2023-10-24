package com.app.models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "roles")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Roles extends BaseEntity {
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "role")
    private Set<Users> user;

    public Set<Users> getUser() {
        return user;
    }

    public void setUser(Set<Users> user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    protected Roles() {
    }

    protected Roles(String name) {
        this.name = name;
    }
}
