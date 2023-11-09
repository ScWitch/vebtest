package com.app.models;

import jakarta.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "users")
public class Users extends BaseEntity {
    @Column(name = "email")
    private String email;
    @Column(name = "first_name")
    private String first_name;
    @Column(name = "last_name")
    private String last_name;
    @Column(name = "password")
    private String password;
    @Column(name = "is_active")
    private Boolean is_active;
    @Column(name = "image_url")
    private String image_url;
    @OneToMany(mappedBy = "seller",cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<Offers> offers;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Roles role;

    public Roles getRole() {
        return role;
    }
    public void setRole(Roles role) {
        this.role = role;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {this.email = email;}
    public String getFirst_name() {return first_name;}
    public void setFirst_name(String first_name) {this.first_name = first_name;}
    public String getLast_name() {return last_name;}
    public void setLast_name(String last_name) {this.last_name = last_name;}
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}
    public Set<Offers> getOffers() {return offers;}
    public void setOffers(Set<Offers> offers) {this.offers = offers;}
    public Boolean getIs_active() {return is_active;}
    public void setIs_active(Boolean is_active) {this.is_active = is_active;}
    public String getimage_url() {return image_url;}
    public void setimage_url(String image_url) {this.image_url = image_url;}
    protected Users() {}
}
