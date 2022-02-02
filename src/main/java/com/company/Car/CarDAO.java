package com.company.Car;

import java.util.List;

public interface CarDAO {
    public List<Car> getAllCars();
    public void addCar(String name, int company_id);
}
