import kollections.Kollections;
import kollections.lists.LinkedList;
import kollections.maps.HashMap;
import kollections.maps.Map;
import model.Category;
import model.Laptop;
import model.LaptopFactory;

import kollections.lists.List;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.stream.Stream;

public class Demo_8 {
    public static void main(String[] args) {

        Laptop empty = LaptopFactory.newEmptyLaptop();

        System.out.println("Laptop Empty");
        System.out.println("===================================");
        System.out.println(empty);
        System.out.println();

        Laptop filled = LaptopFactory.newFilledLaptop("HP", "Zwart", 899.99, 16, 512, Category.GAMING, LocalDate.of(2022, 5, 15), "ABCD12345");
        System.out.println("Laptop Filled");
        System.out.println("===================================");
        System.out.println(filled);
        System.out.println();

        System.out.println("LaptopFactory Random");
        System.out.println("===================================");
        Stream.generate(LaptopFactory::newRandomLaptop)
                .limit(30)
                .sorted(Comparator.comparing(Laptop::getCategory))
                .forEach(System.out::println);

        System.out.println("=========================\n");
        System.out.println("PerformanceTester");
        System.out.println("=========================");
        List<Laptop> laptopList =  PerformanceTester.randomList(10);
        for (int i = 0; i < laptopList.size(); i++) {
            System.out.println(laptopList.get(i));
        }


        System.out.println("=========================\n");

        System.out.println("compareArrayListAndLinkedList");
        int n = 20000;
        PerformanceTester.compareArrayListAndLinkedList(n);



        System.out.println("=======================\n");
        System.out.println("selectionSort");
        System.out.println("===================================");
        List<Laptop> laptopListt = PerformanceTester.randomList(30);
        Kollections.selectionSort(laptopListt);
        for (int i = 0; i < laptopListt.size(); i++) {
            System.out.println(laptopListt.get(i));
        }

        System.out.println("=======================\n");
        System.out.println("mergeSort");
        System.out.println("===================================");
        List<Laptop> laptopList1 = PerformanceTester.randomList(30);
        Kollections.mergeSort(laptopList1);
        for (int i = 0; i < laptopList1.size(); i++){
            System.out.println(laptopList1.get(i));
        }

        System.out.println("=======================\n");

        System.out.println("Amount of Compares on SelectionSort");
        System.out.println("===================================");
        PerformanceTester.testSelectionSort();
        System.out.println();

        System.out.println("Amount of Compares on MergeSort");
        System.out.println("===================================");
        PerformanceTester.testMergeSort();


        System.out.println("=======================\n");
        System.out.println("quik sort");
        System.out.println("===================================");
        List<Laptop> laptopList2 = PerformanceTester.randomList(30);
        Kollections.quickSort(laptopList2);
        for (int i = 0; i < laptopList2.size(); i++){
            System.out.println(laptopList2.get(i));
        }


        System.out.println("===================================\n");
        System.out.println("Searching");

        List<Laptop> laptopList3 = PerformanceTester.randomList(30);
        Laptop laptop = laptopList3.get(0);
        List<Laptop> laptopList4 = new LinkedList<>();
        Laptop emptyLaptop = LaptopFactory.newEmptyLaptop();
        Kollections.mergeSort(laptopList3);
        System.out.println("Index of laptop " + laptop.getMacAdres() + ": " + Kollections.lineairSearch(laptopList3, laptop));
        System.out.println("Index of laptop " + laptop.getMacAdres() + ": " + Kollections.binarySearch(laptopList3, laptop));

        System.out.println("Index of laptop " + emptyLaptop.getMacAdres() + ": " + Kollections.lineairSearch(laptopList4, emptyLaptop));
        System.out.println("Index of laptop " + emptyLaptop.getMacAdres() + ": " + Kollections.binarySearch(laptopList4, emptyLaptop));

         System.out.println("Hashmap");
        System.out.println("===================================");
        Map<String, Laptop> laptopMap = new HashMap<>();
        laptopMap.put("key1", LaptopFactory.newRandomLaptop());
        laptopMap.put("key2", LaptopFactory.newRandomLaptop());
        System.out.println(laptopMap.get("key1"));
        System.out.println(laptopMap.get("key2"));
        System.out.println();

        PerformanceTester.compareListMapToHashMap(100);

        System.out.println("Sets");
        System.out.println("===================================");
        PerformanceTester.compareArraySetToTreeSet(100);
    }
}
