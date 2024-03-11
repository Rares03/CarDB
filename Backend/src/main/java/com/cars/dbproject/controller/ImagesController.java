package com.cars.dbproject.controller;

import com.cars.dbproject.model.Images;
import com.cars.dbproject.repository.ImagesRepository;
import com.cars.dbproject.service.ImagesService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@CrossOrigin("http://localhost:3000")
public class ImagesController {

    Logger logger = LoggerFactory.getLogger(ImagesController.class);


    @Autowired
    private ImagesRepository imagesRepository;

    @Autowired
    private ImagesService imagesService; // Inject EngineService

    @Autowired
    public ImagesController(ImagesService imagesService) {
        this.imagesService = imagesService;
    }

    @PostMapping("/upload2")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            // Save the file to a specific directory on the server
            String uploadDir = "D:/Info/Projects/cars/src/pages/cars/";
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

    @DeleteMapping("/dimage2/{filename}")
    public ResponseEntity<String> deleteImage(@PathVariable String filename) {
        try {
            // Specify the directory where the images are stored
            String uploadDir = "D:/Info/Projects/cars/src/pages/cars/";
            Path imagePath = Path.of(uploadDir, filename);

            logger.info("Attempting to delete image at path: {}", imagePath);

            // Delete the file
            Files.deleteIfExists(imagePath);

            return ResponseEntity.ok("Image deleted successfully");
        } catch (IOException e) {
            // Handle the exception appropriately
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting image");
        }
    }


    @PostMapping("/image")
    ResponseEntity<Images> newImages(@RequestBody Images newImages) {
        try {
            Images savedImages = imagesRepository.save(newImages);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedImages);
        } catch (Exception e) {
            // Log the exception with stack trace
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/images")
    List<Images> getAllImages(){
        return imagesRepository.findAll();
    }

    @DeleteMapping("/dimages/{imageid}")
    public ResponseEntity<String> deleteImages(@PathVariable Integer imageid) {
        try {
            imagesService.deleteImagesById(imageid); // Call on the instance
            return ResponseEntity.ok("Image deleted successfully");
        } catch (EmptyResultDataAccessException e) {
            // Handle the case where the engine with the given ID is not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Image not found");
        } catch (Exception e) {
            // Handle other exceptions
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting image");
        }
    }
}
