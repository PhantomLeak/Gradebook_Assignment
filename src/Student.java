import java.io.*;
import java.util.*;

public class Student {
    private int exam = 100;
    private int quizzes = 225;
    private int homework = 250;
    private int project = 125;
    private int total = 700;
    public List<String> grades = new ArrayList<>();
    public List<Integer> storeInfo = new ArrayList<>();

    public Student() {}
    int examGrade;
    int projectGrade;
    int homeworkGrade;
    int quizGrade;

    int examTotal;
    int quizTotal;
    int projectTotal;
    int homeworkTotal;
    double finalGrade;

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

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(txtFile, true));

            System.out.print("What class are you entering grades for?: " + classOptions + ": "); //Student choosing what class they want to take...
            String classDecision = scan.nextLine();

            System.out.print("What types of grades are you entering? [Exam, Quiz, Project, Homework]: ");
            String gradeTypeDecision = scan.nextLine().toLowerCase();

            switch (gradeTypeDecision) {
                case "exam":
                    examCalculation();
                    break;
                case "quiz":
                    quizCalculation();
                    break;
                case "project":
                    projectCalculation();
                    break;
                case "homework":
                    homeworkCalculation();
                    break;
                default:
                    System.out.println("Error, please try again");
                    newGradeInputs();
                    break;
            }

            //Equation to add up all inputted grades.
            int sumOfGrades = 0;
            for (int i=0; i<storeInfo.size(); i++) {
                sumOfGrades += storeInfo.get(i);

            }

            System.out.print("Would you like to enter any other grades? [yes or no]: ");
            String moreGradeInputs = scan.nextLine().toLowerCase();
            if (moreGradeInputs.equals("yes")) {  //create loop that lets user input more than one grade
                writer.write("You got a " + storeInfo + " on your " + gradeTypeDecision + " in your " + classDecision + "\n");
                writer.close();
                newGradeInputs();
            } else {
                writer.write("You got a " + storeInfo + " on your " + gradeTypeDecision + " in your " + classDecision + "\n");
                writer.write("Your overall grade for the class is a " + sumOfGrades);
                writer.close();
            }

        } catch(IOException writerEX){
            System.out.println("Cannot Write to file: permission issue: " + writerEX.getStackTrace());

        }
    }

    protected double examCalculation(){
        Scanner scan = new Scanner(System.in);
        System.out.print("Please enter the grade you received: ");
        String gradeExamInput = scan.nextLine();

        if (gradeExamInput.length() > 0){
            examGrade = Integer.parseInt(gradeExamInput);
            examTotal = ((examGrade*30)/30);
            storeInfo.add(examTotal);
        }else{
            examTotal = 0;
        }

        return examTotal;
    }

    protected double quizCalculation(){
        Scanner scan = new Scanner(System.in);
        System.out.print("Please enter the grade you received: ");
        String gradeQuizInput = scan.nextLine();
        quizGrade = Integer.parseInt(gradeQuizInput);
        if (quizGrade > 0){
            quizTotal = ((quizGrade*20)/20);
            storeInfo.add(quizTotal);
        }else{
            quizTotal = 0;
        }

        return quizTotal;
    }

    protected double homeworkCalculation(){
        Scanner scan = new Scanner(System.in);
        System.out.print("Please enter the grade you received: ");
        String gradehomeworkInput = scan.nextLine();

        if (gradehomeworkInput.length() > 0) {
            homeworkGrade = Integer.parseInt(gradehomeworkInput);
            homeworkTotal = ((homeworkGrade*25)/25);
            storeInfo.add(homeworkTotal);
        }else{
            homeworkTotal = 0;
        }

        return homeworkTotal;
    }

    protected double projectCalculation(){
        Scanner scan = new Scanner(System.in);
        System.out.print("Please enter the grade you received: ");
        String gradeprojectInput = scan.nextLine();

        if (gradeprojectInput.length() > 0) {
            projectGrade = Integer.parseInt(gradeprojectInput);
            projectTotal = ((projectGrade*25)/25);
            storeInfo.add(homeworkTotal);
        }else{
            projectTotal = 0;
        }

        return projectTotal;
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
