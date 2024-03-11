package com.cars.dbproject.service;

import com.cars.dbproject.repository.ImagesRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImagesService {

    private final ImagesRepository imagesRepository;

    @Autowired
    public ImagesService(ImagesRepository imagesRepository) {
        this.imagesRepository = imagesRepository;
    }

    @Transactional
    public void deleteImagesById(Integer imagesId) {
        // Implement your logic to delete the engine
        imagesRepository.deleteById(imagesId);
    }
}