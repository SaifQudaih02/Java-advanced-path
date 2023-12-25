package be.kdg.java2;
import be.kdg.java2.model.LaptopFactory;
import be.kdg.java2.model.Laptops;
import java.util.stream.Stream;

public class Demo_12 {
    public static void main(String[] args) {
        Laptops laptops = new Laptops();

        Thread thread = new Thread(() -> Stream.generate(LaptopFactory::newRandomLaptop).limit(5000).forEach(laptops::addLaptop));
        Thread thread1 = new Thread(() -> Stream.generate(LaptopFactory::newRandomLaptop).limit(5000).forEach(laptops::addLaptop));

        thread.setDaemon(true);
        thread1.setDaemon(true);
        thread.start();
        thread1.start();
        try {
            thread.join();
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Na toevoegen door 2 threads met elk 5000 objecten: laptops = " + laptops.getSize());
    }
}
