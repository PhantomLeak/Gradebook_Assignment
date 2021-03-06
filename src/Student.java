import java.io.*;
import java.lang.reflect.Array;
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

    private String gradeTypeDecision;
    private String classDecision;
    private String moreGradeInputs;
    private String continueGradeInputs;

    public List<String> grades = new ArrayList<>();
    private ArrayList<Integer> storeGradeInfo = new ArrayList<>();
    private ArrayList<Integer> weightedGradesStored = new ArrayList<>();
    private ArrayList<Integer> gradeWeightesStored = new ArrayList<>();

    public Student() {}
    int quizWeight = 0;
    int examWeight = 0;
    int projectWeight = 0;
    int homeworkWeight = 0;

    int gradeTotal;

    //Get class choice and grade type from the student.
    protected void newGradeInputs() throws IOException {
        Scanner scan = new Scanner(System.in);
        File txtFile = new File("src/StudentGrades.txt");

        if(!txtFile.exists()) {
            txtFile.createNewFile();  //Creates a new text file if there is not one
        }

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(txtFile, true));  // writes the users input into StudentGrades.txt
            if (storeGradeInfo.isEmpty()) {
                getclassOption();
            }else {
                if (moreGradeInputs.equals("yes")){
                    typeOfGrade();
                }else{
                    System.out.print("Would you like to enter a grade for a new class?: [yes or no]: ");
                    continueGradeInputs = scan.nextLine();
                    if (continueGradeInputs.equals("yes")){
                        getclassOption();
                    }else{
                        System.out.println("Thank you for using the gradebook");
                        System.exit(0);
                    }
                }
            }

            // Writes the users grades in StudentGrades.txt and loops over if the user wants to enter in more than one grade.
            System.out.print("Would you like to enter any other grades for this class? [yes or no]: ");
            moreGradeInputs = scan.nextLine().toLowerCase();
            switch (moreGradeInputs) {
                case "yes":
                    writer.write("You got a " +  gradeTotal + "[" + letterGradeTotal()  + "]" + " on your " + gradeTypeDecision + " in " + classDecision + "\n");
                    writer.close();
                    newGradeInputs();
                    break;
                case "no":
                    writer.write("You got a " +  gradeTotal + "[" + letterGradeTotal()  + "]" + " on your " + gradeTypeDecision + "\n");
                    writer.write("Your total for " + classDecision + " is " + finalGrade() + "\n");
                    writer.close();
                    newGradeInputs();
                    break;
                default:
                    System.out.println("Invalid response, please try again.");
            }
        } catch(IOException writerEX){
            System.out.println("Cannot Write to file: permission issue: " + writerEX.getStackTrace());
        }
    }

    private String getclassOption() throws IOException {
        ArrayList<String> classOptions = new ArrayList<>();
        classOptions.add("blaster deflection");
        classOptions.add("lightsaber combat");
        classOptions.add("force control");
        Scanner scan = new Scanner(System.in);
            System.out.print("What class are you entering grades for?: " + classOptions + ": "); //Student choosing what class they want to take...
            classDecision = scan.nextLine().toLowerCase();
            // if statement to ensure the class being inputted is a valid class option.
            if (classOptions.contains(classDecision)) {
                typeOfGrade();
            } else {
                System.out.println("Please enter a valid class");
                newGradeInputs();
            }
            return classDecision;
        }


    // Method that gets the type of grade the user wants to enter and sends them to the proper method to enter the desired grade.
    private String typeOfGrade() throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.print("What types of grades are you entering? [Exam, Quiz, Project, Homework]: ");
        gradeTypeDecision = scan.nextLine().toLowerCase();

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
        return gradeTypeDecision;
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
        System.out.print("Please enter the grade you got on your project: ");
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
        System.out.print("Please enter the grade you got on your homework: ");
       String homeworkGradeInput = scan.next();

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
        System.out.print("Please enter the grade you got on your quiz: ");
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
            System.out.println("Cannot load grades, no past grades entered.");
        } else{
            System.out.println("Your old grades are : ");
            for(String i : grades) {
                System.out.println(i);
            }
        }
    }
}