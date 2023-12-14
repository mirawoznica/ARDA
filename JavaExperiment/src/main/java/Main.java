package main.java;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Main {

    // Bubble Sort
    static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (arr[j] > arr[j + 1]) {
                    // swap arr[j+1] and arr[j]
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
    }

    // Selection Sort
    static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int min_idx = i;
            for (int j = i + 1; j < n; j++)
                if (arr[j] < arr[min_idx])
                    min_idx = j;
            // Swap the found minimum element with the first element
            int temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;
        }
    }

    // Insertion Sort
    static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }

    static void mergeSort(int[] arr, int l, int r) {
        if (l < r) {
            // Find the middle point
            int m = (l + r) / 2;

            // Sort first and second halves
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);

            // Merge the sorted halves
            merge(arr, l, m, r);
        }
    }

    // Merges two subarrays of arr[]
    static void merge(int[] arr, int l, int m, int r) {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        // Create temp arrays
        int[] L = new int[n1];
        int[] R = new int[n2];

        //Copy data to temp arrays
        System.arraycopy(arr, l, L, 0, n1);
        System.arraycopy(arr, m + 1, R, 0, n2);

        // Merge the temp arrays
        int i = 0, j = 0;
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements of L[] if any
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        // Copy remaining elements of R[] if any
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    static void quickSort(int[] arr, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);

            // Recursively sort elements before partition and after partition
            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }
    }

    private static int partition(int[] arr, int begin, int end) {
        int pivot = arr[end];
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;

                int swapTemp = arr[i];
                arr[i] = arr[j];
                arr[j] = swapTemp;
            }
        }

        int swapTemp = arr[i + 1];
        arr[i + 1] = arr[end];
        arr[end] = swapTemp;

        return i + 1;
    }

    static void heapSort(int[] arr) {
        int n = arr.length;

        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        // One by one extract an element from heap
        for (int i = n - 1; i >= 0; i--) {
            // Move current root to end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // call max heapify on the reduced heap
            heapify(arr, i, 0);
        }
    }

    // To heapify a subtree rooted with node i which is an index in arr[]
    static void heapify(int[] arr, int n, int i) {
        int largest = i; // Initialize largest as root
        int l = 2 * i + 1; // left = 2*i + 1
        int r = 2 * i + 2; // right = 2*i + 2

        // If left child is larger than root
        if (l < n && arr[l] > arr[largest])
            largest = l;

        // If right child is larger than largest so far
        if (r < n && arr[r] > arr[largest])
            largest = r;

        // If largest is not root
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            // Recursively heapify the affected sub-tree
            heapify(arr, n, largest);
        }
    }


    static void shellSort(int[] arr) {
        int n = arr.length;

        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i += 1) {
                int temp = arr[i];
                int j;
                for (j = i; j >= gap && arr[j - gap] > temp; j -= gap)
                    arr[j] = arr[j - gap];
                arr[j] = temp;
            }
        }
    }



    // Other sorting algorithms like Merge Sort, Quick Sort, Heap Sort, etc., can be added here

    public static void main(String[] args) {
        // Sample arrays for testing
        List<int[]> testArraysList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("Data/test_arrays.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                int[] arr = parseLineToArray(line);
                testArraysList.add(arr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Convert List to array for easier handling
        int[][] testArrays = testArraysList.toArray(new int[0][]);

        StringBuilder csvOutput = new StringBuilder();
        csvOutput.append("Algorithm");
        for (int i = 0; i < testArrays.length; i++) {
            csvOutput.append(",Time Array ").append(i + 1).append(" (nanoseconds)");
        }
        csvOutput.append("\n");

        // Function to test sorting algorithms
        BiFunction<String, SortFunction, String> testFunction = (name, function) -> {
            StringBuilder result = new StringBuilder(name);
            for (int[] arr : testArrays) {
                result.append(",").append(testSortingAlgorithm(arr.clone(), function));
            }
            return result.toString();
        };

        // Running tests for each sorting algorithm
        csvOutput.append(testFunction.apply("Bubble Sort", Main::bubbleSort)).append("\n");
        csvOutput.append(testFunction.apply("Selection Sort", Main::selectionSort)).append("\n");
        csvOutput.append(testFunction.apply("Insertion Sort", Main::insertionSort)).append("\n");
        csvOutput.append(testFunction.apply("Merge Sort", array -> mergeSort(array, 0, array.length - 1))).append("\n");
        csvOutput.append(testFunction.apply("Quick Sort", array -> quickSort(array, 0, array.length - 1))).append("\n");
        csvOutput.append(testFunction.apply("Heap Sort", Main::heapSort)).append("\n");
        csvOutput.append(testFunction.apply("Shell Sort", Main::shellSort)).append("\n");

        try {
            writeToFile(csvOutput.toString(), "sorting_algorithm_times.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static long testSortingAlgorithm(int[] arr, SortFunction function) {
        long startTime = System.nanoTime();
        function.sort(arr);
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    private static void writeToFile(String content, String fileName) throws IOException {
        String filePath = "Data/" + fileName; // Updated file path
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(content);
        }
    }

    private static int[] parseLineToArray(String line) {
        String[] tokens = line.split(",");
        int[] arr = new int[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            arr[i] = Integer.parseInt(tokens[i].trim()); // Trim to remove any spaces
        }
        return arr;
    }

    // Functional interface for sort methods
    @FunctionalInterface
    interface SortFunction {
        void sort(int[] arr);
    }
}
