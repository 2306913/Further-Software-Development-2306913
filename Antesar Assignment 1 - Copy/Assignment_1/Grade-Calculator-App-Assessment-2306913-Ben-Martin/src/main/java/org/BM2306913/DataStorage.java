package org.BM2306913;

public class DataStorage {
    // Class used to store data for this program.

    private static StudentData[] allStudents;
    //private String[] allModuleCodes = new String[]{"COM5001", "COM5002", "COM5003", "COM5004", "COM5005", "COM5006", "COM5007", "COM5008", "COM5009"};
    private static String[] level5ModuleCodes = new String[]{"COM5001", "COM5002", "COM5003", "COM5004", "COM5005", "COM5006", "COM5007", "COM5008", "COM5009"};
    private static String[] level6ModuleCodes = new String[]{"COM6001", "COM6002", "COM6003", "COM6004", "COM6005", "COM6006", "COM6007", "COM6008", "COM6009"};

    public void setAllStudents(StudentData[] allStudents) {
        // Checks if the array is not null
        if(allStudents == null) {
            throw new IllegalArgumentException("allStudents cannot be null");
        }
        this.allStudents = allStudents;
    }

    public void printAllStudentData() {

        for(int i = 0; i < allStudents.length; i++) {
            System.out.println(allStudents[i].getStudentID());
            System.out.println(allStudents[i].getModuleNum());
            System.out.println(allStudents[i].getDirectEntry());
        }
    }

    public void setAllModuleCodes(String[][] allModuleCodes) {
        level5ModuleCodes = allModuleCodes[0];
        level6ModuleCodes = allModuleCodes[1];
    }

    public static StudentData[] getAllStudents() {
        return allStudents;
    }

    public static String[] getLevel5ModuleCodes(){
        return level5ModuleCodes;
    }
    public static String[] getLevel6ModuleCodes(){
        return level6ModuleCodes;
    }


}


