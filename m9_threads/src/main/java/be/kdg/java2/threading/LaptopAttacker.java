package be.kdg.java2.threading;

import be.kdg.java2.model.Laptop;

import java.util.List;
import java.util.function.Predicate;

public class LaptopAttacker implements Runnable {
    private List<Laptop> laptopList;
    private Predicate<Laptop> laptopPredicate;

    public LaptopAttacker(List<Laptop> laptopList, Predicate<Laptop> laptopPredicate) {
        this.laptopList = laptopList;
        this.laptopPredicate = laptopPredicate;
    }

    @Override
    public void run() {
        synchronized (laptopList) {
            laptopList.removeIf(laptopPredicate);
        }
    }
}
