package com.cars.dbproject.controller;

import com.cars.dbproject.model.Engine;
import com.cars.dbproject.service.EngineService;
import com.cars.dbproject.repository.EngineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class EngineController {

    private static final Logger LOGGER = Logger.getLogger(MakeController.class.getName());

    @Autowired
    private EngineRepository engineRepository;

    @Autowired
    private EngineService engineService; // Inject EngineService

    @Autowired
    public EngineController(EngineService engineService) {
        this.engineService = engineService;
    }

    @PostMapping("/engine")
    ResponseEntity<Engine> newEngine(@RequestBody Engine newEngine) {
        try {
            Engine savedEngine = engineRepository.save(newEngine);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedEngine);
        } catch (Exception e) {
            // Log the exception with stack trace
            LOGGER.log(Level.SEVERE, "Error saving make", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/engines")
    ResponseEntity<List<Engine>> getAllEngines() {
        try {
            List<Engine> engine = engineRepository.findAll();

            return ResponseEntity.ok(engine);
        } catch (Exception e) {
            // Log the exception with stack trace
            LOGGER.log(Level.SEVERE, "Error fetching Models", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/dengine/{engineid}")
    public ResponseEntity<String> deleteEngine(@PathVariable Integer engineid) {
        try {
            engineService.deleteEngineById(engineid); // Call on the instance
            return ResponseEntity.ok("Engine deleted successfully");
        } catch (EmptyResultDataAccessException e) {
            // Handle the case where the engine with the given ID is not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Engine not found");
        } catch (Exception e) {
            // Handle other exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting engine");
        }
    }
}
