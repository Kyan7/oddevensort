import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MyClass {
    public List<Integer> list = new ArrayList<>();
    public int[] array;

    public class MyThread implements Runnable {
        public int i;

        public MyThread(int i) {
            this.i = i;
        }

        public void run()
        {
            if (list.get(this.i) > list.get(this.i + 1)) {
                Collections.swap(list, this.i, this.i + 1);
            }
        }
    }

    /*
    public class Multithreader2 implements Runnable {
        public int first;
        public int second;
        public int firstIndex;
        public int secondIndex;

        public Multithreader2(int i) {
            this.first = array[i];
            this.second = array[i + 1];
            this.firstIndex = i;
            this.secondIndex = i + 1;
        }

        public void run()
        {
            if (first > second) {
                swap();
            }
            array[firstIndex] = first;
            array[secondIndex] = second;

        }

        public void swap() {
            int temp = this.first;
            this.first = this.second;
            this.second = temp;
        }
    }
    */

    public boolean isSorted() {
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) > list.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

    /*
    public boolean isSorted2() {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                return false;
            }
        }
        return true;
    }
     */

    public void oddSort(int threadCount) throws InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(threadCount);
        for (int i = 0; i < list.size() - 1; i += 2) {
            Runnable mt = new MyThread(i);
            es.execute(mt);
        }
        es.shutdown();
        es.awaitTermination(1, TimeUnit.MINUTES);
    }

    /*
    public void oddSort2(int threadCount) throws InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(threadCount);
        for (int i = 0; i < array.length - 1; i += 2) {
            es.execute(new Multithreader2(i));
        }
        es.shutdown();
        es.awaitTermination(1, TimeUnit.MINUTES);
    }

     */

    public void evenSort(int threadCount) throws InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(threadCount);
        for (int i = 1; i < list.size() - 1; i += 2) {
            Runnable mt = new MyThread(i);
            es.execute(mt);
        }
        es.shutdown();
        es.awaitTermination(1, TimeUnit.MINUTES);
    }

    /*
    public void evenSort2(int threadCount) throws InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(threadCount);
        for (int i = 1; i < array.length - 1; i += 2) {
            es.execute(new Multithreader2(i));
        }
        es.shutdown();
        es.awaitTermination(1, TimeUnit.MINUTES);
    }
     */

    public void oddEvenSort(int threadCount) throws InterruptedException {
        while (!isSorted()) {
            oddSort(threadCount);
            evenSort(threadCount);
        }
    }

    /*
    public void oddEvenSort2(int threadCount) throws InterruptedException {
        while (!isSorted2()) {
            oddSort2(threadCount);
            evenSort2(threadCount);
        }
    }
     */

    public void bubbleSort() {
        while(!isSorted()) {
            for (int i = 0; i < list.size() - 1; i++) {
                if (list.get(i) > list.get(i + 1)) {
                    Integer first = list.get(i);
                    Integer second = list.get(i + 1);

                    Integer temp = first;
                    list.set(i, second);
                    list.set(i + 1, temp);

                }
            }
        }
    }

    public static List<Integer> generateOrderedList(int maxElement) {
        List<Integer> orderedList = new ArrayList<>();
        for (int i = 0; i <= maxElement; i++) {
            orderedList.add(i);
        }
        return orderedList;
    }

    public static List<Integer> generateReverseOrderedList(int maxElement) {
        List<Integer> reverseOrderedList = new ArrayList<>();
        for (int i = maxElement; i >= 0; i--) {
            reverseOrderedList.add(i);
        }
        return reverseOrderedList;
    }

    public List<List<Integer>> generateTestLists() {
        List<List<Integer>> testLists = new ArrayList<>();
        testLists.add(new ArrayList<>(Arrays.asList(2, 3 , 5, 8, 7 ,6, 1, 3)));
        testLists.add(new ArrayList<>(Arrays.asList(2, 3)));
        testLists.add(new ArrayList<>(Arrays.asList(2, 4 , 9, 1, 17 ,6, 1, 3, 11, 100, 2, 15, 39, 71, 110, 57, 70, 0, 5, 12, 31, 16, 9)));
        testLists.add(generateOrderedList(1000));
        testLists.add(generateReverseOrderedList(1000));
        testLists.add(generateReverseOrderedList(10000));

        return testLists;
    }
}
