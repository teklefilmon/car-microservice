package com.nice.car.controller;

import java.net.URI;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nice.car.model.Car;
import com.nice.car.service.CarService;

@RestController
public class CarController {

	private CarService carService;

	public CarController(CarService service) {
		Objects.requireNonNull(service, "CarService is required for the class to function");
		this.carService = service;
	}

	@GetMapping(value = "/cars/{idOrPlateNumber}")
	ResponseEntity<Car> getCar(@PathVariable String idOrPlateNumber) {
		Car car = null;
		try {
			Long id = Long.parseLong(idOrPlateNumber);
			car = carService.findOne(id);
		} catch (NumberFormatException exception) {
			car = carService.findOne(idOrPlateNumber);
		}
		return new ResponseEntity<>(car, HttpStatus.OK);
	}

	@GetMapping(value = "/cars")
	ResponseEntity<List<Car>> getAllCars() {
		return new ResponseEntity<>(carService.findAll(), HttpStatus.OK);
	}

	@PostMapping(value = "/cars")
	ResponseEntity<Void> createCar(@Valid @RequestBody Car car) {
		Car newCar = carService.create(car);
		HttpHeaders responseHeaders = new HttpHeaders();
		URI newUserUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newCar.getId())
				.toUri();
		responseHeaders.setLocation(newUserUri);
		return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
	}

	@PutMapping(value = "/cars/{id}")
	ResponseEntity<Void> updateCar(@PathVariable Long id, @RequestBody @Valid Car car) {
		carService.update(id, car);
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@DeleteMapping(value = "/cars/{id}")
	ResponseEntity<Void> deleteUser(@PathVariable Long id) {
		carService.delete(id);
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}
}
