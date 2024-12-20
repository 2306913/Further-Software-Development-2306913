package org.BM2306913;

public class StudentData {
    private int studentID = 000000;
    private String[][][] studentGrades = new String[2][3][10];
    private int moduleNum = 5;
    private boolean isDirectEntry;

    public StudentData(int studentID, String[][][] studentGrades, int moduleNum, boolean isDirectEntry) {
        this.studentID = studentID;
        this.studentGrades = studentGrades;
        this.moduleNum = moduleNum;
        this.isDirectEntry = isDirectEntry;
    }

    public int getStudentID() {
        return this.studentID;
    }
    public String[][][] getStudentGrades() {
        return this.studentGrades;
    }
    public int getModuleNum() {
        return this.moduleNum;
    }
    public boolean getDirectEntry() {
        return this.isDirectEntry;
    }


    public void setStudentGrades(String[][][] studentGrades) {
        this.studentGrades = studentGrades;
    }
    public void setModuleNum(int moduleNum) {
        this.moduleNum = moduleNum;
    }
    public void setDirectEntry(boolean isDirectEntry) {
        this.isDirectEntry = isDirectEntry;
    }

}
