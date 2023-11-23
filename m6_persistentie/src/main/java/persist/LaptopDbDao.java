package persist;

import model.Category;
import model.Laptop;
import model.Laptops;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

// todo close connection en statment.
public class LaptopDbDao implements LaptopDao {
    private final String dbPath;
    private Connection connection;
    private ResultSet resultSet;
    private String username = "DB";
    private String password = "";
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    public LaptopDbDao() {
        //todo de path
        dbPath = "jdbc:hsqldb:file:db";
        try {
            connection = DriverManager.getConnection(dbPath, username, password);
            createTable();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQLException " + e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "Error clossing connection", e.getMessage());
            }
        }
    }


    // todo "gedaan behalve statment close"
    private void createTable() {
        try (Statement statement = connection.createStatement()) {
            /** voor de DDL "CREATE OR DROP" gebuik execute! */
            statement.execute("DROP TABLE IF EXISTS laptoptable");
            statement.execute("CREATE TABLE laptoptable(id INTEGER IDENTITY , name VARCHAR(30) , colour VARCHAR(30),  price DOUBLE, ram INT, storage INT, category VARCHAR(30), releaseDate DATE, macAdres VARCHAR(30) , PRIMARY KEY (id)) ");

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error creating table: " + e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    // todo colse the statement
                    connection.close();
                }
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "Error clossing connection :" + e.getMessage());
            } finally {
                try {
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException e) {
                    logger.log(Level.SEVERE, "Error closing connesction", e.getMessage());
                }
            }
        }
    }


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
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error inserting laptop: " + e.getMessage(), e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "Error closing connesction", e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean delete(String macAdres) {
        String sql = "Delete from laptoptable where macAdres= ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, macAdres);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error deleting laptop" + e.getMessage());

        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "Error closing connesction", e.getMessage());
            }
        }
        return false;
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
            preparedStatement.setDate(7, java.sql.Date.valueOf(laptop.getReleaseDate()));
            preparedStatement.setString(8, laptop.getMacAdres());
            preparedStatement.setInt(9, laptop.getId());

            int updatedRows = preparedStatement.executeUpdate();
            return updatedRows > 0; // return the rows if true "grater then 1"

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error updating laptop: " + e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();

                }
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "Error closing connesction", e.getMessage());
            }
        }

        return false;
    }

    @Override
    public Laptop retrieve(String macAdres) {
        String sql = "SELECT * FROM laptoptable WHERE macAdres = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            resultSet = preparedStatement.getResultSet();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String colour = resultSet.getString("colour");
                double price = resultSet.getDouble("price");
                int ram = resultSet.getInt("ram");
                int storage = resultSet.getInt("storage");
                Category category = Category.valueOf(resultSet.getString("category"));
                LocalDate releaseDate = resultSet.getDate("releaseDate").toLocalDate();
                String retrievedMacAdres = resultSet.getString("macAdres");
                return new Laptop(id, name, colour, price, ram, storage, category, releaseDate, retrievedMacAdres);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error retrieving laptop: " + e.getMessage(), e);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                //if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "Error closing connesction", e.getMessage());
            }
            return null;
        }
    }

    @Override
    public List<Laptop> sortedOn(String query) {
        // todo close the connection
        List<Laptop> laptopList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            resultSet = preparedStatement.getResultSet();
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

            return laptopList;

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error sorting laptop: " + e.getMessage(), e);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                //if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        // todo check de return
        return null;
    }

    public List<Laptop> sortedOnName() {
        String query = "SELECT * FROM laptoptable ORDER BY name";
        return sortedOn(query);
    }

    public List<Laptop> sortedOnPrice() {
        String query = "SELECT * FROM laptoptable ORDER BY price";
        return sortedOn(query);
    }

    public List<Laptop> sortedOnding(){
        String query = "SELECT * FROM laptoptable ORDER BY price";

        return sortedOn(query);
    }
}
