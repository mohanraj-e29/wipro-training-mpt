import java.util.Scanner;

public class switchex {
    public static void main(String[] args) {
        Scanner scanner =new Scanner(System.in);

        System.out.println("Enter the Number 1 to 4:");
        int num=scanner.nextInt();

        
        // other way of declaring scanner or input
        // System.out.print("Enter an integer: ");
        // int number=Integer.parseInt(scanner.nextLine());

        switch (num) {
            case 1:
                 System.out.println("Login the game...");
                break;
            case 2:
                 System.out.println("The game is going to start");
                break;
            case 3:
                 System.out.println("Play the game..");
                break;
            case 4:
                System.out.println("Quit the game..");
               break;
        
            default:
                System.out.println("Invalid input number");
                break;
        }
    }
}
