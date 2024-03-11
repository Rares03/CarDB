package com.cars.dbproject.controller;

import com.cars.dbproject.model.Model;
import com.cars.dbproject.repository.ModelRepository;
import com.cars.dbproject.service.ModelService;
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
public class ModelController {

    private static final Logger LOGGER = Logger.getLogger(MakeController.class.getName());

    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private ModelService modelService; // Inject EngineService

    @Autowired
    public ModelController(ModelService modelService) {
        this.modelService = modelService;
    }

    @PostMapping("/model")
    ResponseEntity<Model> newModel(@RequestBody Model newModel) {
        try {
            Model savedModel = modelRepository.save(newModel);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedModel);
        } catch (Exception e) {
            // Log the exception with stack trace
            LOGGER.log(Level.SEVERE, "Error saving make", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/models")
    ResponseEntity<List<Model>> getAllModels() {
        try {
            List<Model> model = modelRepository.findAll();

            return ResponseEntity.ok(model);
        } catch (Exception e) {
            // Log the exception with stack trace
            LOGGER.log(Level.SEVERE, "Error fetching Models", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/model/{id}")
    ResponseEntity<Model> getModelById(@PathVariable Integer id) {
        try {
            return modelRepository.findById(id+1)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            // Log the exception with stack trace
            LOGGER.log(Level.SEVERE, "Error fetching make by ID", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/dmodel/{modelid}")
    public ResponseEntity<String> deleteModel(@PathVariable Integer modelid) {
        try {
            modelService.deleteModelById(modelid); // Call on the instance
            return ResponseEntity.ok("Model deleted successfully");
        } catch (EmptyResultDataAccessException e) {
            // Handle the case where the engine with the given ID is not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Model not found");
        } catch (Exception e) {
            // Handle other exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting model");
        }
    }

}
