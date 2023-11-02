package com.app.models;

import jakarta.persistence.*;

import java.util.Date;

@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected int id;

    @Column(name = "created")
    private Date created;

    @Column(name = "modified")
    private Date modified;

    public Date getCreated() {return created;}

    public void setCreated(Date created) {this.created = created;}

    public Date getModified() {return modified;}

    public void setModified(Date modified) {this.modified = modified;}

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}
