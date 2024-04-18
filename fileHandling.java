import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class fileHandling {
    public static void main(String[] args) {
        // Read
        File f = new File("./names1.txt");
       
         
        try {
            //f.createNewFile(); // create
            //f.deleteOnExit(); // delete

            //Read a file
            FileReader fil=new FileReader("./names1.txt");
            Scanner Reader = new Scanner(fil);
            while (Reader.hasNextLine()) {
                String data = Reader.nextLine();
                System.out.println(data); 
            }
            //Write a file
            //FileWriter fw = new FileWriter(f);
            //fw.append("mohanraj is a Trainer");
            
            //Delete a file
           // fil.deleteOnExit();
            Reader.close();
        } catch (IOException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
    }
}
