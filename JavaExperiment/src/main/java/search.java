package main.java;

public class search {


    // Linear Search
    static int linearSearch(int[] arr, int x) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == x)
                return i;
        }
        return -1;
        // Time Complexity: O(n)
        // Space Complexity: O(1)
    }

    // Binary Search
    static int binarySearch(int[] arr, int x) {
        int l = 0, r = arr.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (arr[m] == x)
                return m;
            if (arr[m] < x)
                l = m + 1;
            else
                r = m - 1;
        }
        return -1;
        // Time Complexity: O(log n)
        // Space Complexity: O(1)
    }

    // Jump Search
    static int jumpSearch(int[] arr, int x) {
        int n = arr.length;
        int step = (int)Math.floor(Math.sqrt(n));
        int prev = 0;
        while (arr[Math.min(step, n) - 1] < x) {
            prev = step;
            step += (int)Math.floor(Math.sqrt(n));
            if (prev >= n)
                return -1;
        }
        while (arr[prev] < x) {
            prev++;
            if (prev == Math.min(step, n))
                return -1;
        }
        if (arr[prev] == x)
            return prev;
        return -1;
        // Time Complexity: O(√n)
        // Space Complexity: O(1)
    }

    // Interpolation Search
    static int interpolationSearch(int[] arr, int x) {
        int lo = 0, hi = (arr.length - 1);

        while (lo <= hi && x >= arr[lo] && x <= arr[hi]) {
            if (arr[hi] == arr[lo]) {  // Check to prevent division by zero
                return (arr[lo] == x) ? lo : -1;
            }

            int pos = lo + (((hi - lo) / (arr[hi] - arr[lo])) * (x - arr[lo]));

            if (arr[pos] == x) {
                return pos;
            }

            if (arr[pos] < x) {
                lo = pos + 1;
            } else {
                hi = pos - 1;
            }
        }

        return -1;
    }


    // Exponential Search
    static int exponentialSearch(int[] arr, int x) {
        if (arr[0] == x)
            return 0;
        int i = 1;
        while (i < arr.length && arr[i] <= x)
            i = i * 2;
        return binarySearch(arr, i / 2, Math.min(i, arr.length - 1), x);
        // Time Complexity: O(log n)
        // Space Complexity: O(1)
    }

    // Helper method for Exponential Search
    private static int binarySearch(int[] arr, int l, int r, int x) {
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (arr[m] == x)
                return m;
            if (arr[m] < x)
                l = m + 1;
            else
                r = m - 1;
        }
        return -1;
    }

    // Fibonacci Search
    static int fibonacciSearch(int[] arr, int x) {
        int fibMMm2 = 0;   // (m-2)'th Fibonacci No.
        int fibMMm1 = 1;   // (m-1)'th Fibonacci No.
        int fibM = fibMMm2 + fibMMm1;  // m'th Fibonacci
        while (fibM < arr.length) {
            fibMMm2 = fibMMm1;
            fibMMm1 = fibM;
            fibM = fibMMm2 + fibMMm1;
        }
        int offset = -1;
        while (fibM > 1) {
            int i = Math.min(offset + fibMMm2, arr.length - 1);
            if (arr[i] < x) {
                fibM = fibMMm1;
                fibMMm1 = fibMMm2;
                fibMMm2 = fibM - fibMMm1;
                offset = i;
            } else if (arr[i] > x) {
                fibM = fibMMm2;
                fibMMm1 = fibMMm1 - fibMMm2;
                fibMMm2 = fibM - fibMMm1;
            } else return i;
        }
        if (fibMMm1 == 1 && arr[offset + 1] == x)
            return offset + 1;
        return -1;
        // Time Complexity: O(log n)
        // Space Complexity: O(1)
    }

    // Ternary Search
    static int ternarySearch(int[] arr, int x) {
        return ternarySearchRecursive(arr, 0, arr.length - 1, x);
        // Time Complexity: O(log3 n)
        // Space Complexity: O(log3 n) due to recursion stack
    }

    // Helper method for Ternary Search
    private static int ternarySearchRecursive(int[] arr, int l, int r, int x) {
        if (r >= l) {
            int mid1 = l + (r - l) / 3;
            int mid2 = r - (r - l) / 3;
            if (arr[mid1] == x) return mid1;
            if (arr[mid2] == x) return mid2;
            if (x < arr[mid1]) return ternarySearchRecursive(arr, l, mid1 - 1, x);
            if (x > arr[mid2]) return ternarySearchRecursive(arr, mid2 + 1, r, x);
            return ternarySearchRecursive(arr, mid1 + 1, mid2 - 1, x);
        }
        return -1;
    }



    public static void main(String[] args) {
        // Sample array for testing (should be sorted for binary, interpolation, exponential, Fibonacci, and ternary searches)
        int[] arr = {8, 10, 11, 12, 15, 15, 16, 19, 21, 22, 22, 24, 24, 32, 42, 42, 50, 51, 60, 67, 71, 73, 78, 82, 82, 85, 88, 90, 92, 93, 93, 94, 97};
        int target = 71;

        // 19, 71, 90
        // Test Linear Search
        testSearchAlgorithm("Linear Search", arr, target, search::linearSearch);

        // Test Binary Search
        testSearchAlgorithm("Binary Search", arr, target, search::binarySearch);

        // Test Jump Search
        testSearchAlgorithm("Jump Search", arr, target, search::jumpSearch);

        // Test Interpolation Search
        testSearchAlgorithm("Interpolation Search", arr, target, search::interpolationSearch);

        // Test Exponential Search
        testSearchAlgorithm("Exponential Search", arr, target, search::exponentialSearch);

        // Test Fibonacci Search
        testSearchAlgorithm("Fibonacci Search", arr, target, search::fibonacciSearch);

        // Test Ternary Search
        testSearchAlgorithm("Ternary Search", arr, target, search::ternarySearch);
    }

    // Method to test a search algorithm
    private static void testSearchAlgorithm(String algorithmName, int[] arr, int target, SearchFunction function) {
        long startTime = System.nanoTime();
        int resultIndex = function.search(arr, target);
        long endTime = System.nanoTime();

        System.out.println(algorithmName + " found at index: " + resultIndex + ", Time taken: " + (endTime - startTime) + " nanoseconds");
    }


    // Functional interface for search methods
    @FunctionalInterface
    interface SearchFunction {
        int search(int[] arr, int x);
    }}