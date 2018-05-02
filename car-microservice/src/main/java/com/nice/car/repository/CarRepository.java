package com.nice.car.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nice.car.model.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long>
{

    Car findByPlateNumber(String plateNumber);

}
