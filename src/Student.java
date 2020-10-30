import java.util.ArrayList;
import java.util.Scanner;

public class Student {

    public static String studentInfo(){
        Scanner scan = new Scanner(System.in);
        System.out.print("Please enter your name: ");
        String studentName = scan.nextLine();

        ArrayList<String> classOptions = new ArrayList<>();
        classOptions.add("Lightsaber training");
        classOptions.add("Blaster deflection training");
        classOptions.add("Force control training");

        System.out.println(classOptions);
        System.out.print("Please select your class: ");  //Gives student the choices they can choose from...
        String classChoice = scan.nextLine();

        System.out.print("What type of Grade are you inputting for " + classChoice + " (Exam, Quiz, Homework, Project) : ");
        String typeOfGrade = scan.nextLine();

        switch (typeOfGrade) {
            case "Exam":
                examCalc();
                break;
            case "Quiz":
                System.out.print("Please enter the grade you got for the Quiz: ");
                //Insert calculations for Quiz
                break;
            case "Project":
                System.out.print("Please enter the grade you got for the Project: ");
                //Insert calculations for Project
            case "Homework":
                System.out.print("Please enter the grade you got for the Homework: ");
                //Insert calculations for Homework
            default:
                System.out.println("There was an error...Let's try that again");
                studentInfo();
        }

        return studentName;  //Using as a holder so the method can simply work instead of giving me an error...
    }


    protected static int examCalc() {
        Scanner scan = new Scanner(System.in);
        int Exam = 100;
        System.out.print("Please enter the grade you got for the exam: ");
        int examGrade = scan.nextInt();

        switch (examGrade){
            case 90-100:
                System.out.println("You got an A!");
        }
        return examGrade;
    }
}