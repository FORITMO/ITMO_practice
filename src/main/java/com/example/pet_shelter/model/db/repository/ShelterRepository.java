package com.example.pet_shelter.model.db.repository;

import com.example.pet_shelter.model.db.entity.Shelter;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ShelterRepository extends JpaRepository<Shelter, Long> {

        List<Shelter> findByPhoneContaining(String phone);

        @EntityGraph(attributePaths = {"pets"})
        @Query("SELECT s FROM Shelter s WHERE s.id = :id")
        Optional<Shelter> findByIdWithPets(@Param("id") Long id);

        boolean existsByName(String name);
    }

