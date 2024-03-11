package com.cars.dbproject.repository;

import com.cars.dbproject.model.Fuel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuelRepository extends JpaRepository<Fuel, Integer> {
}
