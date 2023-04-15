package student;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private final String firstName;
    private final String lastName;
    private final int year;
    private int ID;
    private List<String> courseList;
    private double balance;

    public Student(String lastName, String firstName, int year) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.year = year;
    }

    public int generateID() {
        this.ID = (int) ((Math.random() * 10000) + (this.year * 10000));
        return this.ID;
    }

    public void setCourses(String courseList) {
        // perform string manipulation
        String modified = courseList.replaceAll("\\s", "");
        String[] courseInitials = modified.toUpperCase().split("");

        List<String> courses = new ArrayList<>();

        for (String course: courseInitials) {
            switch (course){
                case "H":
                    courses.add("History");
                    break;
                case "M":
                    courses.add("Mathematics");
                    break;
                case "E":
                    courses.add("English");
                    break;
                case "B":
                    courses.add("Biology");
                    break;
                case "C":
                    courses.add("Computer Science");
                    break;
            }
        }
        this.courseList = courses;
    }



    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public int getYear() {
        return this.year;
    }

    public int getID() {
        return this.ID;
    }

    public double getBalance() {
        return this.balance = this.courseList.size() * 600;
    }

    public String getCourseList() {
        String list = "";
        for (int i = 0; i < courseList.size(); i++) {
            if (courseList.size() == 1 || i == courseList.size() - 1) {
                list = list + courseList.get(i);
            } else {
               list = list + courseList.get(i) + ", ";
            }
        }
        return list;
    }

}
