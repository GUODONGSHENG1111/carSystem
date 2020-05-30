package com.jkxy.car.api.service.impl;

import com.jkxy.car.api.dao.CarDao;
import com.jkxy.car.api.pojo.Car;
import com.jkxy.car.api.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("carService")
public class CarServiceImpl implements CarService {

    @Autowired
    private CarDao carDao;

    @Override
    public List<Car> findAll() {
        return carDao.findAll();
    }

    @Override
    public Car findById(int id) {
        return carDao.findById(id);
    }

    @Override
    public List<Car> findByCarName(String carName) {
        return carDao.findByCarName(carName);
    }

    @Override
    public void deleteById(int id) {
        carDao.deleteById(id);
    }

    @Override
    public void updateById(Car car) {
        carDao.updateById(car);
    }

    @Override
    public void insertCar(Car car) {
        carDao.insertCar(car);
    }

    @Override
    public String purchaseCar(Car car, Integer number) {
        Integer carNumber = carDao.queryCarNumber(car);
        String msg;
        if (carNumber < number) {
            msg = "车辆库存不足";
        } else {
            carDao.updateCarNum(car, carNumber - number);
            msg = "购买成功";
        }
        return msg;
    }

    @Override
    public List<Car> fuzzyQuery(String keyWord, Integer pageNo, Integer pageSize) {
        List<Car> carList = carDao.fuzzyQuery(keyWord, (pageNo - 1) * pageSize, pageSize);
        return carList;
    }
}
