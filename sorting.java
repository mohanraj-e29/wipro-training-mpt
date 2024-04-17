import java.util.Arrays;

public class sorting {
    public static int main(String[] args) {
        int[] arr = {1, -9, 0, -6, 5, 45, 34};
        /* Arrays.sort(arr);
        for (int i : arr) {
            System.out.println(i);
        } */

        //Bubble Sort...
        System.out.println("---------------------------Bubble sort-----------------------");
        for (int i = 0; i < arr.length; i++) {
            for (int j = i+1; j < arr.length; j++) { // optimsation
                if(arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }

        for (int i : arr) {
            System.out.print(i+" ");
        }

        System.out.println("---------------------------selection sort-----------------------");

        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
            for (int i : arr) {
                System.out.print(i + " ");
            }


            System.out.println("---------------------------insertion sort-----------------------");


            for (int i = 1; i < arr.length; ++i) {
                int key = arr[i];
                int j = i - 1;
    
                // Move elements of arr[0..i-1], that are greater than key,
                // to one position ahead of their current position
                while (j >= 0 && arr[j] > key) {
                    arr[j + 1] = arr[j];
                    j = j - 1;
                }
                arr[j + 1] = key;
            }
            for (int i : arr) {
                System.out.print(i + " ");
            }


            System.out.println("---------------------------Linear search-----------------------");
              
            int target=45;
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == target) {
                    return i; // Return the index of the target element if found
                }
            }
            return -1; // Return -1 if target element is not found
    }

    //    int left = 0;
    //     int right = arr.length - 1;

    //     while (left <= right) {
    //         int mid = left + (right - left) / 2;

    //         if (arr[mid] == target) {
    //             return mid; // Return index of target element if found
    //         }

    //         if (arr[mid] < target) {
    //             left = mid + 1; // Target is in the right half
    //         } else {
    //             right = mid - 1; // Target is in the left half
    //         }
    //     }

    //     return -1; // Return -1 if target element is not found
    
}
