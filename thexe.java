public class thexe {
    public static void main(String[] args) throws InterruptedException {
        Thread th = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 6; i++) {
                    System.out.println(i);
                }
            }
        };
        
        Thread th2 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 6; i++) {
                    System.out.println(i * -1);
                }
            }
        };
        
        th.start();
        
        th2.start();
       
    }
}
