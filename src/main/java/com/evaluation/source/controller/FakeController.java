package com.evaluation.source.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evaluation.source.base.BaseController;
import com.evaluation.source.dto.base.ResultResponse;
import com.evaluation.source.model.Car;
import com.evaluation.source.model.CarInfo;
import com.evaluation.source.model.Model;
import com.evaluation.source.repository.CarInfoRepository;
import com.evaluation.source.repository.CarRepository;
import com.evaluation.source.repository.ModelRepository;
import com.evaluation.source.repository.UserRepository;
import com.github.javafaker.Faker;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/fake")
@RequiredArgsConstructor
public class FakeController extends BaseController{
    private final PasswordEncoder encoder;
    // private final UserRepository userRepository;
    private final CarRepository carRepository;
    private final CarInfoRepository carInfoRepository;
    private final ModelRepository modelRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<ResultResponse> fake(@RequestBody String body) {
        Locale locale = new Locale("vi");
        // User cus = userRepository.findById(UUID.fromString("257e376a-0142-4cbc-8d64-fc68e6387540")).orElse(null);
        // User caro = userRepository.findById(UUID.fromString("257e376a-0142-4cbc-8d64-fc68e6387549")).orElse(null);
        List<Model> models = modelRepository.findAll();
        List<String> colors = List.of("White", "Black", "Gray", "Silver", "Red", "Blue", "Brown", "Green" ,"Beige" ,"Gold" ,"Yellow" ,"Purple");
        Faker faker = new Faker(locale);
        if("car".equals(body))
            IntStream.range(0, 100).forEach(i -> {
                Car car = Car.builder()
                        .color(colors.get(faker.number().numberBetween(0, colors.size())))
                        .model(models.get(faker.number().numberBetween(0, models.size())))
                        .version(null)
                        .year(faker.number().numberBetween(2000, 2022))
                        .build();
                carRepository.save(car);
            });
        List<Car> cars = carRepository.findAll();
        if("carinfo".equals(body))
            IntStream.range(0, 100).forEach(i -> {
                //carimage
                CarInfo carInfo = CarInfo.builder()
                        .car(cars.get(faker.number().numberBetween(0, cars.size())))
                        .plateNumber(faker.regexify("[0-9]{2}[A-Z]{1}-[0-9]{4,5}"))
                        .mileAge(Integer.valueOf(faker.number().numberBetween(0, 100000)))
                        .desc(faker.lorem().sentence(100))
                        .price(Double.valueOf(faker.number().randomDouble(0, 500000000L, 10000000000L)))
                        // .images("https://source.unsplash.com/1600x900/?car-" + brands.get(faker.number().numberBetween(0, brands.size())) + "," +
                        //         "https://source.unsplash.com/1600x900/?car-" + models.get(faker.number().numberBetween(0, models.size()))
                        // )
                        .build();
                carInfoRepository.save(carInfo);
            });
        return buildResponse("Fake response");
    }
}
