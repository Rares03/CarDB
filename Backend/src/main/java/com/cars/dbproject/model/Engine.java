package com.cars.dbproject.model;

import jakarta.persistence.*;

@Entity
@Table(name = "engine")
public class Engine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "engineid")
    private Integer engineid;
    private String enginename;
    private Integer displacement;
    private Integer horsepower;
    private Integer torque;
    private Integer aspirationid;
    private Integer fuelid;

    @OneToOne(mappedBy = "engine")
    private Model model;

    @OneToOne
    @JoinColumn(name = "fuelid", insertable=false, updatable=false)
    private Fuel fuel;

    @OneToOne
    @JoinColumn(name = "aspirationid", insertable=false, updatable=false)
    private Aspiration aspiration;

    public Integer getEngineid() {
        return engineid;
    }

    public void setEngineid(Integer engineid) {
        this.engineid = engineid;
    }

    public String getEnginename() {
        return enginename;
    }

    public void setEnginename(String enginename) {
        this.enginename = enginename;
    }

    public Integer getDisplacement() {
        return displacement;
    }

    public void setDisplacement(Integer displacement) {
        this.displacement = displacement;
    }

    public Integer getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(Integer horsepower) {
        this.horsepower = horsepower;
    }

    public Integer getTorque() {
        return torque;
    }

    public void setTorque(Integer torque) {
        this.torque = torque;
    }

    public Integer getAspirationid() {
        return aspirationid;
    }

    public void setAspirationid(Integer aspirationid) {
        this.aspirationid = aspirationid;
    }

    public Integer getFuelid() {
        return fuelid;
    }

    public void setFuelid(Integer fuelid) {
        this.fuelid = fuelid;
    }

    public Fuel getFuel() {
        return fuel;
    }

    public void setFuel(Fuel fuel) {
        this.fuel = fuel;
    }

    public Aspiration getAspiration() {
        return aspiration;
    }

    public void setAspiration(Aspiration aspiration) {
        this.aspiration = aspiration;
    }
}
