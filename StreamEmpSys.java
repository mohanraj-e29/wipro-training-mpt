import java.util.ArrayList;
import java.util.List;

public class StreamEmpSys {
    public static void main(String[] args) {
        emp e1 = new emp("Aaryan", "Trainer", 100);
        emp e2 = new emp("balu", "Developer", 1000);
        emp e3 = new emp("mohan", "fresher", 900);
        emp e4 = new emp("zain", "testtig", 2000);
        emp e5 = new emp("sathish", "manager", 9000);
        List<emp> list = new ArrayList<>();
        list.add(e1);
        list.add(e2);
        list.add(e3);
        list.add(e4);
        list.add(e5);

        // TODO: Filter emp where salary > 100
        // list.stream().filter(e -> e.role.equals("Trainer")).forEach(System.out::println);
        
        // TODO: sort them by salary

        list.stream().sorted((e, ee) -> {
            if(e.salary < ee.salary) {
                return -1; // swap
            }
            return 0; // don't swap
        }).forEach(System.out::println);
           System.out.println("-------------------------------");
           //sort by role
        list.stream()
        .sorted((f,ff )-> f.role.compareTo(ff.role) )
        .forEach(System.out::println);
        //sort by name
        System.out.println("-------------------------------");
        list.stream()
        .sorted((s,ss )-> s.name.compareTo(ss.name) )
        .forEach(System.out::println);

    }
}

// Model
class emp {
    String name;
    String role;
    double salary;

    public emp(String name, String role, double salary) {
        this.name = name;
        this.role = role;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Emp: {" + name + ", " + role + ", " + salary + "}";
    }
}
