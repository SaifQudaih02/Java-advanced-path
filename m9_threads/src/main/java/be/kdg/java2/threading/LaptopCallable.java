package be.kdg.java2.threading;

import be.kdg.java2.model.Laptop;
import be.kdg.java2.model.LaptopFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LaptopCallable implements Callable<List<Laptop>> {
    private List<Laptop> laptops = new ArrayList<>();
    private Predicate<Laptop> laptopPredicate ;

    public LaptopCallable( Predicate<Laptop> laptopPredicate) {
        this.laptopPredicate = laptopPredicate;
    }
    @Override
    public List<Laptop> call() {
       return Stream.generate(LaptopFactory ::newRandomLaptop).limit(1000).filter(laptopPredicate).collect(Collectors.toList());
    }
}
