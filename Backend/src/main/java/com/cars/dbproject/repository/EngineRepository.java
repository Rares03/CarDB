package com.cars.dbproject.repository;

import com.cars.dbproject.model.Engine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EngineRepository extends JpaRepository<Engine, Integer> {

}