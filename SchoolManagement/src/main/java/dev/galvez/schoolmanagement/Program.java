/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.galvez.schoolmanagement;

/**
 *
 * @author Christina
 */
public class Program {
    private final int programID;
    private String programName;
    private String programSection;

    public Program(int programID, String programName, String programSection) {
        this.programID = programID;
        this.programName = programName;
        this.programSection = programSection;
    }

    public int getProgramID() {
        return programID;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getProgramSection() {
        return programSection;
    }

    public void setProgramSection(String programSection) {
        this.programSection = programSection;
    }
    
    
}
