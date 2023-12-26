package be.kdg.java2.database;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import be.kdg.java2.data.Data;
import be.kdg.java2.exceptios.LaptopException;
import be.kdg.java2.model.*;
public class LaptopDbDao implements LaptopDao {
    private final String dbPath;
    private static Connection connection;
    private ResultSet resultSet;
    private final String username = "DB";
    private final String password = "";
    private static final Logger logger = Logger.getLogger(LaptopDbDao.class.getName());

    private LaptopDbDao() {
        //todo de path
        dbPath = "jdbc:hsqldb:file:db";
        try {
            connection = DriverManager.getConnection(dbPath, username, password);
            createTable();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQLException " + e);
        }
    }

    // Public static methode om de enige instantie te krijgen.
    public static LaptopDbDao getInstance(String path){

        if(connection == null){
            try{
                connection = DriverManager.getConnection("jdbc:hsqldb:file:" + path, "sa", "");
                createTable();
            } catch (SQLException e) {
                logger.log(Level.WARNING, "SQLException ", e);
                throw new LaptopException("Error: ", e);
            }
        }
        return new LaptopDbDao();
    }
    public static void createTable() {
        try {
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            ResultSet set = databaseMetaData.getTables(null, null, "LAPTOPTABLE", null);
            if (!set.next()) {
                Statement statement = connection.createStatement();
                String query = "CREATE TABLE laptoptable (" +
                        "id INTEGER NOT NULL IDENTITY," +
                        "name VARCHAR(30)," +
                        "colour VARCHAR(30)," +
                        "price DOUBLE," +
                        "ram INT," +
                        "storage INT," +
                        "category VARCHAR(30)," +
                        "releaseDate DATE," +
                        "macAdres VARCHAR(30)" +
                        ")";
                statement.execute(query);
                logger.info("Created Laptop Table");
                Data.getData().forEach(new LaptopDbDao()::insert);
            } else {
                logger.info("Laptop Table already exists.");
            }
        } catch (SQLException | IllegalAccessException e) {
            throw new LaptopException(e);
        }
    }
/*
    public static void createTable(){
        try {
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            ResultSet set = databaseMetaData.getTables(null, null, "laptoptable", null);
            if (!set.next()) {
                Statement statement = connection.createStatement();
                String query = "CREATE TABLE laptoptable (" +
                        "id INTEGER NOT NULL IDENTITY," +
                        "name VARCHAR(30)," +
                        "colour VARCHAR(30)," +
                        "price DOUBLE," +
                        "ram INT," +
                        "storage INT," +
                        "category VARCHAR(30)," +
                        "releaseDate DATE," +
                        "macAdres VARCHAR(30)" + // Removed the trailing comma here
                        ")";
                statement.execute(query);
                logger.info("Created Laptop Table");
                Data.getData().forEach(new LaptopDbDao()::insert);
            }
        } catch (SQLException | IllegalAccessException e) {
            throw new LaptopException(e);
        }
    }

 */

