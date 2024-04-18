

import java.util.*;

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}

public class lambdatask {
    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();
        people.add(new Person("Alice", 30));
        people.add(new Person("Bob", 25));
        people.add(new Person("Charlie", 35));

        // Sorting by age using a lambda expression
        Collections.sort(people, (p1, p2) -> Integer.compare(p1.getAge(), p2.getAge()));

        // Printing the sorted list
        for (Person person : people) {
            System.out.println(person.getName() + ": " + person.getAge());
        }
    }
}
