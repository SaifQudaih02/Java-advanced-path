package be.kdg.java2.model;

import be.kdg.java2.Data.Data;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LaptopsTest {

    private Laptops laptops1;
    private Laptops laptops2;
    private Laptops laptops3;
    private Laptops laptops4;
    private Laptops laptops5;

    @BeforeEach
    void setUp() {
        laptops1 = new Laptops();
        laptops2 = new Laptops();
        laptops3 = new Laptops();
        laptops4 = new Laptops();
        laptops5 = new Laptops();
    }

    @Test
    void testAdd() {
        var lap = new Laptop("DELL", "Zwart", 1199.99, 16, 550, Category.HOME, LocalDate.of(2021, 8, 5), "MNOP12346");
        assertTrue(laptops1.addLaptop(lap), "Laptop has been add");

        // assertTrue(laptops1.addLaptop(lap), "Duplicate laptop should not be added");
        assertFalse(laptops1.addLaptop(lap), "Duplicate laptop should not be added");

    }

    @Test
    void testRemove() {
        for (Laptop laptop : Data.getData()) {
            laptops2.addLaptop(laptop);
        }
        int size = laptops2.getSize();
        assertTrue(size == laptops2.getSize());
        assertTrue(laptops2.remove("ABCD12345"), "The laptop with this mac address does not exist1");
        assertTrue(size != laptops2.getSize(),"nothing removed");

    }

    @Test
    void testSort() {
        for (Laptop laptop : Data.getData()) {
            laptops4.addLaptop(laptop);
        }
        List<Laptop> sortedByName = laptops4.sortedBy(Laptop::getName);
        assertAll("Sorted by name",
                () -> assertEquals("ASUS", sortedByName.get(0).getName(), "First position should have name 'ASUS'"),
                () -> assertEquals("ASUS", sortedByName.get(1).getName(), "Second position should have name 'ASUS'"),
                () -> assertEquals("ASUS", sortedByName.get(2).getName(), "Third position should have name 'ASUS'")
        );

    }

    @Test
    void testSortByPrice() {
        for (Laptop laptop : Data.getData()) {
            laptops5.addLaptop(laptop);
        }

        List<Laptop> sortedByPrice = laptops5.sortedBy(Laptop::getPrice);

        Laptop[] laptops = {
                new Laptop("HP", "Wit", 599.99, 4, 128, Category.HOME, LocalDate.of(2022, 3, 8), "1234ABCD"),
                new Laptop("LENOVO", "Grijs", 599.99, 4, 128, Category.HOME, LocalDate.of(2022, 3, 25),"QRST67890"),
                new Laptop("ASUS", "Roze", 699.99, 4, 256, Category.HOME, LocalDate.of(2021, 11, 15), "CDEF67890"),
                new Laptop("MACBOOK", "Blauw", 749.99, 12, 512, Category.HOME, LocalDate.of(2021, 9, 20), "IJKL45678"),
                new Laptop("MACBOOK Air", "Rood", 749.99, 8, 256, Category.BUSINESS, LocalDate.of(2022, 4, 15), "STUV45678")
        };

        assertArrayEquals(laptops, sortedByPrice.subList(0, 5).toArray(), "First 5 laptops should be in the expected order sorted by price");
    }
}