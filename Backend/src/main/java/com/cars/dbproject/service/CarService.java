package com.cars.dbproject.service;

import com.cars.dbproject.repository.CarRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService {

    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Transactional
    public void deleteCarById(Integer carId) {
        // Implement your logic to delete the engine
        carRepository.deleteById(carId);
    }
}