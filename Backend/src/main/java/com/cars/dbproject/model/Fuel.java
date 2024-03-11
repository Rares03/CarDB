package com.cars.dbproject.model;

import jakarta.persistence.*;

@Entity
@Table(name = "fuel")
public class Fuel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fuelid")
    private Integer fuelid;
    private String type;

    @OneToOne(mappedBy = "fuel")
    private Engine engine;

    public Integer getFuelid() {
        return fuelid;
    }

    public void setFuelid(Integer fuelid) {
        this.fuelid = fuelid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
