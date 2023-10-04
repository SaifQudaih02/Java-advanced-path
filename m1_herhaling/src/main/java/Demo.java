import be.kdg.java2.Data.Data;
import be.kdg.java2.model.Category;
import be.kdg.java2.model.Laptop;
import be.kdg.java2.model.Laptops;

import java.time.LocalDate;
import java.util.List;

public class Demo {
    public static void main(String[] args) throws IllegalAccessException {

        Laptops laptops = new Laptops();
        List<Laptop> laptopList;
        try {
            laptopList = Data.getData();
            for (Laptop laptop : laptopList) {
                laptops.setLaptop(laptop);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        // c. Voeg ook eens een dubbel object toe (dat zou niet mogen lukken; zie 2.3)
        boolean isAdded = laptops.setLaptop(Data.getData().get(0)); // Voeg dezelfde laptop toe als de eerste laptop in de lijst
        if (!isAdded) {
            System.out.println("Duplicate object could not be added.");
        }

        // d. Test de methoden search, remove en getSize uit
        Laptop foundLaptop = laptops.search("EFGH67890");
        if (foundLaptop != null) {
            System.out.println("Found laptop: " + foundLaptop);
        } else {
            System.out.println("Laptop not Found.");
        }

        boolean isRemoved = laptops.remove("ABCD12345");
        if (isRemoved) {
            System.out.println("Laptop deleted.");
        } else {
            System.out.println("Laptop not found or deleted.");
        }

        // e. Druk de 3 gesorteerde lijsten af
        System.out.println("Sorted by naam:");
        for (Laptop laptop : laptops.sortedOnName()) {
            System.out.println(laptop);
        }

        System.out.println("Sorted by price:");
        for (Laptop laptop : laptops.sortedOnPrice()) {
            System.out.println(laptop);
        }

        System.out.println("Sorted by release datum:");
        for (Laptop laptop : laptops.sortedOnReleaseDate()) {
            System.out.println(laptop);
        }

        // f. Test beide constructors uit en ook de IllegalArgumentException
        try {
            Laptop invalidLaptop = new Laptop("", "Zwart", 899.99, 16, 512, Category.GAMING, LocalDate.of(2022, 5, 15), "ABCD12345");
        } catch (IllegalAccessException e) {
            System.out.println("IllegalArgumentException: " + e.getMessage());
        }

        try {
            Laptop validLaptop = new Laptop("Lenovo", "Grijs", 599.99, 4, 128, Category.HOME, LocalDate.of(2022, 3, 25), "QRST67890");
        } catch (IllegalAccessException e) {
            System.out.println("IllegalArgumentException: " + e.getMessage());
        }


    }
}