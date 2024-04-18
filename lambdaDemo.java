public class lambdaDemo {
    public static void main(String[] args) {
        inLambda in = () -> System.out.println("Talking");
       

        ShapeArea sa = (side) -> side * side;

        int area = sa.sqArea(10);
        System.out.println("Area: " + area);
         
       double pi=3.14;
        demo dm=(r)-> r*r;
        double areas=dm.circle(7);
        System.out.println("Are of circle : a="+pi*areas) ;

    }
}

interface ShapeArea {
    int sqArea(int side);
}

interface inLambda {
    void talk();
}
/**
 * InnerlambdaDemo
 */
 interface demo {

    int circle(int r );
    
}
