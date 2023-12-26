package be.kdg.java2.service;

import be.kdg.java2.model.Laptop;

import java.util.List;

public interface LaptopsService {
     List<Laptop> getAllLaptops();

     void addLaptop(Laptop laptop);
}
