//package com.example.ITMO_practice.service.impl;
//import com.example.ITMO_practice.model.db.entity.Car;
//import com.example.ITMO_practice.model.db.repository.CarRepository;
//import com.example.ITMO_practice.model.dto.request.CarInfoRequest;
//import com.example.ITMO_practice.model.dto.response.CarInfoResponse;
//import com.example.ITMO_practice.model.enums.CarStatus;
//import com.example.ITMO_practice.service.CarService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//@Slf4j
//
//public class CarServiceImpl implements CarService {
//    private final ObjectMapper mapper;
//    private final CarRepository carRepository;
//
//    @Override
//    public CarInfoResponse addCar(CarInfoRequest carInfoRequest) {
//       Car car = mapper.convertValue(carInfoRequest, Car.class);
//       car.setStatus(CarStatus.CREATED);
//
//       Car save = carRepository.save(car);
//       return mapper.convertValue(save, CarInfoResponse.class);
//
//    }
//    private Car getCarFormDB(long id) {
//        Optional<Car> optionalCar = carRepository.findById(id);
//        return optionalCar.orElse(new Car());
//    }
//    @Override
//    public CarInfoResponse getCarInfo(long carId) {
//        Car car = getCarFormDB(carId);
//        return mapper.convertValue(car, CarInfoResponse.class);
//    }
//
//    @Override
//    public CarInfoResponse updateCar(long carId, CarInfoRequest carInfoRequest) {
//        Car carFromDB = getCarFormDB(carId);
//        if (carFromDB == null) {
//            return mapper.convertValue(carFromDB, CarInfoResponse.class);
//        }
//
//        Car carReq = mapper.convertValue(carInfoRequest, Car.class);
//
//        carFromDB.setBrand(carReq.getBrand() == null ? carFromDB.getBrand() : carReq.getBrand());
//        carFromDB.setModel(carReq.getModel() == null ? carFromDB.getModel() : carReq.getModel());
//        carFromDB.setColor(carReq.getColor() == null ? carFromDB.getColor() : carReq.getColor());
//        carFromDB.setYear(carReq.getYear() == null ? carFromDB.getYear() : carReq.getYear());
//        carFromDB.setPrice(carReq.getPrice() == null ? carFromDB.getPrice() : carReq.getPrice());
//        carFromDB.setIsNew(carReq.getIsNew() == null ? carFromDB.getIsNew() : carReq.getIsNew());
//        carFromDB.setType(carReq.getType() == null ? carFromDB.getType() : carReq.getType());
//
//        carFromDB = carRepository.save(carFromDB);
//        return mapper.convertValue(carFromDB, CarInfoResponse.class);
//    }
//
//    @Override
//    public void deleteCar(long carId) {
//        Car carFromDB = getCarFormDB(carId);
//        if (carFromDB == null) {
//            log.error("User with id {} not found", carId);
//            return;
//        }
//
//        carFromDB.setStatus(CarStatus.DELETED);
//        carRepository.save(carFromDB);
//    }
//}
