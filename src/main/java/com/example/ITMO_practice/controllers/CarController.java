package com.example.ITMO_practice.controllers;

import com.example.ITMO_practice.model.dto.request.CarInfoRequest;
import com.example.ITMO_practice.model.dto.response.CarInfoResponse;
import com.example.ITMO_practice.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @GetMapping("/{carId}")
    public CarInfoResponse getCar(@PathVariable long carId) {
        return carService.getCar(carId);
//        return CarInfoResponse.builder()
//                .brand("Audi")
//                .model("TT")
//                .color(Color.RED)
//                .year(3)
//                .price(1535000L)
//                .isNew(true)
//                .type(CarType.SEDAN)
//                .build();
    }
    @PostMapping("/{carId}")
    public CarInfoResponse addCar(@PathVariable long carId) {
       return carService.getCar(carId);
    }

    @PutMapping("/{id}")
    public CarInfoResponse updateCar(@PathVariable long carId, @RequestBody CarInfoRequest carInfoRequest) {
        return carService.updateCar(carId, carInfoRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable long carId) {
        carService.deleteCar(carId);
    }

//    @GetMapping("/all")
//    public List<CarInfoResponse> getAllCars() {
//        return userService.getAllCars();
//    }

    @GetMapping("/all/{userId}")
    public List<CarInfoResponse> getAllCars(@PathVariable Long userId) {
        return carService.getUserCars(userId);
    }

    @PostMapping("/linkUserAndDriver/{carId}/{userId}")
    public CarInfoResponse linkUserAndDriver(@PathVariable Long carId, @PathVariable Long userId) {
        return carService.linkCarAndDriver(carId, userId);
    }
    @GetMapping("/all")
    public PageImpl<CarInfoResponse> getAllCars(@RequestParam(defaultValue = "1") Integer page,
                                                @RequestParam(defaultValue = "10") Integer perPage,
                                                @RequestParam(defaultValue = "lastName") String sort,
                                                @RequestParam(defaultValue = "ASC") Sort.Direction order,
                                                @RequestParam(required = false) String filter

    ) {
        return carService.getAllCars(page, perPage, sort, order, filter);
    }

}
