package be.kdg.java2;

import be.kdg.java2.threading.LaptopRunnable;
public class Demo_9 {
    private static final int TESTCOUNT = 100;

    public static void main(String[] args) {
        long totalTime = 0;

        for (int i = 0; i < TESTCOUNT; i++) {
            LaptopRunnable laptopRunnable = new LaptopRunnable(laptop -> laptop.getName().equals("Zwart"));
            LaptopRunnable laptopRunnable1 = new LaptopRunnable(laptop -> laptop.getStorage() > 500);
            LaptopRunnable laptopRunnable2 = new LaptopRunnable(laptop -> laptop.getPrice() > 800);

            Thread thread = new Thread(laptopRunnable);
            Thread thread1 = new Thread(laptopRunnable1);
            Thread thread2 = new Thread(laptopRunnable2);

            long startTime = System.currentTimeMillis();

            thread.start();
            thread1.start();
            thread2.start();

            try {
                // Start de threads op en laat de main-thread wachten tot ze allemaal ten einde zijn.
                thread.join();
                thread1.join();
                thread2.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            long endTime = System.currentTimeMillis();
            long elapsedTime = endTime - startTime;
            totalTime += elapsedTime;

            laptopRunnable.getLaptops().stream().limit(5).forEach(System.out::println);
            laptopRunnable1.getLaptops().stream().limit(5).forEach(System.out::println);
            laptopRunnable2.getLaptops().stream().limit(5).forEach(System.out::println);

        }

        double averageTime = (double) totalTime / TESTCOUNT;
        System.out.println("3 threads verzamelen elk 1000 laptops (gemiddelde uit 100 runs): " + averageTime + " ms");
    }
}
