import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        int index = 4;
        int testCount = 20;
        int threadCount = 2;

        MyClass mc = new MyClass();
        List<List<Integer>> testLists = mc.generateTestLists();

        List<Long> timeResults = new ArrayList<>();
        for (int i = 0; i < testCount; i++) {
            mc.list = new ArrayList<>();
            mc.list.addAll(testLists.get(index));

            long startTime = System.currentTimeMillis();
            mc.oddEvenSort(threadCount);
            long endTime = System.currentTimeMillis();
            long time = endTime - startTime;
            System.out.println(i + " : " + time);
            timeResults.add(time);
        }

        mc.list = new ArrayList<>();
        mc.list.addAll(testLists.get(index));

        long startTime = System.currentTimeMillis();
        mc.bubbleSort();
        long endTime = System.currentTimeMillis();
        long time = endTime - startTime;
        System.out.println("Bubble sort : " + time);

        int size = timeResults.size();
        long first = timeResults.get(0);
        long last = timeResults.get(size - 1);

        long sum = 0;
        long min = 0;
        long max = 0;

        for (int i = 0; i < size; i++) {
            Long result = timeResults.get(i);
            sum += result;
            if (i == 0 || result < min) {
                min = result;
            }
            if (result > max) {
                max = result;
            }
        }

        double avg = ((double) sum) / ((double) size);

        System.out.println("First result => " + first);
        System.out.println("Last result => " + last);
        System.out.println("Min result => " + min);
        System.out.println("Max result => " + max);
        System.out.println("Avg result => " + avg);
    }
}
