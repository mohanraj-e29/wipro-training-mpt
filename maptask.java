
 import java.util.HashMap;
import java.util.Map;

public class maptask {
    public static void main(String[] args) {
        String text = "Java is a programming language";
        
        // Split the text into words
        String[] words = text.split(" ");
        
        // Create a Map to store word frequencies
        Map<String, Integer> wordFreqMap = new HashMap<>();
        
        // Count the frequency of each word
        for (String word : words) {
            // Convert the word to lowercase to ensure case-insensitive comparison
            String lowerCaseWord = word.toLowerCase();
            
            // Increment the frequency count for the word in the map
            wordFreqMap.put(lowerCaseWord, wordFreqMap.getOrDefault(lowerCaseWord, 0) + 1);
        }
        
        // Print the word frequencies
        System.out.println("Word frequencies:");
        for (Map.Entry<String, Integer> entry : wordFreqMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}

 