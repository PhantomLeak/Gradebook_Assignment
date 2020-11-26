import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Gradebook {

    public static void main(String [] args) throws IOException {
        Student student = new Student();
        Scanner scan = new Scanner(System.in);

        System.out.print("What is your name?: ");  //Getting student name
        String studentName = scan.nextLine();
        // Finds what the user intends to do, enter new grades or view old ones.
        System.out.print("So " + studentName + " are you entering new grades or viewing old ones? [Enter new or old]: ");  //determine is student is looking at old grades or entering new ones.
        String viewChoice = scan.nextLine().toLowerCase();

        // switch statement which takes in user input based on if they are viewing old grades or entering new ones
        switch (viewChoice) {
            case "new":
                student.newGradeInputs();
                break;
            case "old":
                student.showOldGrades();
                student.printOldGrades();
                break;
            default:
                System.out.println("I don't understand");
        }


    }


}