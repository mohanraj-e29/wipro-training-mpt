import java.util.Arrays;

public class sorting {
    public static void main(String[] args) {
        int[] arr = {1, -9, 0, -6, 5, 45, 34};
        /* Arrays.sort(arr);
        for (int i : arr) {
            System.out.println(i);
        } */

        //Bubble Sort...
       /*
        * In Bubble Sort algorithm, 

traverse from left and compare adjacent elements and the higher one is placed at right side. 
In this way, the largest element is moved to the rightmost end at first. 
This process is then continued to find the second largest and place it and so on until the data is sorted.
        */
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
 
         /*
         * Selection sort is a simple and efficient sorting algorithm that works by repeatedly selecting the
         *  smallest (or largest) element from the unsorted portion of the list and moving it to the sorted 
         * portion of the list. 
         */
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

            /*
             * Insertion sort is a simple sorting algorithm that works by iteratively inserting each element of an
             *  unsorted list into its correct position in a sorted portion of the list. It is a stable sorting
             *  algorithm, meaning that elements with equal values maintain their relative order in the sorted 
             * output.
               Insertion sort is like sorting playing cards in your hands. You split the cards into two groups:
                the sorted cards and the unsorted cards. Then, you pick a card from the unsorted group and put it
                 in the right place in the sorted group.
             */

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
              /*
               * Linear search is used to search a key element from multiple elements. Linear search is less used 
               * today because it is slower than binary search and hashing.
               */
            int target=45;
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == target) {
                    System.out.println(i+"finded..."); // Return the index of the target element if found
                }
                else{
                    System.out.println("not find..");
                }
            }
            
    }


    /*
     * Binary search is one of the searching techniques applied when the input is sorted here we are focusing on 
     * finding the middle element that acts as a reference frame whether to go left or right to it as the elements
     *  are already sorted. This searching helps in optimizing the search technique with every iteration is
     *  referred to as binary search and readers do stress over it as it is indirectly applied in solving 
     * questions.
     */
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
