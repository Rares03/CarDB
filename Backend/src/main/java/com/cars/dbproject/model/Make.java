package com.cars.dbproject.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "make")
public class Make {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer makeid;

    private String makename;
    private String country;
    private String image;

    @OneToMany(mappedBy = "make")
    Set<Car> car;

    public Integer getMakeid() {
        return makeid;
    }

    public void setMakeid(Integer makeid) {
        this.makeid = makeid;
    }

    public String getMakename() {
        return makename;
    }

    public void setMakename(String makename) {
        this.makename = makename;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
