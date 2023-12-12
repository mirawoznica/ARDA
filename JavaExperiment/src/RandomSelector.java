import java.util.Random;
import java.util.HashSet;
import java.util.Set;

public class RandomSelector {

    // Method to select three random numbers from a given array
    public static int[] selectThreeRandomNumbers(int[] array) {
        if (array == null || array.length < 3) {
            throw new IllegalArgumentException("Array must have at least 3 elements.");
        }

        Random random = new Random();
        Set<Integer> selectedIndices = new HashSet<>();

        while (selectedIndices.size() < 3) {
            int randomIndex = random.nextInt(array.length);
            selectedIndices.add(randomIndex);
        }

        int[] selectedNumbers = new int[3];
        int i = 0;
        for (int index : selectedIndices) {
            selectedNumbers[i++] = array[index];
        }

        return selectedNumbers;
    }

    public static void main(String[] args) {
        int[] exampleArray = {5, 5, 5, 9, 13, 19, 21, 23, 24, 25, 26, 27, 28, 29, 30, 31, 31, 33, 33, 34, 35, 36, 38, 40, 42, 42, 43, 45, 52, 55, 55, 55, 58, 62, 75, 76, 76, 77, 78, 78, 81, 82, 90, 94, 98};
        int[] selectedNumbers = selectThreeRandomNumbers(exampleArray);

        System.out.println("Selected Numbers: ");
        for (int number : selectedNumbers) {
            System.out.println(number);
        }
    }
}
