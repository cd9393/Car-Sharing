package com.company.Car;

import java.util.List;

public interface CarDAO {
    public List<Car> getAllCars(int company_id);
    public void addCar(String name, int company_id);
}
