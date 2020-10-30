import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Gradebook {

    public static void main(String [] args) throws IOException {
        Student student = new Student();

        getStudentData(student);
    }

    public static int getStudentData(Student student) throws IOException {
        Scanner scan = new Scanner(System.in);

        FileReader reader = new FileReader("src/StudentGrades.txt");
        FileWriter writer = new FileWriter("src/StudentGrades.txt");

        ArrayList<String> classOptions = new ArrayList<>();
        classOptions.add("Blaster deflection");
        classOptions.add("Lightsaber combat");
        classOptions.add("Force control");

        System.out.print("What is your name?: ");
        String studentName = scan.nextLine();

        System.out.print("So " + studentName + " are you entering new grades or viewing old ones? [Enter new or old]: ");
        String viewChoice = scan.nextLine();

        if (viewChoice == "new") {
            try (BufferedWriter gradeWriter = new BufferedWriter(writer)) {

                System.out.print("So " + studentName + " What class are you entering grades for?: " + classOptions + ": "); //Student choosing what class they want to take...
                String classDecision = scan.nextLine();
                writer.write(classDecision);
            } catch (IOException fileError) {
                System.out.println("File Error: " + fileError.getStackTrace());
            }
            writer.close();
        } else {
            try (BufferedReader gradeReader = new BufferedReader(reader)) {
                String line;
                gradeReader.close();
            } catch (FileNotFoundException ex) {
                System.out.println("File does not exist: " + ex.getStackTrace());
            } catch (IOException ex) {
                System.out.println("Problem reading file: " + ex.getStackTrace());
            }

        }
        return 0;
    }
}