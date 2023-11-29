package persist;
import Data.Data;
import model.Laptop;
import org.junit.jupiter.api.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LaptopDbDaoTest {

    private static LaptopDbDao dao;

    @BeforeAll
    public static void setUp() {
        dao = new LaptopDbDao(); // Instantiate the DAO class

    }

    @AfterAll
    public static void tearDown() {
        dao.close();
    }

    @BeforeEach
    public void fillDatabase() throws IllegalAccessException {
        // Populate the database with data from your Data class
        for (Laptop laptop : Data.getData()) {
            dao.insert(laptop);
        }

    }

    @AfterEach
    public void clearDatabase() {
        // Remove all records from the database
        dao.delete("*");
    }

    @Test
    public void testInsert() throws IllegalAccessException {
        // Verkrijg de gegevens uit de Data-klasse
        List<Laptop> testData = Data.getData();

        int affectedRows = 0;

        for (Laptop laptop : testData) {
            if (dao.insert(laptop)) {
                affectedRows++;
            }
        }

        assertEquals(testData.size(), affectedRows, "The data is not affected in the datbase correctly.");
    }

    @Test
    public void testRetrieveUpdate(){

            // Choose a specific macAdres for testing
            String macAdresForTest = "ABCD12345"; // Replace with a valid macAdres

            // Retrieve a laptop with the specified macAdres
            Laptop initialLaptop = dao.retrieve(macAdresForTest);

            assertNotNull(initialLaptop, "Retrieved laptop should not be null");

            // Change the name of the retrieved laptop
            String updatedName = "Updated Name";
            initialLaptop.setName(updatedName);

            // Perform the update operation
            boolean updateSuccess = dao.update(initialLaptop);

            assertTrue(updateSuccess, "Update operation should be successful");

            // Retrieve the updated laptop again
            Laptop updatedLaptop = dao.retrieve(macAdresForTest);

            assertNotNull(updatedLaptop, "Retrieved updated laptop should not be null");

            // Compare the name before and after update
            assertEquals(updatedName, updatedLaptop.getName(), "Name should be updated");

    }

    @Test
    public void testDelete() {
        String macAdresForTest = "ABCD12345"; // Replace with a valid macAdres

        // Retrieve a laptop with the specified macAdres
        Laptop laptopToDelete = dao.retrieve(macAdresForTest);

        assertNotNull(laptopToDelete, "Retrieved laptop should not be null");

        // Perform the delete operation
        boolean deleteSuccess = dao.delete(macAdresForTest);

        assertTrue(deleteSuccess, "Deletion should be successful");

        // Try deleting the same record again
        boolean secondDeleteSuccess = dao.delete(macAdresForTest);

        assertFalse(secondDeleteSuccess, "Second deletion should fail");
    }

    @Test
    void testSort() throws IllegalAccessException {
        List<Laptop> sortedByName = dao.sortedOnName();
        List<Laptop> sortedByPrice = dao.sortedOnPrice();

        // Sort your test data by name and price
        List<Laptop> testDataSortedByName = Data.getData();
        Collections.sort(testDataSortedByName, Comparator.comparing(Laptop::getName));

        List<Laptop> testDataSortedByPrice = Data.getData();
        Collections.sort(testDataSortedByPrice, Comparator.comparingDouble(Laptop::getPrice));

        assertAll("List comparisons",
                () -> assertIterableEquals(testDataSortedByName, sortedByName, "List sorted on name should match expected sorting"),
                () -> assertIterableEquals(testDataSortedByPrice, sortedByPrice, "List sorted on price should match expected sorting")
        );
    }

}
