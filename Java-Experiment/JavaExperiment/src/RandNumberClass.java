import java.util.Arrays;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;

public class RandNumberClass {
    private static final int NUM_ARRAYS = 500;
    private static final int INITIAL_SIZE = 3;
    private static final int SIZE_INCREMENT = 3;

    private int[][] arrays;

    public RandNumberClass() {
        this.arrays = new int[NUM_ARRAYS][];
        generateAndSortArrays();
        writeToCSV("test_arrays.csv");
    }

    private void generateAndSortArrays() {
        Random random = new Random();
        int size = INITIAL_SIZE;

        for (int i = 0; i < NUM_ARRAYS; i++) {
            arrays[i] = new int[size];
            for (int j = 0; j < size; j++) {
                arrays[i][j] = random.nextInt(1000); // Random numbers up to 1000
            }
            Arrays.sort(arrays[i]); // Sort the array
            size += SIZE_INCREMENT; // Increase the size for the next array
        }
    }

    private void writeToCSV(String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            for (int[] array : arrays) {
                String line = Arrays.toString(array)
                        .replaceAll("[\\[\\] ]", "")
                        .concat("\n");
                writer.write(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int[][] getArrays() {
        return arrays;
    }

    public static void main(String[] args) {
        RandNumberClass randNumberClass = new RandNumberClass();
        int[][] arrays = randNumberClass.getArrays();

        // Example: Print the sorted arrays
        for (int i = 0; i < arrays.length; i++) {
            System.out.println("Array " + (i + 1) + " (Size " + arrays[i].length + "): " + Arrays.toString(arrays[i]));
        }
    }
}
