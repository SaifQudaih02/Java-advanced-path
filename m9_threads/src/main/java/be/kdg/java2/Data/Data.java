package be.kdg.java2.Data;

import be.kdg.java2.model.Category;
import be.kdg.java2.model.Laptop;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Data {

    public static List<Laptop> getData() throws IllegalAccessException {
        List<Laptop> laptops = new ArrayList<>();
        laptops.add(new Laptop("HP", "Zwart", 899.99, 16, 512, Category.GAMING, LocalDate.of(2022, 5, 15), "ABCD12345"));
        laptops.add(new Laptop("SAMSUNG", "Zilver", 999.99, 8, 256, Category.BUSINESS, LocalDate.of(2022, 4, 10), "EFGH67890"));
        laptops.add(new Laptop("MACBOOK", "Blauw", 749.99, 12, 512, Category.HOME, LocalDate.of(2021, 9, 20), "IJKL45678"));
        laptops.add(new Laptop("DELL", "Rood", 1299.99, 32, 1000, Category.GAMING, LocalDate.of(2022, 8, 5), "MNOP12345"));
        laptops.add(new Laptop("LENOVO", "Grijs", 599.99, 4, 128, Category.HOME, LocalDate.of(2022, 3, 25), "QRST67890"));
        laptops.add(new Laptop("ASUS", "Zwart", 799.99, 8, 256, Category.BUSINESS, LocalDate.of(2022, 1, 10), "UVWX45678"));
        laptops.add(new Laptop("ASUS", "Wit", 1199.99, 16, 512, Category.GAMING, LocalDate.of(2022, 6, 30), "YZAB12345"));
        laptops.add(new Laptop("ASUS", "Roze", 699.99, 4, 256, Category.HOME, LocalDate.of(2021, 11, 15), "CDEF67890"));
        laptops.add(new Laptop("LENOVO", "Goud", 1499.99, 32, 1000, Category.BUSINESS, LocalDate.of(2022, 7, 12), "GHIJ45678"));
        laptops.add(new Laptop("LENOVO", "Zilver", 855.99, 8, 512, Category.HOME, LocalDate.of(2022, 2, 5), "KLMN12345"));
        laptops.add(new Laptop("DELL", "Blauw", 950.99, 16, 512, Category.GAMING, LocalDate.of(2021, 10, 28), "OPQR67890"));
        laptops.add(new Laptop("MACBOOK Air", "Rood", 749.99, 8, 256, Category.BUSINESS, LocalDate.of(2022, 4, 15), "STUV45678"));
        laptops.add(new Laptop("DELL", "Zwart", 1199.99, 32, 1000, Category.GAMING, LocalDate.of(2022, 9, 3), "WXYZ12345"));
        laptops.add(new Laptop("HP", "Wit", 599.99, 4, 128, Category.HOME, LocalDate.of(2022, 3, 8), "1234ABCD"));
        laptops.add(new Laptop("SAMSUNG", "Zilver", 799.99, 12, 512, Category.BUSINESS, LocalDate.of(2022, 12, 18), "5678EFGH"));
        return laptops;
    }
}
