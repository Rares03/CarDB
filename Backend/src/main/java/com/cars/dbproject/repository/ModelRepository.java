package com.cars.dbproject.repository;

import com.cars.dbproject.model.Model;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository extends JpaRepository<Model, Integer> {
}
