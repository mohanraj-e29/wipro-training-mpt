package array;

public class array {
    public static void main(String[] args) {
        // data_type[] arr_name = new data_type[];
        // String[] arr = new String[4]; // length of the Array, it is fixed
        //assiginng value into array
        // arr[0] = "Aaryan";
        // arr[1] = "Arush";
        // arr[2] = "Lakshya";
        // arr[3] = "Rohit";
        //   int[] numbers = {1, 2, 3, 4, 5};
        String[] arr={"mohan","raj","sathish","balu"};
        // arr.length = 4, range (0 - 3)
        System.out.println("Previous: ");
        for (String str : arr) {
            System.out.print(str + ", ");
        }
        System.out.println("\nNew: After swapping");
        String temp = arr[0]; //swapping
        arr[0] = arr[1];
        arr[1] = temp;

        for (String str : arr) {
            System.out.print(str + ", ");
        }
        System.out.println(arr[arr.length - 1]); // print the last element
    }
}
