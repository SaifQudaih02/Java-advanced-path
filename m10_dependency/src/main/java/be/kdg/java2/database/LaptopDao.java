package be.kdg.java2.database;


import be.kdg.java2.model.*;

import java.sql.SQLException;
import java.util.List;

public interface LaptopDao {
    boolean insert(Laptop laptop);
    boolean delete(String naam) throws SQLException;
    boolean update(Laptop laptop);
    Laptop retrieve(String naam);
    List<Laptop> sortedOn(String query);

    List<Laptop> getAllLaptops();
}
