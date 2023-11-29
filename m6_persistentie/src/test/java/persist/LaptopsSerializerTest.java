package persist;
import Data.Data;
import model.Laptop;
import model.Laptops;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;
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
    @Test
    void testSerialize(){
        assertDoesNotThrow(()-> laptopsSerializer.serialize(laptops));
    }
    @Test
    void testDeserialize()  {
        try {
            Laptops deserializedLaptops = laptopsSerializer.deserialize();
            assertEquals(laptops.getAllLaptops(), deserializedLaptops.getAllLaptops());
        } catch (IOException | ClassNotFoundException e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }
}