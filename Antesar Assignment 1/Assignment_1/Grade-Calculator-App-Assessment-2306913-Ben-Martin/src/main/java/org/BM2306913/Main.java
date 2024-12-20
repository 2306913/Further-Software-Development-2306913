package org.BM2306913;

public class Main {
    static GradeCalcCardUI calcUI;// = new GradeCalcCardUI();
    //static GradeCalc calc = new GradeCalc();
    static DataStorage dataStorage = new DataStorage();
    public static void main(String[] args) {
        dataStorage.setAllStudents(JSONHandler.loadAllStudentData()); // Sets up "Database" entry for student data
        dataStorage.setAllModuleCodes(JSONHandler.loadAllModuleCodes()); // Sets up "Database" entry for module codes
        calcUI = new GradeCalcCardUI(); // Creates and runs UI
    }
}