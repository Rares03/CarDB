package com.cars.dbproject.controller;

import com.cars.dbproject.model.Car;
import com.cars.dbproject.model.Configuration;
import com.cars.dbproject.repository.ConfigurationRepository;
import com.cars.dbproject.service.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@CrossOrigin("http://localhost:3000")
public class ConfigurationController {

    private static final Logger LOGGER = Logger.getLogger(MakeController.class.getName());

    @Autowired
    private ConfigurationRepository configurationRepository;

    @Autowired
    private ConfigurationService configurationService; // Inject EngineService

    @Autowired
    public ConfigurationController(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    @PostMapping("/configuration")
    ResponseEntity<Configuration> newConfiguration(@RequestBody Configuration newConfiguration) {
        try {
            Configuration savedConfiguration = configurationRepository.save(newConfiguration);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedConfiguration);
        } catch (Exception e) {
            // Log the exception with stack trace
            LOGGER.log(Level.SEVERE, "Error saving make", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/configurations")
    List<Configuration> getAllConfigurations(){
        return configurationRepository.findAll();
    }

    @DeleteMapping("/dconfiguration/{configid}")
    public ResponseEntity<String> deleteConfiguration(@PathVariable Integer configid) {
        try {
            configurationService.deleteConfigurationById(configid); // Call on the instance
            return ResponseEntity.ok("Configuration deleted successfully");
        } catch (EmptyResultDataAccessException e) {
            // Handle the case where the engine with the given ID is not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Configuration not found");
        } catch (Exception e) {
            // Handle other exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting configuration");
        }
    }
}
