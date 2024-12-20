package org.BM2306913;

import java.util.Arrays;

public class GradeCalc {

    static final double minThirdValue = 39.50; // Minimum mark required for a Third
    static final double minTwoTwoValue = 49.50; // Minimum mark required for a 2-2
    static final double minTwoOneValue = 59.50; // Minimum mark required for a 2-1
    static final double minFirstValue = 69.50; // // Minimum mark required for a First

    static final int desiredCreditTotal = 120; // Required credit value for any given level
    static final int minimumPassingMark = 40; // Minimal passing mark

    public static String[][] calculateGrade(String[][][] panelData, boolean isDirectEntry, int numOfModules){
        // Calculates the grade achieved by the student based on calculation methods A, B, C, D, and then also determines their final grade.
        String[][] allCalculatedMarks = new String[5][];

        // Initialisation of dummy data for each mark method
        String[] markA = {"N/A", "N/A"};
        String[] markB = {"N/A", "N/A"};
        String[] markC = {"N/A", "N/A"};
        String[] markD = {"N/A", "N/A"};
        String[] markF = {"N/A", "N/A"};


        if(!isDirectEntry) { // Checks if the course is Direct Entry
            // Ensure that credit totals equal 120 for both levels, if not, returns "N/A" in all fields as calculations are not done.
            if (!creditTotalsMatch(panelData[0][1], panelData[1][1], numOfModules) || !allGradesInteger(panelData[0][2], panelData[1][2], numOfModules) || !allModulesValid(panelData[0][0], panelData[1][0], numOfModules)) {
                allCalculatedMarks[0] = markA;
                allCalculatedMarks[1] = markB;
                allCalculatedMarks[2] = markC;
                allCalculatedMarks[3] = markD;
                allCalculatedMarks[4] = markF;
                return allCalculatedMarks;
            }

            markA = calculateGradeMethodA(panelData, numOfModules); // Mark derived using "Method A"
            markB = calculateGradeMethodB(panelData, numOfModules); // Mark derived using "Method B"
            markD = calculateGradeMethodD(panelData, markA[0], markB[0], numOfModules); // Mark derived using "Method D" - Overloaded method

            // Ensure that all marks are passing values (40 +)
            if(!allGradesPassing(panelData[0][2], panelData[1][2], numOfModules)){
                allCalculatedMarks[0] = new String[]{markA[0], "FAIL"};
                allCalculatedMarks[1] = new String[]{markB[0], "FAIL"};
                allCalculatedMarks[2] = new String[]{markC[0], "FAIL"};
                allCalculatedMarks[3] = new String[]{markD[0], "FAIL"};
                allCalculatedMarks[4] = new String[]{markF[0], "FAIL"};
                //System.out.println("Result is" + allCalculatedMarks[4][0]);
                return allCalculatedMarks;
            }
        }

        else{
            // Ensure that credit total equals 120 for level 6, if not, returns "N/A" in all fields as calculations are not done.
            if (!creditTotalsMatch(panelData[1][1], numOfModules) || !allGradesInteger(panelData[1][2], numOfModules) || !allModulesValid(panelData[1][0], numOfModules)) {
                allCalculatedMarks[0] = markA;
                allCalculatedMarks[1] = markB;
                allCalculatedMarks[2] = markC;
                allCalculatedMarks[3] = markD;
                allCalculatedMarks[4] = markF;
                return allCalculatedMarks;
            }
            markC = calculateGradeMethodC(panelData, numOfModules); // Mark derived using "Method C"
            markD =  calculateGradeMethodD(panelData, markC[0], numOfModules); // Mark derived using "Method D" - Overloaded method

            // Ensure that all marks are passing values (40 +)
            if(!allGradesPassing(panelData[1][2], numOfModules)){
                allCalculatedMarks[0] = new String[]{markA[0], "FAIL"};
                allCalculatedMarks[1] = new String[]{markB[0], "FAIL"};
                allCalculatedMarks[2] = new String[]{markC[0], "FAIL"};
                allCalculatedMarks[3] = new String[]{markD[0], "FAIL"};
                allCalculatedMarks[4] = new String[]{markF[0], "FAIL"};
                return allCalculatedMarks;
            }

        }

        String[][] combinedMarks = new String[][]{markA, markB, markC, markD};
        markF = calculateFinalGrade(combinedMarks);

        allCalculatedMarks[0] = markA;
        allCalculatedMarks[1] = markB;
        allCalculatedMarks[2] = markC;
        allCalculatedMarks[3] = markD;
        allCalculatedMarks[4] = markF;
        return allCalculatedMarks;
    }

