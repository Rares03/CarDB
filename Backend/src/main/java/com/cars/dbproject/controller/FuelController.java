package com.cars.dbproject.controller;

import com.cars.dbproject.model.Car;
import com.cars.dbproject.model.Fuel;
import com.cars.dbproject.repository.FuelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class FuelController {

    @Autowired
    private FuelRepository fuelRepository;

    @PostMapping("/fuel")
    Fuel newCar(@RequestBody Fuel newFuel){
        return fuelRepository.save(newFuel);
    }

    @GetMapping("/fuels")
    List<Fuel> getAllFuels(){
        return fuelRepository.findAll();
    }
}
