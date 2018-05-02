package com.nice.car.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.nice.car.exception.CarNotFoundException;
import com.nice.car.model.Car;
import com.nice.car.repository.CarRepository;
import com.nice.car.service.CarService;

@Service
@Transactional
public class CarServiceImpl implements CarService
{

    private CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository)
    {
        this.carRepository = carRepository;
    }

    @Override
    public Car findOne(Long id)
    {
        Optional<Car> car = carRepository.findById(id);
        if (!car.isPresent())
            throw new CarNotFoundException(String.format("Car with id %s not found", id));
        return car.get();
    }

    @Override
    public Car findOne(String plateNumber)
    {
        return carRepository.findByPlateNumber(plateNumber);
    }

    @Override
    public List<Car> findAll()
    {
        return carRepository.findAll();
    }

    @Override
    public Car create(Car car)
    {
        return carRepository.save(car);
    }

    @Override
    public void update(Long id, Car car)
    {
        Car fetchedCar = findOne(id);
        fetchedCar.setAvailability(car.getAvailability());
        fetchedCar.setDailyPrice(car.getDailyPrice());
        fetchedCar.setModel(car.getModel());
        fetchedCar.setPlateNumber(car.getPlateNumber());
        fetchedCar.setType(car.getType());
        carRepository.save(fetchedCar);
    }

    @Override
    public void delete(Long id)
    {
        findOne(id);
        carRepository.deleteById(id);

    }

}
