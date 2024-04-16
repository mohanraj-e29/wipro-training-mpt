import java.util.List;
import java.util.ArrayList;

public class arraylistex {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        numbers.add(5);

        System.out.println(numbers.size());

        System.out.println("Original list: " + numbers);
        arraylistex(numbers);
    }

    private static void arraylistex(List<Integer> numbers) {
        for (int i = 1; i < numbers.size(); i+=1) {
            numbers.remove(i); // Remove every second element
        }
        System.out.println("List after removing every second element: " + numbers);
    }
}

/*
 * import java.util.List;
import java.util.ArrayList;

public class RemoveEverySecondElement {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        numbers.add(5);

        System.out.println("Original list: " + numbers);
        removeEverySecondElement(numbers);
    }

    public static void removeEverySecondElement(List<?> list) {
        for (int i = 1; i < list.size(); i++) {
            list.remove(i); // Remove every second element
        }
        System.out.println("List after removing every second element: " + list);
    }
}

 */