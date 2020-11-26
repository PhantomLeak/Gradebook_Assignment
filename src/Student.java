import java.io.*;
import java.util.*;


/*
This class will ask the user for their grade inputs and calculate their grades
 */
public class Student {
    //Super method holding variables for use
    private int exam = 100;
    private int quizzes = 225;
    private int homework = 250;
    private int project = 125;
    private int total = 700;
    public List<String> grades = new ArrayList<>();
    public ArrayList<Integer> storeGradeInfo = new ArrayList<>();
    public ArrayList<Integer> weightedGradesStored = new ArrayList<>();
    public ArrayList<Integer> gradeWeightesStored = new ArrayList<>();
    public ArrayList<Integer> BlasterDeflectionGrade = new ArrayList<>();
    public ArrayList<Integer> LightsaberCombatGrade = new ArrayList<>();
    public ArrayList<Integer> ForceControlGrade = new ArrayList<>();

    public Student() {}
    int quizWeight = 0;
    int examWeight = 0;
    int projectWeight = 0;
    int homeworkWeight = 0;

    int gradeTotal;

    //Get class choice and grade type from the student.
    protected void newGradeInputs() throws IOException {
        Scanner scan = new Scanner(System.in);
        ArrayList<String> classOptions = new ArrayList<>();
        classOptions.add("Blaster deflection");
        classOptions.add("Lightsaber combat");
        classOptions.add("Force control");
        File txtFile = new File("src/StudentGrades.txt");

        if(!txtFile.exists()) {
            txtFile.createNewFile();  //Creates a new text file if there is not one
        }

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(txtFile, true));  // writes the users input into StudentGrades.txt

            System.out.print("What class are you entering grades for?: " + classOptions + ": "); //Student choosing what class they want to take...
            String classDecision = scan.nextLine();

            System.out.print("What types of grades are you entering? [Exam, Quiz, Project, Homework]: ");
            String gradeTypeDecision = scan.nextLine().toLowerCase();

            // Switch statement that takes in what the grade type is and stores calculation into array... should probably do calculations in separate method though

            switch (gradeTypeDecision) {
                case "exam":
                    examGrade();
                    break;
                case "project":
                    projectGrade();
                    break;
                case "homework":
                    homeworkGrade();
                    break;
                case "quiz":
                    quizGrade();
                    break;
                default:
                    System.out.println("Error, please try again");
                    newGradeInputs();
                    break;
            }

            // Writes the users grades in StudentGrades.txt and loops over if the user wants to enter in more than one grade.
            System.out.print("Would you like to enter any other grades? [yes or no]: ");
            String moreGradeInputs = scan.nextLine().toLowerCase();
            if (moreGradeInputs.equals("yes")) {  //create loop that lets user input more than one grade
                writer.write("You got a " +  gradeTotal + "[" + letterGradeTotal()  + "]" + " on your " + gradeTypeDecision + " in " + classDecision + "\n");
                writer.close();
                newGradeInputs();
            } else {
                writer.write("You got a " +  gradeTotal + "[" + letterGradeTotal() + "]" + " on your " + gradeTypeDecision + " in your " + classDecision + "\n");
                writer.write("Your overall grade is " + finalGrade() + "[" + letterGradeTotal() + "]" + "\n");
                writer.close();
            }

        } catch(IOException writerEX){
            System.out.println("Cannot Write to file: permission issue: " + writerEX.getStackTrace());

        }
    }

    //Calculates the exam grade
    protected String examGrade() {
        Scanner scan = new Scanner(System.in);
        examWeight = 30;
        gradeWeightesStored.add(examWeight);
        System.out.print("Please enter the grade you got on your exam: ");
        String examGradeInput = scan.nextLine();

        if (examGradeInput.length() > 0) {
            gradeTotal = Integer.parseInt(examGradeInput);
            storeGradeInfo.add(gradeTotal);
            weightedGradesStored.add(gradeTotal * 30);
        }else{
            gradeTotal = 0;
        }
        return "Please enter a valid grade";
    }

    //Calculates the grade for projects
    protected String projectGrade() {
        Scanner scan = new Scanner(System.in);
        projectWeight = 25;
        gradeWeightesStored.add(projectWeight);
        System.out.print("Please enter the grade you got on your exam: ");
        String projectGradeInput = scan.nextLine();

        if (projectGradeInput.length() > 0) {
            gradeTotal = Integer.parseInt(projectGradeInput);
            storeGradeInfo.add(gradeTotal);
            weightedGradesStored.add(gradeTotal * 25);
        }else{
            gradeTotal = 0;
        }
        return "Please enter a valid grade";
    }

    // Calculates homework grade
    protected String homeworkGrade() {
        Scanner scan = new Scanner(System.in);
        homeworkWeight = 25;
        gradeWeightesStored.add(homeworkWeight);
        System.out.print("Please enter the grade you got on your exam: ");
        String homeworkGradeInput = scan.nextLine();

        if (homeworkGradeInput.length() > 0) {
            gradeTotal = Integer.parseInt(homeworkGradeInput);
            storeGradeInfo.add(gradeTotal);
            weightedGradesStored.add(gradeTotal * 25);
        }else{
            gradeTotal = 0;
        }
        return "Please enter a valid grade";
    }

    // Calculates the quiz grade
    protected String quizGrade() {
        Scanner scan = new Scanner(System.in);
        quizWeight = 20;
        gradeWeightesStored.add(quizWeight);
        System.out.print("Please enter the grade you got on your exam: ");
        String quizGradeInput = scan.nextLine();

        if (quizGradeInput.length() > 0) {
            gradeTotal = Integer.parseInt(quizGradeInput);
            storeGradeInfo.add(gradeTotal);
            weightedGradesStored.add(gradeTotal * 20);
        }else{
            gradeTotal = 0;
        }
        return "Please enter valid grade";
    }

    // Calculates the final overall grade for the student
    protected int finalGrade() {
        //Equations to add up all inputted grades.
        int sumOfGrades = 0;
        int sumOfWeights = 0;
        int sumOfGradeTimesWeight = 0;

        for (int i=0; i<storeGradeInfo.size(); i++) {
            sumOfGrades += storeGradeInfo.get(i);
        }
        for (int i=0; i<gradeWeightesStored.size(); i++) {
            sumOfWeights += gradeWeightesStored.get(i);
        }
        for (int i=0; i<weightedGradesStored.size(); i++) {
            sumOfGradeTimesWeight += weightedGradesStored.get(i);
        }
        // used to show the final grade overall.
        int finalGrades = (sumOfGradeTimesWeight/sumOfWeights);

        return finalGrades;
    }

    public String letterGradeTotal() {
        if (gradeTotal <= 100 && gradeTotal >= 90) {
            return "A";
        } else if (gradeTotal <= 89 && gradeTotal >= 80) {
            return "B";
        } else if (gradeTotal <= 79 && gradeTotal >= 70) {
            return "C";
        } else if (gradeTotal <= 69 && gradeTotal >= 60) {
            return "D";
        } else if ((gradeTotal <= 59 && gradeTotal >= 0)) {
            return "F";
        }
        return "You entered an invalid grade.";
    }

    protected void writeGrades() {

    }

    // uses reader to read StudentGrades.txt
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

    // Either states there are not grades to print out or uses reader to print out grades for student
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