    @Override
    public boolean insert(Laptop laptop) {

        String insertQuery = "INSERT INTO laptoptable (name, colour, price, ram, storage, category, releaseDate, macAdres) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            preparedStatement.setString(1, laptop.getName());
            preparedStatement.setString(2, laptop.getColour());
            preparedStatement.setDouble(3, laptop.getPrice());
            preparedStatement.setInt(4, laptop.getRam());
            preparedStatement.setInt(5, laptop.getStorage());
            preparedStatement.setString(6, laptop.getCategory().toString());
            preparedStatement.setDate(7, Date.valueOf(laptop.getReleaseDate()));
            preparedStatement.setString(8, laptop.getMacAdres());
            logger.info("New laptop  has been added to the tabel");

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.log(Level.WARNING, "SQLException while creating table", e);
            throw new LaptopException("Error creating table", e);
        }
    }

    @Override
    public boolean delete(String macAdres) {
        String sql = "Delete from laptoptable where macAdres= ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, macAdres);
            logger.info("New laptop  has been deleted from the tabel");

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.log(Level.WARNING, "SQLException while deleting laptop", e);
            throw new LaptopException("Error deleting laptop ", e);
        }
    }

    @Override
    public boolean update(Laptop laptop) {
        String updateQuery = "UPDATE laptoptable SET name = ?, colour = ?, price = ?, ram = ?, storage = ?, category = ?, releaseDate = ?, macAdres = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, laptop.getName());
            preparedStatement.setString(2, laptop.getColour());
            preparedStatement.setDouble(3, laptop.getPrice());
            preparedStatement.setInt(4, laptop.getRam());
            preparedStatement.setInt(5, laptop.getStorage());
            preparedStatement.setString(6, laptop.getCategory().toString());
            preparedStatement.setDate(7, Date.valueOf(laptop.getReleaseDate()));
            preparedStatement.setString(8, laptop.getMacAdres());
            preparedStatement.setInt(9, laptop.getId());

            int updatedRows = preparedStatement.executeUpdate();
            logger.info("New laptop  has been updated in the tabel");

            return updatedRows > 0; // return the rows if true "grater then 1"

        } catch (SQLException e) {
            logger.log(Level.WARNING, "SQLException while updating laptop", e);
            throw new LaptopException("Error updating laptop", e);
        }


    }

    @Override
    public Laptop retrieve(String macAdres) {
        String sql = "SELECT * FROM laptoptable WHERE macAdres = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, macAdres); // Set the parameter for the query
            resultSet = preparedStatement.executeQuery(); // Execute the query to populate resultSet

            if (resultSet.next()) { // Check if there are results before retrieving data
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String colour = resultSet.getString("colour");
                double price = resultSet.getDouble("price");
                int ram = resultSet.getInt("ram");
                int storage = resultSet.getInt("storage");
                Category category = Category.valueOf(resultSet.getString("category"));
                LocalDate releaseDate = resultSet.getDate("releaseDate").toLocalDate();
                String retrievedMacAdres = resultSet.getString("macAdres");
                logger.info("New laptop  has been retrieved from the tabel");

                return new Laptop(id, name, colour, price, ram, storage, category, releaseDate, retrievedMacAdres);
            }
        } catch (SQLException e) {
            logger.log(Level.WARNING, "SQLException while retrieving laptop", e);
            throw new LaptopException("Error creating table", e);
        }
        return null;

    }

    @Override
    public List<Laptop> sortedOn(String query) {
        List<Laptop> laptopList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            resultSet = preparedStatement.executeQuery(); // Execute the query

            while (resultSet.next()) { // Move the cursor to the next row
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String colour = resultSet.getString("colour");
                double price = resultSet.getDouble("price");
                int ram = resultSet.getInt("ram");
                int storage = resultSet.getInt("storage");
                Category category = Category.valueOf(resultSet.getString("category"));
                LocalDate releaseDate = resultSet.getDate("releaseDate").toLocalDate();
                String macAdres = resultSet.getString("macAdres");
                Laptop laptop = new Laptop(id, name, colour, price, ram, storage, category, releaseDate, macAdres);
                laptopList.add(laptop);
            }

        } catch (SQLException e) {
            logger.log(Level.WARNING, "SQLException while sorting laptops", e);
            throw new LaptopException("Error sorting laptops", e);
        }
        return laptopList;
    }

    @Override
    public List<Laptop> getAllLaptops() {
        List<Laptop> laptops = new ArrayList<>();
        String sql = "SELECT * FROM laptoptable";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            // Execute the query
            ResultSet resultSet = preparedStatement.executeQuery();

            // Iterate through the result set
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String colour = resultSet.getString("colour");
                double price = resultSet.getDouble("price");
                int ram = resultSet.getInt("ram");
                int storage = resultSet.getInt("storage");
                Category category = Category.valueOf(resultSet.getString("category"));
                LocalDate releaseDate = resultSet.getDate("releaseDate").toLocalDate();
                String macAdres = resultSet.getString("macAdres");

                // Create a Laptop object and add it to the list
                Laptop laptop = new Laptop(id, name, colour, price, ram, storage, category, releaseDate, macAdres);
                laptops.add(laptop);
            }
        } catch (SQLException e) {
            throw new LaptopException("Error getting laptops", e);
        }
        return laptops;
    }


    public List<Laptop> sortedOnName() {
        String query = "SELECT * FROM laptoptable ORDER BY name";
        return sortedOn(query);
    }

    public List<Laptop> sortedOnPrice() {
        String query = "SELECT * FROM laptoptable ORDER BY price";
        return sortedOn(query);
    }

    public void close() {
        try {
            connection.close();
            logger.info("Datebase closed ");
        } catch (SQLException e) {

        }
    }

}
