import java.util.Arrays;

public class slicarraytask {
    public static void main(String[] args) {
        int [] arr={1,2,3,4,5,6,7,8,9,10};
        int startIndex = 2;
        int endIndex = 9;

       
        int[] slicedArray = Arrays.stream(arr, startIndex , endIndex).toArray();

       
        System.out.println("Sliced array: " + Arrays.toString(slicedArray));
       
    }
}
