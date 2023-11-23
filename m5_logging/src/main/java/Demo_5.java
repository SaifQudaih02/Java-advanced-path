import be.kdg.java2.logging.SmallLogFormatter;
import be.kdg.java2.model.Category;
import be.kdg.java2.model.Laptop;
import be.kdg.java2.model.Laptops;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class Demo_5 {
    private static final Logger logger = Logger.getLogger("be.kdg.java2.Demo_5");

    public static void main(String[] args) {

        Laptop laptop = new Laptop();
        Laptops laptops = new Laptops();


        /** zorg je ervoor dat het configuratiebestand logging.properties geladen wordt.*/
        try {
            InputStream inputStream = Demo_5.class.getResourceAsStream("logging.properties");
            LogManager.getLogManager().readConfiguration(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        /** aantal foutieve basisobjecten aan om de logging te testen.*/
        laptop.setName(null);
        laptop.setReleaseDate(LocalDate.of(2078, 12, 18));

        laptops.setLaptop(new Laptop("MACBOOK", "Zilver", 999.99, 16, 512, Category.BUSINESS, LocalDate.of(2022, 9, 20), "IJKL45679"));
        laptops.setLaptop(new Laptop("MACBOOK-Air", "Zwart", 1200.00, 16, 512, Category.GAMING, LocalDate.of(2021, 9, 20), "IJKL45655"));
        laptops.setLaptop(new Laptop("MACBOOK", "wit", 500.00, 16, 512, Category.HOME, LocalDate.of(2001, 9, 20), "RMKL45644"));
        laptops.setLaptop(new Laptop("SAMSUNG", "Zilver", 800.00, 16, 512, Category.UNKNOWN, LocalDate.of(2018, 9, 20), "SDEL45648"));
        laptops.remove("IJKL45679");

        logger.log(Level.INFO , "eel log");

    }
}
