package com.cars.dbproject.controller;

import com.cars.dbproject.model.Car;
import com.cars.dbproject.model.Model;
import com.cars.dbproject.repository.CarRepository;
import com.cars.dbproject.repository.EngineRepository;
import com.cars.dbproject.service.CarService;
import com.cars.dbproject.service.EngineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@CrossOrigin("http://localhost:3000")
public class CarController {

    private static final Logger LOGGER = Logger.getLogger(MakeController.class.getName());

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarService carService; // Inject EngineService

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }


    @PostMapping("/car")
    ResponseEntity<Car> newCar(@RequestBody Car newCar) {
        try {
            Car savedCar = carRepository.save(newCar);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCar);
        } catch (Exception e) {
            // Log the exception with stack trace
            LOGGER.log(Level.SEVERE, "Error saving make", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/cars")
    ResponseEntity<List<Car>> getAllCars() {
        try {
            List<Car> car = carRepository.findAll();

            return ResponseEntity.ok(car);
        } catch (Exception e) {
            // Log the exception with stack trace
            LOGGER.log(Level.SEVERE, "Error fetching Models", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/cars/{makeid}")
    public ResponseEntity<List<Car>> getCarsByMake(@PathVariable Integer makeid) {
        try {
            List<Car> cars = carRepository.findByMakeid(makeid);
            if (!cars.isEmpty()) {
                return ResponseEntity.ok(cars);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            // Log the exception with stack trace
            LOGGER.log(Level.SEVERE, "Error fetching Cars by Make", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/car/{id}")
    public ResponseEntity<Car> getCarsById(@PathVariable Integer id) {
        try {
            return carRepository.findById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            // Log the exception with stack trace
            LOGGER.log(Level.SEVERE, "Error fetching Cars by Make", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/dcar/{id}")
    public ResponseEntity<String> deleteCar(@PathVariable Integer id) {
        try {
            carService.deleteCarById(id); // Call on the instance
            return ResponseEntity.ok("Car deleted successfully");
        } catch (EmptyResultDataAccessException e) {
            // Handle the case where the engine with the given ID is not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Car not found");
        } catch (Exception e) {
            // Handle other exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting car");
        }
    }

    @PutMapping("/car/{id}")
    ResponseEntity<Car> updateCar(@PathVariable Integer id, @RequestBody Car updatedCar) {
        try {
            // Check if the car with the given ID exists
            Optional<Car> existingCarOptional = carRepository.findById(id);

            if (existingCarOptional.isPresent()) {
                // Update the existing car with the new values
                Car existingCar = existingCarOptional.get();
                existingCar.setMake(updatedCar.getMake());
                existingCar.setModel(updatedCar.getModel());
                // Add more fields to update as needed

                // Save the updated car
                Car savedCar = carRepository.save(existingCar);

                // Return a response with HTTP status 200 (OK) and the updated car in the body
                return ResponseEntity.ok(savedCar);
            } else {
                // If the car with the given ID is not found, return HTTP status 404 (NOT_FOUND)
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            // If there is an exception during the update operation, log the exception with stack trace
            LOGGER.log(Level.SEVERE, "Error updating car", e);

            // Return a response with HTTP status 500 (INTERNAL_SERVER_ERROR) and a null body
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
