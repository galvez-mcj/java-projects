/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.galvez.schoolmanagement;

/**
 *
 * @author Christina
 */
public class Course {
    private final int courseID;
    private String courseName;
    private int courseCapacity;
    private int courseEnrollees;
    private int courseInstructor;

    public Course(int courseID, String courseName, int courseCapacity, int courseEnrollees, int courseInstructor) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.courseCapacity = courseCapacity;
        this.courseEnrollees = courseEnrollees;
        this.courseInstructor = courseInstructor;
    }
    
    public int getCourseID() {
        return courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCourseCapacity() {
        return courseCapacity;
    }

    public void setCourseCapacity(int courseCapacity) {
        this.courseCapacity = courseCapacity;
    }

    public int getCourseEnrollees() {
        return courseEnrollees;
    }

    public void setCourseEnrollees(int courseEnrollees) {
        this.courseEnrollees = courseEnrollees;
    }

    public int getCourseInstructor() {
        return courseInstructor;
    }

    public void setCourseInstructor(int courseInstructor) {
        this.courseInstructor = courseInstructor;
    }
    
    
}
