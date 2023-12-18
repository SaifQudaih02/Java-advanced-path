package be.kdg.java2.threading;

import be.kdg.java2.model.Laptop;
import be.kdg.java2.model.LaptopFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LaptopRunnable implements Runnable{
    private Predicate<Laptop> laptopPredicate ;
    private List<Laptop> laptops ;

    public LaptopRunnable(Predicate<Laptop> laptopPredicate) {
        this.laptopPredicate = laptopPredicate;
    }

    @Override
    public void run() {

            this.laptops = Stream.generate(LaptopFactory::newRandomLaptop).limit(1000).filter(laptopPredicate).collect(Collectors.toList());
    }

    public List<Laptop> getLaptops() {
        return laptops;
    }
}
