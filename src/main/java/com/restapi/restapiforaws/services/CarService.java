package com.restapi.restapiforaws.services;

import com.restapi.restapiforaws.entities.CarEntity;
import com.restapi.restapiforaws.mappers.CarMapper;
import com.restapi.restapiforaws.models.CarModel;
import com.restapi.restapiforaws.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CarService {

    private final CarMapper carMapper;

    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository, CarMapper carMapper) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
    }

    public List<CarModel> getAllCars() {
        List<CarEntity> entities = this.carRepository.findAll();
        return this.carMapper.entitiesToModels(entities);
    }

    public List<CarModel> getCarsByMake(String make) {
        List<CarEntity> entities = this.carRepository.findByMake(make);
        return this.carMapper.entitiesToModels(entities);
    }

    public List<CarModel> getCarsByModel(String model) {
        List<CarEntity> entities = this.carRepository.findByModel(model);
        return this.carMapper.entitiesToModels(entities);
    }

    public List<CarModel> getCarsByYear(int year) {
        List<CarEntity> entities = this.carRepository.findByYear(year);
        return this.carMapper.entitiesToModels(entities);
    }

    public void insertCar(CarModel carModel) {
        CarEntity carEntity = this.carMapper.modelToEntity(carModel);
        this.carRepository.save(carEntity);
    }

    @Transactional
    public void deleteCar(CarModel carModel) {
        this.carRepository.deleteCar(carModel.getMake(), carModel.getModel(), carModel.getYear());
    }
}
