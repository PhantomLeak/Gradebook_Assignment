import java.util.ArrayList;
import java.util.Scanner;

public class Gradebook {

    public static void main(String [] args) {
        Student student = new Student();

        getStudentData(student);
    }

    public static int getStudentData(Student student) {
        Scanner scan = new Scanner(System.in);

        System.out.print("What is your name?: ");
        String studentName = scan.nextLine();

        ArrayList<String> classOptions = new ArrayList<>();
        classOptions.add("Blaster deflection");
        classOptions.add("Lightsaber combat");
        classOptions.add("Force control");

        System.out.print("So " + studentName + " What class are you entering grades for?: " + classOptions + ": "); //Student choosing what class they want to take...
        String classDecision = scan.nextLine();

        System.out.print(classDecision + " A very fun class, what type of grade are you entering? [Exam, Homework, Quiz, Project]: ");
        String gradeType = scan.nextLine();

        return 0;
    }
}