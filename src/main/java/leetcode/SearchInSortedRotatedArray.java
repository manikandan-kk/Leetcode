package leetcode;

public class SearchInSortedRotatedArray {

    static int searchRecursive(int[] nums, int target, int low, int high) {
        if (low == high) {
            if (nums[low] == target) return low;
            return -1;
        }
        int mid = (low + high) / 2;
        //check complete overlap or partial overlap
        if ((target >= nums[low] && target <= nums[mid]) ||
                (nums[low] > nums[mid] && ( target >= nums[low] || target <= nums[mid] ))) {
            return searchRecursive(nums, target, low, mid);
        } else { //ignore if completely no overlap
            return searchRecursive(nums, target, mid + 1, high);
        }
    }

    public static void main(String[] args) {
        int[] nums = {4,5,6,7,0,1,2};
        int target = 0;
        int value =  searchRecursive(nums, target, 0, nums.length - 1);
        System.out.println(value);
    }

}

