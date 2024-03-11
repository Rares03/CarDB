package com.cars.dbproject.model;

import jakarta.persistence.*;

import java.util.Set;


@Entity
@Table(name = "model")
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "modelid")
    private Integer modelid;

    private String modelname;
    private Integer engineid;
    private Integer configid;

    @OneToMany(mappedBy = "model")
    Set<Car> car;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "engineid", insertable=false, updatable=false)
    private Engine engine;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "configid", insertable=false, updatable=false)
    private Configuration configuration;

    public Integer getModelid() {
        return modelid;
    }

    public void setModelid(Integer modelid) {
        this.modelid = modelid;
    }

    public String getModelname() {
        return modelname;
    }

    public void setModelname(String modelname) {
        this.modelname = modelname;
    }

    public Integer getEngineid() {
        return engineid;
    }

    public void setEngineid(Integer engineid) {
        this.engineid = engineid;
    }

    public Integer getConfigid() {
        return configid;
    }

    public void setConfigid(Integer configid) {
        this.configid = configid;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }
}
