package com.evaluation.source.service.i;

import com.evaluation.source.dto.car.CarRequest;
import com.evaluation.source.model.Car;
import com.evaluation.source.model.CarInfo;
import com.evaluation.source.model.User;
import com.evaluation.source.model.CarInfo.OrderStatus;
import com.evaluation.source.model.CarInfo.OrderType;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface CarService {

    public Car createCar(CarRequest carRequest);

    public Car getCarById(Long id);

    public List<Car> getCar(int modelId, String version, Integer year, String color);
   
    public Car updateCar(CarRequest carRequest);

    public CarInfo getCarInfoById(Long id);

    public CarInfo createCarInfo(User user, Car car, CarRequest carRequest);

    public List<CarInfo> getCarInfo(User user, List<Car> car, Double minPrice, Double maxPrice, OrderStatus status, OrderType type);

    public CarInfo updateCarInfo(CarRequest carRequest);

    public void DeleteCarInfoById(Long id);

    public String valuation(CarRequest carRequest);
}
