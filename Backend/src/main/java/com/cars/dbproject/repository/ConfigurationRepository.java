package com.cars.dbproject.repository;

import com.cars.dbproject.model.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfigurationRepository extends JpaRepository<Configuration, Integer> {
}
