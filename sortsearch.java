import java.util.Arrays;

public class sortsearch {
    public static void main(String[] args) {
        int[] array = {3, 7, 1, 9, 5, 2, 8, 4, 6};

        BruteForceSort(array);

        System.out.println("Sorted array:");
        for (int num : array) {
            System.out.print(num + " ");
        }
        System.out.println();
       

        int target = 5; 
        int index = PerformLinearSearch(array, target);

        if (index != -1) {
            System.out.println("Element " + target + " found at index " + index);
        } else {
            System.out.println("Element " + target + " not found in the array");
        }

        
    }
    public static void BruteForceSort(int[] array) {
        Arrays.sort(array);
    }


    public static int PerformLinearSearch(int[] array, int target) {
        int n = array.length;
        for (int i = 0; i < n; i++) {
            if (array[i] == target) {
                return i; 
            }
        }
        return -1; 
    }

    
   
}
