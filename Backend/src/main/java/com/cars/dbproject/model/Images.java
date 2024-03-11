package com.cars.dbproject.model;

import jakarta.persistence.*;

@Entity
@Table(name = "images")
public class Images {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer imageid;
    private String image_url;

    @OneToOne(mappedBy = "images")
    private Car car;

    public Integer getId() {
        return imageid;
    }

    public void setId(Integer id) {
        this.imageid = id;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}