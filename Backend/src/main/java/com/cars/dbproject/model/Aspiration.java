package com.cars.dbproject.model;

import jakarta.persistence.*;

@Entity
@Table(name = "aspiration")
public class Aspiration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aspirationid")
    private Integer aspirationid;
    private String type;

    @OneToOne(mappedBy = "aspiration")
    private Engine engine;

    public Integer getAspirationid() {
        return aspirationid;
    }

    public void setAspirationid(Integer aspirationid) {
        this.aspirationid = aspirationid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
