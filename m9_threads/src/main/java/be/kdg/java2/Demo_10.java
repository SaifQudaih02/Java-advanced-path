package be.kdg.java2;

import be.kdg.java2.model.Laptop;
import be.kdg.java2.model.LaptopFactory;
import be.kdg.java2.threading.LaptopAttacker;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Demo_10 {
    public static void main(String[] args) {

        List<Laptop> laptops = Stream.generate(LaptopFactory::newRandomLaptop).limit(1000).collect(Collectors.toList());

        LaptopAttacker laptopAttacker = new LaptopAttacker(laptops , laptop -> laptop.getPrice() > 500);
        LaptopAttacker laptopAttacker1 = new LaptopAttacker(laptops , laptop -> laptop.getColour().equals("Zwaart"));
        LaptopAttacker laptopAttacker2 = new LaptopAttacker(laptops , laptop -> laptop.getStorage() > 500);

        Thread thread = new Thread(laptopAttacker);
        Thread thread1 = new Thread(laptopAttacker1);
        Thread thread2 = new Thread(laptopAttacker2);

        thread.start();
        thread1.start();
        thread2.start();

        try {
            thread.join();
            thread1.join();
            thread2.join();

            System.out.println("Na uitzuivering:");
            System.out.println("Aantal laptops price > 500 : " + laptops.stream().filter(laptop -> laptop.getPrice() > 500).count());
            System.out.println("Aantal laptops with black colour: " + laptops.stream().filter(laptop -> laptop.getName().equals("Zwaart")).count());
            System.out.println("Aantal laptops Storage > 500 :" + laptops.stream().filter(laptop -> laptop.getStorage() > 500).count());

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
