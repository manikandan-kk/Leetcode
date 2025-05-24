package sorting;

public class BubbleSort {

    public static void main(String[] args) {
        int[] arr = {5, 6, 4, 3, 1, 2, 8, 11, 5, 6, 36, 23};

        for (int i = 1; i <= arr.length; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }

        System.out.println();
    }

}
