import java.util.Arrays;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        Student[] students = new Student[5];
        System.out.println(Arrays.toString(students));

        Student tamim = new Student();
        System.out.println(tamim);
        System.out.println(tamim.name);

    }
}
    class Student{
        String name;
        int rollNo;
        float marks;

    }
