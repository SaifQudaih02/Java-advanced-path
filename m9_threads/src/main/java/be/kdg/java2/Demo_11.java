package be.kdg.java2;

import be.kdg.java2.model.Laptop;
import be.kdg.java2.threading.LaptopCallable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Predicate;

public class Demo_11 {
    private static int TESTCOUNT = 1;

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);

        long test;
        List<Long> threadTime = new ArrayList<>();

        while (TESTCOUNT <= 100) {
            test = System.currentTimeMillis();

            // Aanpassen van de predicaten voor laptops
            Callable<List<Laptop>> call1 = new LaptopCallable(laptop -> laptop.getStorage() > 500);
            Callable<List<Laptop>> call2 = new LaptopCallable(laptop -> laptop.getRam() > 12);
            Callable<List<Laptop>> call3 = new LaptopCallable(laptop -> laptop.getReleaseDate().isAfter(LocalDate.of(2017, 10, 13)));

            Future<List<Laptop>> future1 = executor.submit(call1);
            Future<List<Laptop>> future2 = executor.submit(call2);
            Future<List<Laptop>> future3 = executor.submit(call3);

            while (!future1.isDone() && !future2.isDone() && !future3.isDone()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            try {
                System.out.println("Future 1 " + future1.get());
                System.out.println("Future 2 " + future2.get());
                System.out.println("Future 3 " + future3.get());
                executor.awaitTermination(0, TimeUnit.MINUTES);
                TESTCOUNT++;
                threadTime.add(System.currentTimeMillis() - test);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        executor.shutdown();
        System.out.println();
        System.out.println("3 Futures verzamelen elk 1000 laptops (gemiddelde uit 100 runs): " + threadTime.stream().mapToDouble(Long::doubleValue).average().getAsDouble() + "ms");
    }
}
