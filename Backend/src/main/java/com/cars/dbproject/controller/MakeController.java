package com.cars.dbproject.controller;

import com.cars.dbproject.model.Make;
import com.cars.dbproject.repository.MakeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@CrossOrigin("http://localhost:3000")
public class MakeController {

    private static final Logger LOGGER = Logger.getLogger(MakeController.class.getName());

    @Autowired
    private MakeRepository makeRepository;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            // Save the file to a specific directory on the server
            String uploadDir = "D:/Info/Projects/cars/src/pages/img/";
            String fileName = file.getOriginalFilename();
            Path uploadPath = Path.of(uploadDir);
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            return ResponseEntity.status(HttpStatus.CREATED).body("File uploaded successfully");
        } catch (IOException e) {
            // Handle the exception appropriately
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading file");
        }
    }

    @PostMapping("/make")
    ResponseEntity<Make> newMake(@RequestBody Make newMake) {
        try {
            Make savedMake = makeRepository.save(newMake);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedMake);
        } catch (Exception e) {
            // Log the exception with stack trace
            LOGGER.log(Level.SEVERE, "Error saving make", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/makes")
    ResponseEntity<List<Make>> getAllMakes() {
        try {
            // Define the sorting order based on the 'name' field in ascending order
            Sort sort = Sort.by(Sort.Direction.ASC, "makename");

            // Fetch makes sorted alphabetically
            List<Make> makes = makeRepository.findAll(sort);

            return ResponseEntity.ok(makes);
        } catch (Exception e) {
            // Log the exception with stack trace
            LOGGER.log(Level.SEVERE, "Error fetching makes", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/make/{id}")
    ResponseEntity<Make> getMakeById(@PathVariable Integer id) {
        try {
            // Retrieve the make by ID
            return makeRepository.findById(id+1)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            // Log the exception with stack trace
            LOGGER.log(Level.SEVERE, "Error fetching make by ID", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}