    private static boolean creditTotalsMatch(String[] level6Credits, int numOfModules){
        // Overload of "creditTotalsMatch" that only checks level 6 credits
        int totalLevel6Credits = 0;

        for(int i = 0; i < numOfModules; i++){
            try { // Attempts to add level6Credits[i] to the running total of level 6 credits
                totalLevel6Credits += Integer.parseInt(level6Credits[i]);
            }
            catch(NumberFormatException e){ // Catches number format errors, presumes credit totals do not match
                return false;
            }
        }
        System.out.println(totalLevel6Credits);
        return totalLevel6Credits == desiredCreditTotal;
    }
    private static boolean creditTotalsMatch(String[] level5Credits, String[] level6Credits, int numOfModules){

        // Ensures that the total credits present in both level 5 and 6 total to 120 each.
        int totalLevel5Credits = 0;
        int totalLevel6Credits = 0;

        for(int i = 0; i < numOfModules; i++){
            try { // Attempts to add level5Credits[i] and level6Credits[i] to their respective variable totals
                totalLevel5Credits += Integer.parseInt(level5Credits[i]);
                totalLevel6Credits += Integer.parseInt(level6Credits[i]);
            }
            catch(NumberFormatException e){ // Catches number format errors, presumes credit totals do not match
                return false;
            }
        }
        System.out.println(totalLevel5Credits);
        System.out.println(totalLevel6Credits);
        return totalLevel5Credits == desiredCreditTotal && totalLevel6Credits == desiredCreditTotal;

    }

    private static boolean allModulesValid(String[] level6Modules, int numOfModules){
        // Overload - Ensures all input level 6 module codes correspond to a valid module code
        String[] validLevel6Modules = DataStorage.getLevel6ModuleCodes();

        for(int i = 0; i < numOfModules; i++){
            if(!Arrays.asList(validLevel6Modules).contains(level6Modules[i])){
                return false;
            }
        }

        return true;
    }
    private static boolean allModulesValid(String[] level5Modules, String[] level6Modules, int numOfModules){
        // Overload - Ensures all input level 5 or 6 module codes correspond to a valid module code
        String[] validLevel5Modules = DataStorage.getLevel5ModuleCodes();
        String[] validLevel6Modules = DataStorage.getLevel6ModuleCodes();

        for(int i = 0; i < numOfModules; i++){
            if(!Arrays.asList(validLevel6Modules).contains(level6Modules[i]) || !Arrays.asList(validLevel5Modules).contains(level5Modules[i])){
                return false;
            }
        }
        return true;
    }

    private static boolean allGradesInteger(String[] level6Marks, int numOfModules){

        // Overload of "allGradesInteger" that checks only level 6 marks
        // Iterates through level 6 marks and ensures that each mark is an integer
        for(int i = 0; i < numOfModules; i++){
            try{
                Integer.parseInt(level6Marks[i]);
            }
            catch(NumberFormatException e){ // Catches number format errors
                return false;
            }
        }
        return true;

    }
    private static boolean allGradesInteger(String[] level5Marks, String[] level6Marks, int numOfModules){
        // Iterates through level 5 and level 6 marks and ensures that each mark is a valid integer
        for(int i = 0; i < numOfModules; i++){
            try{
                Integer.parseInt(level5Marks[i]);
                Integer.parseInt(level6Marks[i]);
            }
            catch(NumberFormatException e){ // Catches number format errors, presumes marks are invalid
                return false;
            }
        }
        return true;
    }

    private static boolean allGradesPassing(String[] level6Marks, int numOfModules){

        // Overload of "allGradesPassing" that checks only level 6 marks
        // Iterates through level 6 marks and ensures that each mark is equal to or greater than 40
        for(int i = 0; i < numOfModules; i++){
            try{
                if(Double.parseDouble(level6Marks[i]) < minimumPassingMark){
                    return false;
                }
            }
            catch(NumberFormatException e){ // Catches number format errors, presumes marks are invalid
                return false;
            }
        }
        return true;

    }
    private static boolean allGradesPassing(String[] level5Marks, String[] level6Marks, int numOfModules){
        // Iterates through level 5 and level 6 marks and ensures that each mark is equal to or greater than 40
        for(int i = 0; i < numOfModules; i++){
            try{
                if(Double.parseDouble(level5Marks[i]) < minimumPassingMark || Double.parseDouble(level6Marks[i]) < minimumPassingMark){
                    return false;
                }
            }
            catch(NumberFormatException e){ // Catches number format errors, presumes marks are invalid
                return false;
            }
        }
        return true;

    }

