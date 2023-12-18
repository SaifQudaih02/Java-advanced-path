package be.kdg.java2.model;
import java.time.LocalDate;
import java.util.Objects;
public final class Laptop implements Comparable<Laptop>{
    private final String name ;
    private final String colour;
    private final double price;
    private final int ram;
    private final int storage;
    private final Category category;
    private final LocalDate releaseDate;
    private final String macAdres;

    public Laptop() throws IllegalArgumentException {
        this("Anoniem", "Ongekend", 0.0, 0, 0, Category.UNKNOWN  , LocalDate.now() , "onbekend");
    }
    public Laptop(String name, String colour, double price, int ram, int storage, Category category, LocalDate releaseDate, String macAdres) {
        this.name = name;
        this.colour = colour;
        this.price = price;
        this.ram = ram;
        this.storage = storage;
        this.category = category;
        this.releaseDate = releaseDate;
        this.macAdres = macAdres;
    }
    public String getName(){
        return name;
    }

    public String getColour() {
        return colour;
    }

    public double getPrice() {
        return price;
    }

    public int getRam() {
        return ram;
    }

    public int getStorage() {
        return storage;
    }

    public Category getCategory() {
        return category;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public String getMacAdres(){return macAdres;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Laptop laptop = (Laptop) o;
        return Objects.equals(macAdres, laptop.macAdres);
    }


    @Override
    public int hashCode() {
        return Objects.hash(macAdres);
    }


    @Override
    public int compareTo(Laptop o) {
        if (this.macAdres == null || o.macAdres == null) {
            if (this.macAdres == null && o.macAdres == null) {
                return 0; // Beide zijn null, dus ze zijn gelijk.
            } else if (this.macAdres == null) {
                return -1; // Dit object heeft een null macAdres, dus het is kleiner.
            } else {
                return 1; // Het andere object heeft een null macAdres, dus het is kleiner.
            }
        }
        return this.macAdres.compareTo(o.macAdres);
    }

    @Override
    public String toString() {
        return String.format("Laptop: %s Kleur: %s Prijs: %.2fRAM: %d GB Storage: %d GB Categorie: %s Releasedatum: %s MacAdres %s" ,
                name, colour, price, ram, storage, category, releaseDate, macAdres);
    }
}
