package com.cars.dbproject.repository;

import com.cars.dbproject.model.Make;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MakeRepository extends JpaRepository<Make, Integer> {
    List<Make> findAll(Sort sort);
}
