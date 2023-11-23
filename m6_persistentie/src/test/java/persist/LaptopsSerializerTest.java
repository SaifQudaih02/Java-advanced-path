package persist;
import Data.Data;
import model.Laptop;
import model.Laptops;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;


import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LaptopsSerializerTest {
   private LaptopsSerializer laptopsSerializer;
   private Laptops laptops;
    @BeforeEach
    void setUp() throws IllegalAccessException {
        laptopsSerializer = new LaptopsSerializer();
        laptops = new Laptops();
        for (Laptop laptop : Data.getData()){
            laptops.setLaptop(laptop);
        }
    }

    // todo Schrijf een testmethode testSerialize die controleert of er geen IOException optreedt bij het serialiseren
    @Test
    void testSerialize(){

        //assertThrows(IOException.class , () -> laptopsSerializer.serialize(laptops));
        assertDoesNotThrow(()-> laptopsSerializer.serialize(laptops));
    }


    @Test
    void testDeserialize()  {
        try {
           // laptopsSerializer.serialize(laptops);
            Laptops deserializedLaptops = laptopsSerializer.deserialize();
            assertEquals(laptops.getAllLaptops(), deserializedLaptops.getAllLaptops());
        } catch (IOException | ClassNotFoundException e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }
}