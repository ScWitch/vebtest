package com.app.dtos;

import java.util.Date;
public class BrandsDto {
    private int id;
    private Date created;
    private Date modified;
    private String name;
    private ModelsDto models;
    private ModelsDto brand_id;

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public Date getCreated() {return created;}
    public void setCreated(Date created) {this.created = created;}
    public Date getModified() {return modified;}
    public void setModified(Date modified) {this.modified = modified;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public ModelsDto getModels() {return models;}
    public void setModels(ModelsDto models) {this.models = models;}
    public ModelsDto getBrand_id() {return brand_id;}
    public void setBrand_id(ModelsDto brand_id) {this.brand_id = brand_id;}

    public BrandsDto(){}
    public BrandsDto(Date created, Date modified, String name){
        this.created = created;
        this.modified = modified;
        this.name = name;
    }
}
