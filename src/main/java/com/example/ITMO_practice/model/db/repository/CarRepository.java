package com.example.ITMO_practice.model.db.repository;

import com.example.ITMO_practice.model.db.entity.Car;
import com.example.ITMO_practice.model.db.entity.User;
import com.example.ITMO_practice.model.enums.CarStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    Car findByBrand(String brand);

    List<Car> findAllByStatus(CarStatus status);

//    @Query(nativeQuery = true, value = "select * from cars where true order by cars.update_at desc limit 1")
//    Car findLastByUpdateDate();
//
//    @Query("select c from Car c where c.status <> :status")
//    List<Car> findAllByStatusNot(@Param("status") String status);

    @Query("select u.cars from User u where u.id = :userId")
    List<Car> getUserCarsBrandNames(@Param("userId") Long userId);


    @Query("select  c from Car c where c.model like %:filter% or c.brand like %:filter%")
    Page<Car> findAllFilteredCar(Pageable pageRequest, @Param("filter") String filter);
}
