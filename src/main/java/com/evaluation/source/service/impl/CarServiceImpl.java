package com.evaluation.source.service.impl;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.evaluation.source.dto.car.CarRequest;
import com.evaluation.source.model.Car;
import com.evaluation.source.model.CarInfo;
import com.evaluation.source.model.Model;
import com.evaluation.source.model.User;
import com.evaluation.source.model.CarInfo.OrderStatus;
import com.evaluation.source.model.CarInfo.OrderType;
import com.evaluation.source.model.CarRepair;
import com.evaluation.source.repository.CarInfoRepository;
import com.evaluation.source.repository.CarRepairRepository;
import com.evaluation.source.repository.CarRepository;
import com.evaluation.source.repository.ModelRepository;
import com.evaluation.source.service.i.CarService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final CarInfoRepository carInfoRepository;
    private final CarRepairRepository carRepairRepository;
    private final ModelRepository modelRepository;

    @Override
    public Car createCar(CarRequest carRequest) {
        Model model = modelRepository.findById(carRequest.getModelId()).orElseThrow();

        return carRepository.save(Car.builder()
                                    .model(model)
                                    .version(carRequest.getVersion())
                                    .year(carRequest.getYear())
                                    .color(carRequest.getColor())
                                    .build());
    }

    @Override
    public CarInfo createCarInfo(User user, Car car, CarRequest carRequest) {
        return carInfoRepository.save(CarInfo.builder()
                                        .car(car)
                                        .user(user)
                                        .mileAge(carRequest.getMileAge())
                                        .plateNumber(carRequest.getPlateNumber())
                                        .price(carRequest.getPrice())
                                        .desc(carRequest.getDesc())
                                        .build());
    }

    @Override
    public Car getCarById(Long id){
        return carRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Car> getCar(int modelId, String version, Integer year, String color) {
        return carRepository.findCar(modelId, version, year, color);    
    }

    @Override
    public Car updateCar(CarRequest carRequest) {
        Car car = carRepository.findById(carRequest.getCarId()).orElseThrow();
        Optional<Model> model = modelRepository.findById(carRequest.getModelId());
        if (model.isPresent()) {
            car.setModel(model.get());
        }
        car.setVersion(carRequest.getVersion());
        car.setYear(carRequest.getYear());
        car.setColor(carRequest.getColor());
        return carRepository.save(car);
    }

    @Override
    public CarInfo getCarInfoById(Long id) {
        return carInfoRepository.findById(id).orElseThrow();
    }

    @Override
    public List<CarInfo> getCarInfo(User user, List<Car> car, Double minPrice, Double maxPrice, OrderStatus status, OrderType type) {
        List<CarInfo> cars = carInfoRepository.findCarInfo(user, car, minPrice, maxPrice, status, type);
        return cars;
    }

    @Override
    public CarInfo updateCarInfo(CarRequest carRequest) {
        CarInfo car = carInfoRepository.findById(carRequest.getCarInfoId()).orElseThrow();
        car.setCar(Car.builder().carId(carRequest.getCarId()).build());
        car.setDesc(carRequest.getDesc());
        // car.setImages(carRequest.getImages());
        car.setMileAge(carRequest.getMileAge());
        car.setPlateNumber(carRequest.getPlateNumber());
        car.setPrice(carRequest.getPrice());
        car.setStatus(OrderStatus.valueOf(carRequest.getStauts()));
        carInfoRepository.save(car);
        return car;
    }

    @Override
    public void DeleteCarInfoById(Long id) {
        CarInfo car = carInfoRepository.findById(id).orElseThrow();
        carInfoRepository.delete(car);
    }

    @Override
    public String valuation(CarRequest carRequest) {
        // List<Car> cars = carRepository.findCar(carRequest.getModelId(), carRequest.getVersion(), carRequest.getYear(), carRequest.getColor());

        Model model = modelRepository.findById(carRequest.getModelId()).orElseThrow();
        double yearReduce = (2024-carRequest.getYear())*7;
        double repairReduce = adjustPriceByRepairs(carRequest.getRepairAreas());
        double price = carRequest.getPrice() * (1 - (yearReduce+repairReduce)/100);

        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
        numberFormat.setMaximumFractionDigits(0);

        return model.getBrand().getName() + " " + model.getName()  + ": " + String.valueOf(numberFormat.format(price));
    }  

    public double adjustPriceByRepairs(List<Integer> carRepairs) {
        double totalReduction = 0.0;

        for (int repairName : carRepairs) {
            Optional<CarRepair> repairOptional = carRepairRepository.findById(repairName);

            if (repairOptional.isPresent()) {
                CarRepair repair = repairOptional.get();
                totalReduction += repair.getReductionRepair();  
            } else {
                System.out.println("Repair area not found: " + repairName);
            }
        }

        // Calculate the adjusted price
        return totalReduction;
    }
}
