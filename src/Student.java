import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Student {
    private int exam = 100;
    private int quizzes = 225;
    private int homework = 250;
    private int project = 125;
    private int total = 700;
    public List<String> grades = new ArrayList<>();

    public Student() {}


    protected void newGradeInputs() throws IOException {
        Scanner scan = new Scanner(System.in);
        ArrayList<String> classOptions = new ArrayList<>();
        classOptions.add("Blaster deflection");
        classOptions.add("Lightsaber combat");
        classOptions.add("Force control");
        File txtFile = new File("src/StudentGrades.txt");

        if(!txtFile.exists()) {
            txtFile.createNewFile();
        }

        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(txtFile, true));

            System.out.print( "What class are you entering grades for?: " + classOptions + ": "); //Student choosing what class they want to take...
            String classDecision = scan.nextLine();

            System.out.print("What types of grades are you entering? [Exam, Quiz, Project, Homework]: ");
            String gradeTypeDecision = scan.nextLine();

            System.out.print("Please enter the grade you received for your " + gradeTypeDecision + " : ");

            writer.write(classDecision + " " + "[" + gradeTypeDecision + "]" + "\n");
            writer.close();

            System.out.print("Would you like to enter any other grades? [yes or no]: ");
            String moreGradeInputs = scan.nextLine().toLowerCase();
            if (moreGradeInputs.equals("yes")){  //create loop that lets user input more than one grade
                newGradeInputs();
            }else {
                writer.close();
            }

        } catch(IOException writterEX){
            System.out.println("Cannot Write to file: permission issue: " + writterEX.getStackTrace());

        }
    }

    protected void showOldGrades() {
        try {
            FileReader reader = new FileReader("src/StudentGrades.txt"); //Makes files readable
            BufferedReader bReader = new BufferedReader(reader);  //Reads it one line at a time

            String line = "";
            while ((line = bReader.readLine()) != null) {
                grades.add(line.toLowerCase());
            }

            reader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File does not exist: " + ex.getStackTrace());
        } catch (IOException ex) {
            System.out.println("Problem reading file: " + ex.getStackTrace());
        }
    }

    protected void printOldGrades() {
        if(grades.size() < 1) {
            System.out.println("Grades not loaded");
        } else{
            System.out.println("Your old grades are : ");
            for(String i : grades) {
                System.out.println(i);
            }
        }
    }






}
