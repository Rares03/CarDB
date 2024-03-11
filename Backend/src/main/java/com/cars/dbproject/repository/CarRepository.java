package com.cars.dbproject.repository;

import com.cars.dbproject.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Integer> {
    List<Car> findByMakeid(Integer makeid);
}
