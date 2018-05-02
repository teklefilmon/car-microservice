package com.nice.car.service;

import java.util.List;

import com.nice.car.model.Car;

public interface CarService
{
    Car findOne(Long id);

    Car findOne(String plateNumber);

    List<Car> findAll();

    Car create(Car car);

    void update(Long id, Car car);

    void delete(Long id);
}