    private static String[] calculateGradeMethodA(String[][][] modulesCreditsMarks, int numOfModules){

        // Calculates the student's final grade based on "Method A"
        // Method A: The average mark of all module marks achieved at Level 5 and Level 6, (L6 average + L5 average) ÷ 2 = overall mark

        String[] level5Credits = modulesCreditsMarks[0][1]; // Extracts the String[] of credit values for level 5
        String[] level5Marks = modulesCreditsMarks[0][2]; // Extracts the String[] of marks values for level 5

        String[] level6Credits = modulesCreditsMarks[1][1]; // Extracts the String[] of credit values for level 6
        String[] level6Marks = modulesCreditsMarks[1][2]; // Extracts the String[] of marks values for level 6

        double level5FinalMark = calculateLevelGrade(level5Credits, level5Marks, numOfModules);
        double level6FinalMark = calculateLevelGrade(level6Credits, level6Marks, numOfModules);
        double finalMark = 0; // Numerical mark
        String awardClass = "N/A"; // Award classification, Fail, Third, 2-2, 2-1, and First.

        if(level5FinalMark != 0 && level6FinalMark != 0) { // Ensures that a divide by zero error does not occur.
            finalMark = ((level5FinalMark + level6FinalMark) / 2);
        }

        awardClass = calculateAwardClass(finalMark);

        String[] grade = new String[2];
        grade[0] = String.valueOf(finalMark);
        grade[1] = awardClass;
        return grade;
    }
    private static String[] calculateGradeMethodB(String[][][] modulesCreditsMarks, int numOfModules){

        // Calculates the student's final grade based on "Method B"
        // Method B: The average mark of all module marks achieved at Level 5 and Level 6, weighted in the 2:1 favour of level 6 credits, (L5
        //average + L6 average + L6 average) ÷ 3 = Overall mark

        String[] level5Credits = modulesCreditsMarks[0][1]; // Extracts the String[] of credit values for level 5
        String[] level5Marks = modulesCreditsMarks[0][2]; // Extracts the String[] of marks values for level 5

        String[] level6Credits = modulesCreditsMarks[1][1]; // Extracts the String[] of credit values for level 6
        String[] level6Marks = modulesCreditsMarks[1][2]; // Extracts the String[] of marks values for level 6

        double level5FinalMark = calculateLevelGrade(level5Credits, level5Marks, numOfModules);
        double level6FinalMark = calculateLevelGrade(level6Credits, level6Marks, numOfModules);
        double finalMark = 0; // Numerical mark
        String awardClass = "N/A"; // Award classification, Fail, Third, 2-2, 2-1, and First.

        if(level5FinalMark != 0 && level6FinalMark != 0) { // Ensures that a divide by zero error does not occur.
            finalMark = ((level5FinalMark + level6FinalMark + level6FinalMark) / 3);
        }

        awardClass = calculateAwardClass(finalMark);

        String[] grade = new String[2];
        grade[0] = String.valueOf(finalMark);
        grade[1] = awardClass;
        return grade;
    }
    private static String[] calculateGradeMethodC(String[][][] modulesCreditsMarks, int numOfModules){

        // Calculates the student's final grade based on "Method C"
        // Method C: The average mark of all module marks achieved a Level 6

        String[] level6Credits = modulesCreditsMarks[1][1]; // Extracts the String[] of credit values for level 6
        String[] level6Marks = modulesCreditsMarks[1][2]; // Extracts the String[] of marks values for level 6

        //double level5FinalMark = calculateLevelGrade(level5Credits, level5Marks);
        double level6FinalMark = calculateLevelGrade(level6Credits, level6Marks, numOfModules);
        //double finalMark = 0; // Numerical mark
        String awardClass = "N/A"; // Award classification, Fail, Third, 2-2, 2-1, and First.

        awardClass = calculateAwardClass(level6FinalMark);

        String[] grade = new String[2];
        grade[0] = String.valueOf(level6FinalMark);
        grade[1] = awardClass;
        return grade;
    }
    private static String[] calculateGradeMethodD(String[][][] modulesCreditsMarks, String markAVal, String markBVal, int numOfModules){

        // Calculates the student's final grade based on "Method D" using both level 5 and 6
        // Method D: Mark Profiling.

        String[] level5Credits = modulesCreditsMarks[0][1]; // Extracts the String[] of credit values for level 5
        String[] level5Marks = modulesCreditsMarks[0][2]; // Extracts the String[] of marks values for level 5

        String[] level6Credits = modulesCreditsMarks[1][1]; // Extracts the String[] of credit values for level 6
        String[] level6Marks = modulesCreditsMarks[1][2]; // Extracts the String[] of marks values for level 6

        double[] level5AwardClassCreditTotals = awardClassCreditTotals(level5Credits, level5Marks, numOfModules);
        double[] level6AwardClassCreditTotals = awardClassCreditTotals(level6Credits, level6Marks, numOfModules);

        double[] combinedAwardClassCreditTotals = combineAwardClassCreditArrays(level5AwardClassCreditTotals, level6AwardClassCreditTotals);

        String awardClass = "N/A";

        if(combinedAwardClassCreditTotals[0] >= (combinedAwardClassCreditTotals[5] / 2)) {
            awardClass = "First";
        }
        else if(combinedAwardClassCreditTotals[1] >= (combinedAwardClassCreditTotals[5] / 2)){
            awardClass = "2-1";
        }
        else if(combinedAwardClassCreditTotals[2] >= (combinedAwardClassCreditTotals[5] / 2)){
            awardClass = "2-2";
        }
        else if(combinedAwardClassCreditTotals[3] >= (combinedAwardClassCreditTotals[5] / 2)){
            awardClass = "Third";
        }

        // Determines the larger grade value between that of method A and method B
        String largerGradeVal = String.valueOf(Math.max(Double.parseDouble(markAVal), Double.parseDouble(markBVal)));

        String[] grade = new String[2];
        grade[0] = largerGradeVal;
        grade[1] = awardClass;
        return grade;
    }
    private static String[] calculateGradeMethodD(String[][][] modulesCreditsMarks, String markCVal, int numOfModules){

        // Calculates the student's final grade based on "Method D" - Overloaded method using only level 6 grades
        // Method D: Mark Profiling.

        String[] level6Credits = modulesCreditsMarks[1][1]; // Extracts the String[] of credit values for level 6
        String[] level6Marks = modulesCreditsMarks[1][2]; // Extracts the String[] of marks values for level 6

        double[] level6AwardClassCreditTotals = awardClassCreditTotals(level6Credits, level6Marks, numOfModules);

        String awardClass = "N/A";

        if(level6AwardClassCreditTotals[0] >= (level6AwardClassCreditTotals[5] / 2)) {
            awardClass = "First";
        }
        else if(level6AwardClassCreditTotals[1] >= (level6AwardClassCreditTotals[5] / 2)){
            awardClass = "2-1";
        }
        else if(level6AwardClassCreditTotals[2] >= (level6AwardClassCreditTotals[5] / 2)){
            awardClass = "2-2";
        }
        else if(level6AwardClassCreditTotals[3] >= (level6AwardClassCreditTotals[5] / 2)){
            awardClass = "Third";
        }

        String[] grade = new String[2];
        grade[0] = markCVal;
        grade[1] = awardClass;
        //grade[0] = String.valueOf(level6FinalMark);
        //grade[1] = awardClass;
        return grade;
    }
    private static String[] calculateFinalGrade(String[][] allMarkMethods){
        String finalAwardClass = "N/A";
        Double finalAwardGradeTotal = 0.0;

        String[] allFinalAwardClasses = {allMarkMethods[0][1], allMarkMethods[1][1], allMarkMethods[2][1], allMarkMethods[3][1]};
        String[] allFinalMarks = {allMarkMethods[0][0], allMarkMethods[1][0], allMarkMethods[2][0], allMarkMethods[3][0]};
        // Determines the highest award class achieved
        if (Arrays.asList(allFinalAwardClasses).contains("First")) {
            finalAwardClass = "First";
        }
        else if (Arrays.asList(allFinalAwardClasses).contains("2-1")) {
            finalAwardClass = "2-1";
        }
        else if (Arrays.asList(allFinalAwardClasses).contains("2-2")) {
            finalAwardClass = "2-2";
        }
        else if (Arrays.asList(allFinalAwardClasses).contains("Third")) {
            finalAwardClass = "Third";
        }
        else if (Arrays.asList(allFinalAwardClasses).contains("FAIL")) {
            finalAwardClass = "FAIL";
        }
        else {
            finalAwardClass = "N/A";
        }

        // Determines the highest percentage grade achieved
        for(int i = 0; i < allFinalMarks.length; i++){
            try{ // Try/Catch ensures that only valid doubles are checked
                if(Double.parseDouble(allFinalMarks[i]) > finalAwardGradeTotal){ // If the currently checked grade is higher than the current highest grade, replace.
                    finalAwardGradeTotal = Double.parseDouble(allFinalMarks[i]);
                }
            }
            catch (NumberFormatException e){
                // Do nothing
            }
        }

        String[] finalGrade = new String[2];
        finalGrade[0] = String.valueOf(finalAwardGradeTotal);
        finalGrade[1] = finalAwardClass;
        return finalGrade;
    }

