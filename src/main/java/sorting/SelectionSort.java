package sorting;

public class SelectionSort {

    public static void main(String[] args) {
        int[] arr = {5, 6, 4, 3, 1, 2, 8, 11, 5, 6, 36, 23};

        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[i]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }

        System.out.println();
    }

}
