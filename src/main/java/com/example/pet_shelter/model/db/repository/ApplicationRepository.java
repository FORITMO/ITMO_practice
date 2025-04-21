package com.example.pet_shelter.model.db.repository;

import com.example.pet_shelter.model.db.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
}
