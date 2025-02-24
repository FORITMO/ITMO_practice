package com.example.ITMO_practice.model.db.repository;

import com.example.ITMO_practice.model.db.entity.Car;
import com.example.ITMO_practice.model.db.entity.User;
import com.example.ITMO_practice.model.enums.UserStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User getUserByAgeAndEmailAndStatus(Integer age, String email, UserStatus status);

    Optional<User> findByEmail(String email);

    @Query("select  u from User u where u.lastName like %:filter% or u.name like %:filter%")
    Page<User> findAllFiltered(Pageable pageRequest, @Param("filter") String filter);


    @Query("select c from Car c where c.status <> :status")
    List<Car> findAllByStatusNot(@Param("status") String status);

    @Query("select u.cars from User u where u.id = :userId")
    List<Car> getUserCarsBrandNames(@Param("userId") Long userId);
}
