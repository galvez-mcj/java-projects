package student;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    private static List<Integer> studentIDs = new ArrayList<>();
    private static List<Student> studentList = new ArrayList<>();

    public static void main(String[] args) {
        mainMenu();
    }

    public static void mainMenu() {
        System.out.println("***************************************");
        System.out.println("Hi there! What do you want to do today?");
        System.out.println("[1] Create new student/s");
        System.out.println("[2] View student information");
        System.out.println("[3] Exit");
        System.out.print("Enter your choice: ");
        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();
        System.out.println("***************************************");

        switch (choice) {
            case 1: creationMenu();
            case 2: viewStudents();
            case 3: System.exit(0);
        }
    }

    public static void creationMenu() {
        System.out.print("Enter number of students to be created: ");
        Scanner input = new Scanner(System.in);
        int numStudents = input.nextInt();
        System.out.println("---------------------------------------");

        for (int i = 0; i < numStudents; i++) {
            System.out.printf("Enter Details for Student#%d \n", i+1);
            System.out.print("Enter Student's Last Name: ");
            Scanner s_lastname = new Scanner(System.in);
            String lastName = s_lastname.nextLine();
            System.out.print("Enter Student's First Name: ");
            Scanner s_firstname = new Scanner(System.in);
            String firstName = s_firstname.nextLine();
            System.out.print("Enter Student's Year Level: ");
            Scanner s_year = new Scanner(System.in);
            int year = s_year.nextInt();

            // Create the student object and generate ID
            Student newStudent = new Student(lastName, firstName, year);

            // Check if ID is unique
            int studentID = newStudent.generateID();
            while(studentIDs.contains(studentID)) {
                studentID = newStudent.generateID();
            }
            studentIDs.add(studentID);

            System.out.printf("%s's student ID is: %d", lastName, studentID);
            System.out.println();
            System.out.println("---------------------------------------");

            // enroll to courses
            System.out.println("Choose what courses to enroll!");
            System.out.println("[H]istory 101");
            System.out.println("[M]athematics 101");
            System.out.println("[E]nglish 101");
            System.out.println("[B]iology 101");
            System.out.println("[C]omputer Science 101");
            System.out.println("---------------------------------------");
            System.out.print("Type the letter/s of your choice: ");
            Scanner s_courses = new Scanner(System.in);
            String courseList = s_courses.nextLine();
            System.out.println("---------------------------------------");

            newStudent.setCourses(courseList);
            studentList.add(newStudent);
        }
        mainMenu();
    }

    public static void viewStudents() {

        System.out.println("Student Record for Today");
        System.out.println(String.format("%-20s", "Last Name") + "|" +
                            String.format("%-20s", "First Name") + "|" +
                            String.format("%-20s", "Student Number") + "|" +
                            String.format("%-40s", "Courses Enrolled") + "|" +
                            String.format("%-10s", "Balance"));

        for (Student student:
             studentList) {
            System.out.println(String.format("%-20s", student.getLastName()) + "|" +
                        String.format("%-20s", student.getFirstName()) + "|" +
                        String.format("%-20s", student.getID()) + "|" +
                        String.format("%-40s", student.getCourseList()) + "|" +
                        String.format("%-10s", student.getBalance()));
        }
        System.out.println("");
        mainMenu();

    }
}
