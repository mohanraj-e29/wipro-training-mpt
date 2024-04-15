public class patterns {
    /*
     * 
     * *
     * * *
     * * * *
    */
    public static void triangle(int rows, char ch) {
        for (int i = 1; i < rows; i++) {
            for (int j = 0; j < i; j++) {
                System.out.print(ch + " ");
            }
            System.out.println();
        }
    }
    
    public static void Itriangle(int rows, char ch) {
        for (int i = rows; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                System.out.print(ch + " ");
            }
            System.out.println();
        }
    }
    /*
     * * * * 
       * * *
         * *
           *
    */
    public static void IItriangle(int row,char ch) {
        // TODO: write the logic  
        for (int i= row; i>= 1; i--){  
            for (int j=row; j>i;j--){  
            System.out.print(" ");  
        }  
            for (int k=1;k<=i;k++){  
               System.out.print(ch+"");  
            }  
               System.out.println("");  
            }  
}  
    
    public static void main(String[] args) {
        triangle(5, '*');
        Itriangle(5, 'A');
        IItriangle(5,'$');
    }
}
