public class recursive {
    public static void main(String[] args) {
        int[] arr={1,2,3,4,5};
        int index=0;
        int sum=sumarray(arr,index);
        System.out.println(sum);
     
    }

    private static int sumarray(int[]arr,int index) {
        // TODO Auto-generated method stub
        if(index==arr.length){
        return 0;
    }
        return arr[index] + sumarray(arr, index + 1);
    }
    
    
}
