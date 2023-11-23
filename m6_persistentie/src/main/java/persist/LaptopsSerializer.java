package persist;

import model.Laptop;
import model.Laptops;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LaptopsSerializer {
    private final String FILENAME;

    public LaptopsSerializer() {
        FILENAME="db/laptops.ser";
    }

    public void serialize(Laptops laptops) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILENAME));
        oos.writeObject(laptops);
    }

    public Laptops deserialize() throws IOException, ClassNotFoundException{
    //    List<Laptops> laptopsList = new ArrayList<>();
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILENAME));

       // laptopsList.add(new Laptop(laptop));
                return (Laptops) ois.readObject();

    }





}
