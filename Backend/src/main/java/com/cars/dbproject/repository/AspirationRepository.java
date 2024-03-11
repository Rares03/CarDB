package com.cars.dbproject.repository;

import com.cars.dbproject.model.Aspiration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AspirationRepository extends JpaRepository<Aspiration, Integer> {
}
