package com.cars.dbproject.service;

import com.cars.dbproject.repository.ConfigurationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfigurationService {

    private final ConfigurationRepository configurationRepository;

    @Autowired
    public ConfigurationService(ConfigurationRepository configurationRepository) {
        this.configurationRepository = configurationRepository;
    }

    @Transactional
    public void deleteConfigurationById(Integer configurationId) {
        // Implement your logic to delete the engine
        configurationRepository.deleteById(configurationId);
    }
}