package com.evaluation.source.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.evaluation.source.model.Car;
import com.evaluation.source.model.CarInfo;
import com.evaluation.source.model.User;
import com.evaluation.source.model.CarInfo.OrderStatus;
import com.evaluation.source.model.CarInfo.OrderType;

import java.util.List;

public interface CarInfoRepository extends JpaRepository<CarInfo, Long> {

    @Query("SELECT c FROM CarInfo c WHERE (:user IS NULL OR c.user = :user) AND (:cars IS NULL OR c.car IN :cars) AND(:status IS NULL OR c.status = :stauts) AND(:type IS NULL OR c.orderType = :type) AND (:min IS NULL OR c.price >= :min) AND (:max IS NULL OR c.price <= :max)")
    List<CarInfo> findCarInfo(User user, List<Car> cars, Double min, Double max, OrderStatus status, OrderType type);
}
