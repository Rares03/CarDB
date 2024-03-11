package com.cars.dbproject.model;

import jakarta.persistence.*;

@Entity
@Table(name = "configuration")
public class Configuration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "configid")
    private Integer configid;
    private String trimlevel;
    private String interiorcolor;

    @OneToOne(mappedBy = "configuration")
    private Model model;

    public Integer getConfigid() {
        return configid;
    }

    public void setConfigid(Integer configid) {
        this.configid = configid;
    }

    public String getTrimlevel() {
        return trimlevel;
    }

    public void setTrimlevel(String trimlevel) {
        this.trimlevel = trimlevel;
    }

    public String getInteriorcolor() {
        return interiorcolor;
    }

    public void setInteriorcolor(String interiorcolor) {
        this.interiorcolor = interiorcolor;
    }
}
