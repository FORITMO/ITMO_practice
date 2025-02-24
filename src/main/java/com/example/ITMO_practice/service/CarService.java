package com.example.ITMO_practice.service;

import com.example.ITMO_practice.exception.CommonBackendException;
import com.example.ITMO_practice.model.dto.request.CarInfoRequest;
import com.example.ITMO_practice.model.dto.response.CarInfoResponse;
import com.example.ITMO_practice.model.db.entity.Car;
import com.example.ITMO_practice.model.db.entity.User;
import com.example.ITMO_practice.model.db.repository.CarRepository;
import com.example.ITMO_practice.model.dto.response.UserInfoResponse;
import com.example.ITMO_practice.model.enums.CarStatus;
import com.example.ITMO_practice.model.enums.UserStatus;
import com.example.ITMO_practice.utils.PaginationUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarService {
    private final UserService userService;
    private final CarRepository carRepository;
    private final ObjectMapper objectMapper;


    public CarInfoResponse getCar(Long id) {
        final String errMsg = String.format("Car with id %s not found", id);
        return carRepository.findById(id)
                .map(car -> objectMapper.convertValue(car, CarInfoResponse.class))
                .orElseThrow(() -> {
                    log.error("Car with id {} not found", id);
                    return new CommonBackendException(errMsg, HttpStatus.NOT_FOUND);
                });
    }

    public CarInfoResponse addCar(CarInfoRequest req) {
        Car car = objectMapper.convertValue(req, Car.class);
        Car savedCar = carRepository.save(car);
        return objectMapper.convertValue(savedCar, CarInfoResponse.class);
    }

    @SneakyThrows
    public CarInfoResponse updateCar(Long id, CarInfoRequest req) {
        Car existingCar = carRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Car with id {} not found", id);
                    return new CommonBackendException("Car already exists ", HttpStatus.BAD_REQUEST);
                });
        objectMapper.updateValue(existingCar, req);
        Car updatedCar = carRepository.save(existingCar);
        return objectMapper.convertValue(updatedCar, CarInfoResponse.class);
    }

    public void deleteCar(long id) {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Car with id {} not found for deletion", id);
                    return new CommonBackendException("Car not found with id: ", HttpStatus.BAD_REQUEST);
                });
        carRepository.delete(car);
    }

    public List<CarInfoResponse> getAllCars() {
        return null;
    }

    private Car getCarFromDB(Long carId) {

        return carRepository.findById(carId).orElseThrow(() ->
                new CommonBackendException("Car not found with id: ", HttpStatus.BAD_GATEWAY));
    }

    public CarInfoResponse linkCarAndDriver(Long carId, Long userId) {
        Car carFromDB = getCarFromDB(carId);
        User userFromDB = userService.getUserFromDB(userId);
        if (carFromDB.getCarId() == null || userFromDB.getId() == null) {
            return CarInfoResponse.builder().build();
        }
        List<Car> cars = userFromDB.getCars();
        Car existingCar = cars.stream().filter(car -> car.getCarId().equals(carId)).findFirst().orElse(null);
        if (existingCar != null) {
            return objectMapper.convertValue(existingCar, CarInfoResponse.class);
        }
        cars.add(carFromDB);
        User user = userService.updateCarList(userFromDB);

        carFromDB.setUser(user);
        carRepository.save(carFromDB);

        CarInfoResponse carInfoResp = objectMapper.convertValue(carFromDB, CarInfoResponse.class);
        UserInfoResponse userInfoResp = objectMapper.convertValue(user, UserInfoResponse.class);

        carInfoResp.setUser(userInfoResp);

        return carInfoResp;
    }

    public List<CarInfoResponse> getUserCars(Long userId) {
        return carRepository.getUserCarsBrandNames(userId).stream()
                .map(car -> objectMapper.convertValue(car, CarInfoResponse.class))
                .collect(Collectors.toList());
    }


    private static List<Car> getCars(User userFromDB) {
        return userFromDB.getCars();
    }


    public PageImpl<CarInfoResponse> getAllCars(Integer page, Integer perPage, String sort, Sort.Direction order, String filter) {
        Pageable pageRequest = PaginationUtils.getPageRequest(page, perPage, sort, order);

        Page<Car> cars;

        if (StringUtils.hasText(filter)) {
            cars = carRepository.findAllFilteredCar(pageRequest, filter);
        } else {
            cars = carRepository.findAll(pageRequest);
        }

        List<CarInfoResponse> content = cars.getContent().stream()
                .map(c -> objectMapper.convertValue(c, CarInfoResponse.class))
                .collect(Collectors.toList());

        return new PageImpl<>(content, pageRequest, cars.getTotalElements());
    }

}