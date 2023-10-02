import java.util.Arrays;

public class RadixSort {
    // A utility function to get the maximum value in the input array
    private static int getMax(int[] arr) {
        int max = arr[0];
        for (int num : arr) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }

    // A function to perform counting sort based on the digit represented by exp
    private static void countSort(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[10]; // 10 digits (0-9)

        // Initialize count array
        Arrays.fill(count, 0);

        // Count the occurrences of each digit in the input array
        for (int i = 0; i < n; i++) {
            count[(arr[i] / exp) % 10]++;
        }

        // Calculate the cumulative count
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // Build the output array by placing elements in the correct position
        for (int i = n - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }

        // Copy the sorted elements back to the original array
        System.arraycopy(output, 0, arr, 0, n);
    }

    // Radix Sort function to sort an array of integers
    public static void radixSort(int[] arr) {
        int max = getMax(arr);

        // Perform counting sort for every digit place value
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countSort(arr, exp);
        }
    }

    // A utility function to print an array
    private static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {170, 45, 75, 90, 802, 24, 2, 66};
        System.out.println("Original Array:");
        printArray(arr);

        radixSort(arr);

        System.out.println("Sorted Array:");
        printArray(arr);
    }
}
