package kollections;

public class Kollections {

    private Kollections() {
    }

    public static <T extends Comparable<T>> void selectionSort(List<T> list) {
        //TODO: use the selectionSort from the introduction module and make it generic!
        for(int i = 0; i < list.size() -1; i++){
            int indexSmallest = i;
            for (int j = i+1; j < list.size(); j++){
                if (list.get(j).compareTo(list.get(indexSmallest)) < 0){
                    indexSmallest = j;
                }
            }
            T temp = list.get(i);
            list.set(i, list.get(indexSmallest));
            list.set(indexSmallest, temp);
        }
    }

    public static <T extends Comparable<T>> void mergeSort(List<T> list) {
        //splits in 2 delen, sorteer elk deel en merge dan
        mergeSort(list, 0, list.size() - 1);
    }

    //from inclusive, to inclusive
    private static <T extends Comparable<T>> void mergeSort(List<T> list, int from, int to) {
        //splits in 2 delen, sorteer elk deel en merge dan
        if (from > to) throw new IllegalArgumentException("from should be before to");
        if (to - from == 0) return;//trivial case
        //TODO: complete the algorithm! Use recursive calls and the merge method...
        int mid = (from + to) / 2;
        mergeSort(list, from, mid); // Sorteer het linkerdeel
        mergeSort(list, mid + 1, to); // Sorteer het rechterdeel

        merge(list, from, mid, to); // Voeg de gesorteerde delen samen
    }

    private static <T extends Comparable<T>> void merge(List<T> list, int from, int mid, int to) {
        int n1 = mid - from + 1;
        int n2 = to - mid;

        // Maak tijdelijke arrays voor de gesplitste delen
        List<T> left = new ArrayList<>();
        List<T> right = new ArrayList<>();

        for (int i = 0; i < n1; ++i) {
            left.add(list.get(from + i));
        }
        for (int j = 0; j < n2; ++j) {
            right.add(list.get(mid + 1 + j));
        }

        int i = 0, j = 0, k = from;

        // Voeg de gesorteerde delen samen
        while (i < n1 && j < n2) {
            if (left.get(i).compareTo(right.get(j)) <= 0) {
                list.set(k, left.get(i));
                i++;
            } else {
                list.set(k, right.get(j));
                j++;
            }
            k++;
        }

        // Voeg de resterende elementen toe aan de samengevoegde lijst
        while (i < n1) {
            list.set(k, left.get(i));
            i++;
            k++;
        }

        while (j < n2) {
            list.set(k, right.get(j));
            j++;
            k++;
        }
    }
    public static <T extends Comparable<T>> void quickSort(List<T> list) {
        quickSort(list, 0, list.size());
    }

    private static <T extends Comparable<T>> void quickSort(List<T> list, int start, int end) {
        if (end - start <= 0) return;
        int i = start;
        int j = end - 1;
        boolean movingI = true;
        while (i < j) {
            if (list.get(i).compareTo(list.get(j)) > 0) {
                //swap(list, i, j);
                T tmp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, tmp);
                movingI = !movingI;
            } else {
                if (movingI) {
                    i++;
                } else {
                    j--;
                }
            }
        }
        quickSort(list, start, i);
        quickSort(list, i + 1, end);
    }

    public static <T> int lineairSearch(List<T> list, T element) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(element)) return i;
        }
        return -1;
    }

    public static <T extends Comparable<T>> int binarySearch(List<T> sortedList, T element) {
        return binarySearch(sortedList, element, 0, sortedList.size());
    }

    private static <T extends Comparable<T>> int binarySearch(List<T> sortedList, T element, int from, int to) {
        //TODO: implement this method!
        if (sortedList.size() == 0) {
            return -1;
        }
        if (to >= from) {
            int mid = (from + (to - from) / 2) - 1;

            if (sortedList.get(mid) == element) {
                return mid;
            }
            if (element.compareTo(sortedList.get(mid)) < 0) {
                return binarySearch(sortedList, element, from, mid);
            } else {
                return binarySearch(sortedList, element, mid + 2, to);
            }
        }

        return -1;
    }
}
