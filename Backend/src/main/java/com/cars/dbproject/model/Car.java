package com.cars.dbproject.model;

import jakarta.persistence.*;

@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer makeid;
    private Integer modelid;
    private Integer price;
    private Integer imageid;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "makeid", insertable=false, updatable=false)
    Make make;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "modelid", insertable=false, updatable=false)
    Model model;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "imageid", insertable=false, updatable=false)
    private Images images;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMakeid() {
        return makeid;
    }

    public void setMakeid(Integer makeid) {
        this.makeid = makeid;
    }

    public Integer getModelid() {
        return modelid;
    }

    public void setModelid(Integer modelid) {
        this.modelid = modelid;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getImageid() {
        return imageid;
    }

    public void setImageid(Integer imageid) {
        this.imageid = imageid;
    }

    public Make getMake() {
        return make;
    }

    public void setMake(Make make) {
        this.make = make;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }
}
