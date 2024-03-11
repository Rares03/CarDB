package com.cars.dbproject.controller;

import com.cars.dbproject.model.Aspiration;
import com.cars.dbproject.model.Car;
import com.cars.dbproject.repository.AspirationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class AspirationController {

    @Autowired
    private AspirationRepository aspirationRepository;

    @PostMapping("/aspiration")
    Aspiration newAspiration(@RequestBody Aspiration newAspiration){
        return aspirationRepository.save(newAspiration);
    }

    @GetMapping("/aspirations")
    List<Aspiration> getAllAspirations(){
        return aspirationRepository.findAll();
    }
}
