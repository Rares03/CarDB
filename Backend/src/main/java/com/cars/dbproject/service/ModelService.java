package com.cars.dbproject.service;

import com.cars.dbproject.repository.ModelRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModelService {

    private final ModelRepository modelRepository;

    @Autowired
    public ModelService(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    @Transactional
    public void deleteModelById(Integer modelId) {
        // Implement your logic to delete the engine
        modelRepository.deleteById(modelId);
    }
}