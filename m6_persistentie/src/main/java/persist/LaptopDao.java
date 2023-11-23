package persist;

import model.Laptop;

import java.sql.SQLException;
import java.util.List;

public interface LaptopDao {
    boolean insert(Laptop dictator);
    boolean delete(String naam) throws SQLException;
    boolean update(Laptop dictator);
    Laptop retrieve(String naam);
    List<Laptop> sortedOn(String query);
}
