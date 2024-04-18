
    import java.util.HashSet;
import java.util.Set;

public class setask {
    public static void main(String[] args) {
        
        String text = "Java";
        
        // Split the text into words
        String[] words = text.split(" ");
        
        // Create a Set to store unique words
        Set<String> uniqueWords = new HashSet<>();
        
        // Add words to the Set
        for (String word : words) {
            uniqueWords.add(word);
        }
        
        System.out.println("Number of unique words: " + uniqueWords.size()+ ",");
    }
}


