package be.kdg.java2.model;

import be.kdg.java2.Data.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LaptopTest {

    private Laptop laptop;
    private Laptop laptop2;

    @BeforeEach
    void setUp() {
        laptop = new Laptop();
        laptop2=new Laptop();
    }

    @Test
    void testEquals(){
        List<Laptop> laptopList = Data.getData();
        var lap1= laptopList.get(0);
        var lap2 = laptopList.get(1);

        Laptop laptop3 = new Laptop("Samsung", "Zilver", 699.99, 32, 250, Category.UNKNOWN, LocalDate.of(2021, 5, 15), "ABCD12345");

        /**  beide testobjecten verschillen van elkaar */

        assertFalse(lap1.equals(lap2) , "the laptops should be not equals");


        assertTrue(lap1.equals(laptop3) , "the laptops should be  equals");


    }

    @Test
    void testIllegalName(){

         assertThrows(IllegalArgumentException.class, () -> laptop.setName(""), "the Laptop name  is legal");

    }
    @Test
    void testLegalName(){
        assertDoesNotThrow(() -> laptop.setName("DELL") ," laptop name is not legal");
    }

    @Test
    void testCompareTo()  {
        // Maak ook een nieuw lokaal object van de basisklasse dat gelijk zou moeten zijn aan één van de testobjecten
        List<Laptop> laptops = Data.getData();
        Laptop laptop1 = laptops.get(0);
        Laptop laptop2 = laptops.get(1);

        // Test of de compareTo-methode de verwachte waarden retourneert voor twee verschillende laptops
        int result =  laptop1.compareTo(laptop2);
        assertTrue(result != 0); // laptop1 zou kleine of groter moeten zijn dan laptop2

        var laptlaptop1Duplicate  = new Laptop("HP", "Zwart", 899.99, 16, 512, Category.GAMING, LocalDate.of(2022, 5, 15), "ABCD12345");

        // Test of compareTo effectief 0 teruggeeft voor twee gelijke objecten
        assertEquals(0, laptop1.compareTo(laptlaptop1Duplicate));
    }

    @Test
    void testPrice(){
        double expected = 500.00;
        double delta = 0.1; // Tolerantie voor de dubbele precisie

        laptop.setPrice(expected);
        assertEquals(expected, laptop.getPrice() , delta);
    }
}