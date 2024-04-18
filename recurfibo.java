

public class recurfibo {
    public static void main(String[] args) {
        int n = 10;
        int[] fibseries = new int[n];
        funfibonacci(fibseries, 0, n);
        System.out.println("Fibonacci sequence up to " + n + " elements: " + java.util.Arrays.toString(fibseries));
    }

    public static void funfibonacci(int[] arr, int index, int n) {
        if (index >= n) {
            return;
        }
        if (index == 0 || index == 1) {
            arr[index] = index;
        } else {
            arr[index] = arr[index - 1] + arr[index - 2]; 
        }
        funfibonacci(arr, index + 1, n); 
    }
}
