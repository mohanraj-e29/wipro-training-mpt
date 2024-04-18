
    public class thread {
        public static void main(String[] args) {
            Thread th = new Thread() {
                @Override
                public void run() {
                    for (int i = 1; i <=10; i++) {
                        System.out.println(i);
                        try {
                            
                            Thread.sleep(1000); 
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        
                    }
                }
            };
            Thread th2 = new Thread() {
                @Override
                public void run() {
                    for (int i = 1; i <= 10; i++) {
                        
                        try {
                            System.out.println(i * -1);
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
            th.start();
            th2.start();
        }
    }
    

