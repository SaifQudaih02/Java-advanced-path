package be.kdg.java2.service;

import be.kdg.java2.database.LaptopDbDao;
import be.kdg.java2.model.Laptop;

import java.util.List;

public class LaptopsServiceImpl implements LaptopsService{
    //  Zorg voor dependency injection: benader dus de Dao-klasse niet rechtstreeks, maar wel via de interface!
    private final LaptopDbDao laptopDbDao ;

    public LaptopsServiceImpl(LaptopDbDao laptopDbDao) {
        this.laptopDbDao = laptopDbDao;
    }

    @Override
    public List<Laptop> getAllLaptops() {
        return laptopDbDao.getAllLaptops();
    }

    @Override
    public void addLaptop(Laptop laptop) {
        laptopDbDao.insert(laptop);
    }
}
