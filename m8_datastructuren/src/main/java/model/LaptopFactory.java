package model;

import java.time.LocalDate;
import java.time.Year;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LaptopFactory {
    private static Random random = new Random();
    public static Laptop newEmptyLaptop() {
        return new Laptop();
    }

    public static Laptop newFilledLaptop(String name, String colour, double price, int ram, int storage, Category category, LocalDate releaseDate, String macAdres) {
        return new Laptop(name, colour, price, ram, storage, category, releaseDate, macAdres);
    }

    public static Laptop newRandomLaptop() {

        String name = generateLaptopName();
        String colour = generateColor();
        double price = random.nextDouble(600) + 600;
        int ram = generateRam();
        int storage = generateStorage();
        Category category = generateCategory();
        LocalDate date = generateReleaseDate();
        //macadres
        String macadres = generateString(14, 1, false);

        return new Laptop(name, colour, price, ram, storage, category, date, macadres);
    }


    private static String generateString(int maxWordLength, int wordCount, boolean camelCase) {

        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < wordCount; i++) {
            int wordLength = random.nextInt(maxWordLength) + 1; // Generate random word length between 1 and maxWordLength

            for (int j = 0; j < wordLength; j++) {
                char randomChar;
                // 1 in 3 chance for a vowel, 2 in 3 chance for a consonant
                if (random.nextInt(3) == 0) {
                    randomChar = (char) ('a' + random.nextInt(26)); // Generate a random lowercase vowel
                } else {
                    randomChar = (char) ('b' + random.nextInt(25)); // Generate a random lowercase consonant
                }

                // Capitalize first letter if camelCase is true and it's the first word
                if (camelCase && j == 0 && i == 0) {
                    randomChar = Character.toUpperCase(randomChar);
                }

                stringBuilder.append(randomChar);
            }

            if (i < wordCount - 1) {
                stringBuilder.append(" "); // Add space between words
            }
        }

        return stringBuilder.toString();
    }

    private static String generateLaptopName() {
        Random random = new Random();
        int laptopCase = random.nextInt(8) + 1; // Kies een random getal tussen 1 en 8 voor verschillende namen

        String laptopName;
        switch (laptopCase) {
            case 1:
                laptopName = "Acer Aspire";
                break;
            case 2:
                laptopName = "Dell Inspiron";
                break;
            case 3:
                laptopName = "HP Pavilion";
                break;
            case 4:
                laptopName = "Lenovo ThinkPad";
                break;
            case 5:
                laptopName = "Asus ROG";
                break;
            case 6:
                laptopName = "Apple MacBook";
                break;
            case 7:
                laptopName = "Microsoft Surface";
                break;
            case 8:
                laptopName = "Razer Blade";
                break;
            default:
                laptopName = "Samsung Laptop";
                break;
        }
        return laptopName;
    }


//    private static LocalDate generateReleaseDate() {
//
//        int day = random.nextInt(30) + 1;
//        int month = random.nextInt(12) + 1;
//        int year = random.nextInt(23) + 2000;
//
//        return LocalDate.of(year, month, day);
//    }

    private static LocalDate generateReleaseDate() {
        int day = random.nextInt(28) + 1; // Maximum dagen in de meeste maanden (niet-februari)
        int month = random.nextInt(12) + 1;
        int year = random.nextInt(23) + 2000;

        // Voor februari, controleer het jaar op schrikkeljaar en beperk de dagen tot 29
        if (month == 2) {
            boolean isLeapYear = Year.of(year).isLeap();
            day = isLeapYear ? random.nextInt(29) + 1 : random.nextInt(28) + 1;
        }

        return LocalDate.of(year, month, day);
    }

    private static Category generateCategory() {
        Category category = null;
        int caseCategory = random.nextInt(4) + 1;
        switch (caseCategory) {
            case 1:
                category = Category.GAMING;
                break;
            case 2:
                category = Category.BUSINESS;
                break;
            case 3:
                category = Category.HOME;
                break;
            case 4:
                category = Category.UNKNOWN;
                break;
        }
        return category;
    }

    private static int generateRam() {
        //4 GB, 8 GB, 16 GB, 32 GB, 64 GB, 128 GB
        int ram = 0;
        int ramCase = random.nextInt(6) + 1;
        switch (ramCase) {
            case 1:
                ram = 4;
                break;
            case 2:
                ram = 8;
                break;
            case 3:
                ram = 16;
                break;
            case 4:
                ram = 32;
                break;
            case 5:
                ram = 64;
                break;
            case 6:
                ram = 128;
                break;
        }
        return ram;
    }

    private static int generateStorage() {
        // 128GB 256GB 512GB 1TP (1000 GB) 2TB(2000GB)
        int storage = 0;
        int storagCase = random.nextInt(5) + 1;
        switch (storagCase) {
            case 1:
                storagCase = 128;
                break;
            case 2:
                storagCase = 256;
                break;
            case 3:
                storagCase = 512;
                break;
            case 4:
                storage = 1000;
                break;
            case 5:
                storage = 2000;
                break;
        }
        return storage;
    }

    private static String generateColor() {
        Random random = new Random();
        int colorCase = random.nextInt(5) + 1;

        String color;
        switch (colorCase) {
            case 1:
                color = "Black";
                break;
            case 2:
                color = "Blue";
                break;
            case 3:
                color = "Silver";
                break;
            case 4:
                color = "Gray";
                break;
            case 5:
                color = "White";
                break;
            default:
                color = "Unknown";
                break;
        }
        return color;
    }
}
