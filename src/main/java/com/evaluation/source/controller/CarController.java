package com.evaluation.source.controller;

import com.evaluation.source.base.BaseController;
import com.evaluation.source.dto.base.ResultResponse;
import com.evaluation.source.dto.car.CarDetail;
import com.evaluation.source.dto.car.CarRequest;
import com.evaluation.source.model.Brand;
import com.evaluation.source.model.Car;
import com.evaluation.source.model.CarInfo;
import com.evaluation.source.model.CarRepair;
import com.evaluation.source.model.Model;
import com.evaluation.source.model.User;
import com.evaluation.source.repository.BrandRepository;
import com.evaluation.source.repository.CarRepairRepository;
import com.evaluation.source.repository.ModelRepository;
import com.evaluation.source.service.i.CarService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/car")
@RequiredArgsConstructor
public class CarController extends BaseController{

    private final CarService carService;
    private final CarRepairRepository carRepairRepository;
    private final BrandRepository brandRepository;
    private final ModelRepository modelRepository;

    @GetMapping("/getCarRepairs")
    public ResponseEntity<ResultResponse> getAllCarRepairs() {
        List<CarRepair> carRepairs = carRepairRepository.findAll();
        return buildResponse("Car Repair list",carRepairs);
    }

    @PostMapping("/valuation")
    public ResponseEntity<ResultResponse> valuation(@RequestBody CarRequest carRequest) {
        System.err.println(carRequest.getYear()+ "---------------------------------------------");
        CarDetail car = carService.valuation(carRequest);
        return buildResponse("Car value", car);
    }

    @GetMapping("/brands")
    public ResponseEntity<ResultResponse> getAllBrands() {
        List<Brand> brands = brandRepository.findAll();
        return buildResponse("List Brand", brands);
    }

    @GetMapping("/brand/{id}")
    public ResponseEntity<ResultResponse> getModels(@PathVariable int id) {
        List<Model> model = modelRepository.findByBrand(brandRepository.findById(id).get());
        return buildResponse("List Model", model);
    }

    @GetMapping("/brand/model/{id}")
    public ResponseEntity<ResultResponse> getCars(@PathVariable int id) {
        List<Car> cars = carService.getCar(id, null, null, null);
        return buildResponse("List Car", cars);
    }

    @GetMapping("/getCars/{id}")
    public ResponseEntity<ResultResponse> getAllCars(@PathVariable Long id) {
        Car car = carService.getCarById(id);
        return buildResponse("Car", car);
    }

    @GetMapping("/findCars")
    public ResponseEntity<ResultResponse> findCars(CarRequest car) {
        List<Car> cars = carService.getCar(car.getModelId(),
                                 car.getVersion(),
                                 car.getYear(), 
                                 car.getColor());
        return buildResponse("Founded Car", cars);
    }

    @PostMapping("/addCar")
    public ResponseEntity<ResultResponse> addCar(Authentication authentication, @RequestBody CarRequest carRequest) {
        var currentUser = (User) authentication.getPrincipal();
        Car car = carService.createCar(carRequest);
        CarInfo carInfo = carService.createCarInfo(currentUser, car, carRequest);
        return buildResponse("Created Successfully", carInfo);
    }

    @PutMapping("/updateCar/{id}")
    public ResponseEntity<ResultResponse> updateCar(@PathVariable UUID id, @RequestBody CarRequest carRequest) {
        Car car = carService.updateCar(carRequest);
        return buildResponse("Update Successfully", car);
    }

    @GetMapping("/getCarInfo/{id}")
    public ResponseEntity<ResultResponse> getCarById(@PathVariable Long id) {
        CarInfo car = carService.getCarInfoById(id);
        return buildResponse("Car Info", car);
    }

    @PutMapping("/updateCarInfo")
    public ResponseEntity<ResultResponse> getCarById(@RequestBody CarRequest carRequest) {
        CarInfo car = carService.updateCarInfo(carRequest);
        return buildResponse("Car Info", car);
    }

    @GetMapping("/getCarRepair/{id}")
    public ResponseEntity<ResultResponse> getCarRepairById(@PathVariable Integer id) {
        CarRepair carRepair = carRepairRepository.findById(id).orElseThrow();
        return buildResponse("Car Repair", carRepair);
    }

    @PutMapping("/updateCarRepair/{id}")
    public ResponseEntity<ResultResponse> updateCarRepair(@PathVariable Integer id, @RequestBody CarRepair carRepair) {
        CarRepair saveCar = carRepairRepository.findById(id).orElseThrow();

        saveCar.setNameRepair(carRepair.getNameRepair());
        saveCar.setReductionRepair(carRepair.getReductionRepair());

        carRepairRepository.save(saveCar);
        return buildResponse("Update Successfully");
        
    }
}
