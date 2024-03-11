package com.cars.dbproject.service;

import com.cars.dbproject.repository.EngineRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EngineService {

    private final EngineRepository engineRepository;

    @Autowired
    public EngineService(EngineRepository engineRepository) {
        this.engineRepository = engineRepository;
    }

    @Transactional
    public void deleteEngineById(Integer engineId) {
        // Implement your logic to delete the engine
        engineRepository.deleteById(engineId);
    }
}