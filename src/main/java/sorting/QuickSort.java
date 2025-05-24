package sorting;

public class QuickSort {

    private static void sort(int[] arr, int low, int high) {
        if (low >= high) {
            return;
        }
        int pivot = low;
        int i = low, j = high;
        while (i <= j) {
            if (arr[i] > arr[pivot] && arr[j] <= arr[pivot]) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
                continue;
            }
            if(arr[i] <= arr[pivot]) {
                i++;
            }
            if (arr[j] > arr[pivot]) {
                j--;
            }
        }
        int temp = arr[j];
        arr[j] = arr[pivot];
        arr[pivot] = temp;
        sort(arr, low, j - 1);
        sort(arr, j + 1, high);
    }

    public static void main(String[] args) {
        int[] arr = {5, 6, 4, 3, 1, 2, 8, 11, 5, 6, 36, 23};

        sort(arr, 0, arr.length - 1);

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }

        System.out.println();
    }
}
