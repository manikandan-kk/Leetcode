package sorting;

public class MergeSort {

    //O(n log n)
    private static int[] sort(int[] arr, int low, int high) {
        if (low == high) {
            int [] singleValueArray = new int[1];
            singleValueArray[0] = arr[low];
            return  singleValueArray;
        }
        int mid = (low + high) / 2;
        int[] leftArray = sort(arr, low, mid);
        int[] rightArray = sort(arr, mid+1 ,  high);
        int[] sorted = new int[leftArray.length + rightArray.length];

        int i = 0, j = 0, ptr = 0;
        while (i < leftArray.length && j < rightArray.length) {
            if (leftArray[i] <= rightArray[j]) {
                sorted[ptr++] = leftArray[i];
                i++;
            } else {
                sorted[ptr++] = rightArray[j];
                j++;
            }
        }

        while (i < leftArray.length) {
            sorted[ptr++] = leftArray[i++];
        }

        while (j < rightArray.length) {
            sorted[ptr++] = rightArray[j++];
        }

        //logic to iterate and sort leftArray and rightArray

        return sorted;
    }

    public static void main(String[] args) {
        int[] arr = {5, 6, 4, 3, 1, 2, 8, 11, 5, 6, 36, 23};
        int[] sorted = sort(arr, 0, arr.length - 1);

        for (int i = 0; i < sorted.length; i++) {
            System.out.print(sorted[i] + " ");
        }

        System.out.println();
    }
}