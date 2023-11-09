package com.app.models;

import com.app.enums.Category;
import jakarta.persistence.*;
import java.util.Date;
@Entity
@Table(name = "models")
public class Models extends BaseEntity {
    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private Category category;
    @Column(name = "end_year")
    private Integer end_year;
    @Column(name = "image_url")
    private String image_url;
    @Column(name = "name")
    private String name;
    @Column(name = "start_year")
    private Integer start_year;
    @Column(name = "brand_id", insertable = false, updatable = false)
    private Integer brand_id;
    @OneToOne(mappedBy = "model",cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Offers offer;
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brands brand;
    public Models() {}
    public Category getCategory() {return category;}
    public void setCategory(Category category) {this.category = category;}
    public Integer getBrand_id() {
        return brand_id;
    }
    public void setBrand_id(Integer brand_id) {
        this.brand_id = brand_id;
    }
    public Brands getBrand() {
        return brand;
    }
    public void setBrand(Brands brand) {
        this.brand = brand;
    }
    public Offers getOffer() {
        return offer;
    }
    public void setOffer(Offers offers) {
        this.offer = offer;
    }
    public Integer getEnd_year() {
        return end_year;
    }
    public void setEnd_year(Integer end_year) {
        this.end_year = end_year;
    }
    public String getImage_url() {
        return image_url;
    }
    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getStart_year() {
        return start_year;
    }
    public void setStart_year(Integer start_year) {
        this.start_year = start_year;
    }
}