    private static double[] awardClassCreditTotals(String[] credits, String[] marks, int numOfModules){ // returns the total credit values of each award type

        double totalCredits = 0;
        double[] classCredits = new double[6]; // Double array containing credit totals for each grade class, First[0], 2-1[1], 2-2[2], Third[3], FAIL[4], Total[5]

        // Determines the award type of each module and adds the credit values of that module to the classCredits array.
        for (int i = 0; i < numOfModules; i++) {
//            totalCredits += Double.parseDouble(credits[i]);
            try{
                totalCredits += Double.parseDouble(credits[i]);
                switch (calculateAwardClass(Double.parseDouble(marks[i]))) {
                    case "N/A":
                        //classCredits[0] += Double.parseDouble(credits[i]);
                        break;
                    case "First":
                        classCredits[0] += Double.parseDouble(credits[i]);
                        break;
                    case "2-1":
                        classCredits[1] += Double.parseDouble(credits[i]);
                        break;
                    case "2-2":
                        classCredits[2] += Double.parseDouble(credits[i]);
                        break;
                    case "Third":
                        classCredits[3] += Double.parseDouble(credits[i]);
                        break;
                    case "FAIL":
                        classCredits[4] += Double.parseDouble(credits[i]);
                        break;
                }

            }
            catch(NumberFormatException e){
                // Some sort of error
            }
        }

        // Adds total credit count to end of classCredits array
        classCredits[5] = totalCredits;

        // Conversion of classCredits double[] to classCreditsStringArray String[]
        String[] classCreditsStringArray = new String[classCredits.length];
        for (int i = 0; i < classCredits.length; i++) {
            classCreditsStringArray[i] = String.valueOf(classCredits[i]);
        }
        return classCredits;
    }

