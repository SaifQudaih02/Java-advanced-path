import be.kdg.java2.Data.Data;
import be.kdg.java2.model.Category;
import be.kdg.java2.model.Laptop;
import be.kdg.java2.model.Laptops;
import be.kdg.java2.util.LaptopFunctions;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Demo_4 {
    public static void main(String[] args) throws IllegalAccessException {

        Laptops laptops = new Laptops();
        for (Laptop laptop: Data.getData()){
            laptops.addLaptop(laptop);
        }
        System.out.println("\nLaptop sorted by name:");
       laptops.sortedBy(Laptop::getName).forEach(System.out::println);

        System.out.println("\nLaptop sorted by Price: ");
        laptops.sortedBy(Laptop:: getPrice).forEach(System.out::println);

        System.out.println("\nLaptop sorted by releaseDate: ");
        laptops.sortedBy(Laptop::getReleaseDate).forEach(System.out::println);


        Data data = new Data();
        List<Laptop> laptopList = data.getData();

//        // b. Roep drie keer de filtermethode op en geef telkens een ander Predicate mee.
//        // c. Geef het Predicate bij de oproep mee als een Lambda-expression.
        List<Laptop> filteredLaptopsByName = LaptopFunctions.filteredList(laptopList, laptop -> laptop.getName().equals("HP"));
        List<Laptop> filteredLaptopsByPrice = LaptopFunctions.filteredList(laptopList, laptop -> laptop.getPrice() > 1000);
        List<Laptop> filteredLaptopsByReleaseDate = LaptopFunctions.filteredList(laptopList, laptop -> laptop.getReleaseDate().isBefore(LocalDate.of(2022, 5, 10)));


        // d. Controleer de afdruk
        System.out.println("Toepassing 3 keer FilteredList met telkens een ander Predicate:\n");
        System.out.println("Laptops met naam HP");
        System.out.println(filteredLaptopsByName);

        System.out.println("-------filteredLaptopsByPrice--------");
        System.out.println(filteredLaptopsByPrice);

        System.out.println("--------filteredLaptopsByReleaseDate: --------------");
        System.out.println(filteredLaptopsByReleaseDate);


        System.out.println("-----Average Data list-------");
        List<Laptop> laptopData = Data.getData();

        System.out.printf("Average number of prizes: %.1f mln\n",
                LaptopFunctions.average(laptopData, Laptop::getPrice));

        System.out.printf("Average number of Storages: %.1f mln\n",
                LaptopFunctions.average(laptopData, Laptop::getStorage));


        System.out.println("----------Predicate------------");

        long gamingLaptopsCount = LaptopFunctions.countIf(laptopData, laptop -> laptop.getCategory() == Category.GAMING);
        System.out.println("Aantal gaming laptops: " + gamingLaptopsCount);

        System.out.printf("Number of Laptops with price  > 1000%%: %d\n",
                LaptopFunctions.countIf(laptopData, laptop -> laptop.getPrice() > 1000));


        System.out.println("------------STREAMS-----------------");
        List<Laptop> laptopList1 = Data.getData();

        long countt = laptopList1.stream().filter(laptop -> laptop.getReleaseDate()
                .isAfter(LocalDate.of(2022, 5, 10))).count();
        System.out.printf("Numeber of laptops after '2022,5,10' is : %d\n", countt);


        System.out.println("----. Sorteer (op 2 attributen!) en druk af----");
        laptopList1.stream().sorted(Comparator.comparingInt(Laptop::getStorage)
                        .thenComparing(Laptop::getName))
                .forEach(System.out::println);

        String names = laptopList1.stream().map(laptop -> laptop.getName()).distinct().sorted(Comparator.reverseOrder()).collect(Collectors.joining(", "));
        System.out.println(" All Laptops recordings in capital letters, reverse sorted and without duplicates:\n" +names);

        // TODO lees FAQ op canvas
        System.out.println("---- optionalLaptop --------");
        Optional<Laptop> optionalLaptop = laptopList1.stream().filter(e -> e.getRam() > 16).findAny();
//        System.out.printf("A random Laptop with more than %.2f GB RAM:%\"",
//                laptopList1.stream().filter(e -> e.getRam() > 16).findAny());
        System.out.println(optionalLaptop);

        System.out.println("--------------sorted -------------------");
        List<String> sortedLaptopsNames = laptopData.stream()
                .filter(laptop -> laptop.getName().startsWith("S"))
                .map(Laptop::getName)
                .sorted()
                .collect(Collectors.toList());

        System.out.println("List of sorted Laptops Names starting with 'S':");
        System.out.println(sortedLaptopsNames);


        System.out.println("Sublist of Laptops made After 2022:");
        laptopData.stream().filter(e -> e.getReleaseDate().isAfter(LocalDate.of(2022, 1, 1))).forEach(System.out::println);

        System.out.println("Sublist of Laptops made before 2022:");
        laptopData.stream().filter(e -> e.getReleaseDate().isBefore(LocalDate.of(2022, 1, 1))).forEach(System.out::println);

        System.out.println("group all objects into certain categories :");

     Map<String, List<Laptop>> laptopsByMacAddress = laptopData.stream()
             .collect(Collectors.groupingBy(Laptop::getMacAdres));

     laptopsByMacAddress.forEach((k, v) -> {
      String laptopNames = v.stream()
              .sorted(Comparator.comparing(Laptop::getName))
              .map(Laptop::getName)
              .collect(Collectors.joining(", "));

             System.out.println(k + " : " + laptopNames);
     });
    }
}