import java.util.HashMap;
import java.util.Map;

public class twosum {
    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] result = twoSum(nums, target);
        if (result != null) {
            System.out.println("Indices of the two numbers that add up to the target:");
            System.out.println("Index 1: " + result[0]);
            System.out.println("Index 2: " + result[1]);
        } else {
            System.out.println("No solution found.");
        }
    }

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        return null; 
    }
}
