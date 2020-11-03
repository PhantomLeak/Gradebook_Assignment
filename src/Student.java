import java.io.*;
import java.util.*;

public class Student {
    private int exam = 100;
    private int quizzes = 225;
    private int homework = 250;
    private int project = 125;
    private int total = 700;
    public List<String> grades = new ArrayList<>();
    public ArrayList<Integer> storeGradeInfo = new ArrayList<>();
    public ArrayList<Integer> weightedGradesStored = new ArrayList<>();
    public ArrayList<Integer> gradeWeightesStored = new ArrayList<>();

    public Student() {}
    int quizWeight = 0;
    int examWeight = 0;
    int projectWeight = 0;
    int homeworkWeight = 0;

    int gradeTotal;

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
                    break;
                case "project":
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
                    break;
                case "homework":
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
                    break;
                case "quiz":
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
                    break;
                default:
                    System.out.println("Error, please try again");
                    newGradeInputs();
                    break;
            }

            //Equation to add up all inputted grades.
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

            int finalGrades = (sumOfGradeTimesWeight/sumOfWeights);

            System.out.print("Would you like to enter any other grades? [yes or no]: ");
            String moreGradeInputs = scan.nextLine().toLowerCase();
            if (moreGradeInputs.equals("yes")) {  //create loop that lets user input more than one grade
                writer.write("You got a " + "[" +  gradeTotal + "]" + " on your " + gradeTypeDecision + " in " + classDecision + "\n");
                writer.close();
                newGradeInputs();
            } else {
                writer.write("You got a " + "[" +  gradeTotal + "]"+ " on your " + gradeTypeDecision + " in your " + classDecision + "\n");
                writer.write("Your overall grade for the class is a " + finalGrades + "\n");
                writer.close();
            }

        } catch(IOException writerEX){
            System.out.println("Cannot Write to file: permission issue: " + writerEX.getStackTrace());

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
