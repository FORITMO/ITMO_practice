package com.example.pet_shelter.service.impl;

import com.example.pet_shelter.model.db.entity.Application;
import com.example.pet_shelter.model.db.repository.ApplicationRepository;
import com.example.pet_shelter.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
@Slf4j
@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {
    private final ApplicationRepository applicationRepository;

    @Override
    public Application create(Application application) {
        // Здесь можно добавить дополнительную логику валидации
        return applicationRepository.save(application);
    }
}