    private static double[] combineAwardClassCreditArrays(double[] level5, double[] level6){
        // Helper function to combine level 5 and 6 credit totals
        double[] combinedArray = new double[6];
        for (int i = 0; i < level5.length; i++) {
            combinedArray[i] = level5[i] + level6[i] + level6[i];
        }
        return combinedArray;
    }

    private static double calculateLevelGrade(String[] credits, String[] marks, int numOfModules){ // Helper function used to calculate the total grade of a level

        double totalMarks = 0; // Running total of marks (Multiplied by associated credits) in this operation.
        double totalCredits = 0; // Running total of credit values for this operation.
        double finalMark = 0;

        for(int i = 0; i < numOfModules; i++){ // Iterates through Credits array and multiplies each credit array index by the associated marks array index.
            try {
                totalMarks += (Double.parseDouble(credits[i]) * Double.parseDouble(marks[i])); // Multiplies the marks of each module by the associated credit value
                totalCredits += Double.parseDouble(credits[i]); // Adds credits for each index to a running total
            }
            catch(NumberFormatException e){
                // Either totalMarks or totalCredits
            }
        }
        if(totalMarks != 0 && totalCredits != 0) { // Ensures that a divide by zero error does not occur.
            finalMark = totalMarks / totalCredits; // Calculates final mark based on sum of weighted averages / total credits
        }

        return finalMark;
    }

    private static String calculateAwardClass(double mark){ // Helper function to calculate the award class of a value
        String awardClass = "N/A"; // Dummy data

        if(mark < minThirdValue) {
            awardClass = "FAIL";
        }
        else if(mark < minTwoTwoValue) {
            awardClass = "Third";
        }
        else if(mark < minTwoOneValue) {
            awardClass = "2-2";
        }
        else if(mark < minFirstValue) {
            awardClass = "2-1";
        }
        else if(mark >= minFirstValue) {
            awardClass = "First";
        }
        else{ // Unexpected value handling
            awardClass = "Something went wrong";
        }

        return awardClass;
    }
}
