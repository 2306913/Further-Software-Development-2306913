package org.BM2306913;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GradeCalcTest {
    private GradeCalc gradeCalc;

    @BeforeEach
    void setUp() {
        gradeCalc = new GradeCalc();
    }
    @Test
    void testCalculateNotDirectEntryValidCredits(){
        // Tests non-direct entry final results using valid credit totals and a passing grade
        String[][][] panelData = new String[2][3][3];
        // Mock valid panelData
        panelData[0][0] = new String[]{"COM5001", "COM5002", "COM5003"}; // Valid level 5 module codes
        panelData[0][1] = new String[]{"40", "40", "40"}; // Valid credit total level 5
        panelData[0][2] = new String[]{"40", "40", "40"}; // Valid passing mark level 5
        panelData[1][0] = new String[]{"COM6001", "COM6002", "COM6003"}; // Valid level 6 module codes
        panelData[1][1] = new String[]{"40", "40", "40"}; // Valid credit total level 6
        panelData[1][2] = new String[]{"40", "40", "40"}; // Valid passing mark level 6

        boolean isDirectEntry = false;
        int numOfModules = 3;

        String[][] result = gradeCalc.calculateGrade(panelData, isDirectEntry, numOfModules);

        // Assuming calculated marks are not n/a and pass the requirements
        assertNotNull(result);
        assertNotEquals("N/A",result[4][1]);
    }

    @Test
    void testCalculateNotDirectEntryInvalidCredits(){
        // Tests nondirect entry final results using invalid credit totals and a passing grade
        String[][][] panelData = new String[2][3][3];
        // Mock valid panelData
        panelData[0][0] = new String[]{"COM5001", "COM5002", "COM5003"}; // Valid level 5 module codes
        panelData[0][1] = new String[]{"40", "5", "40"}; // Valid credit total level 5
        panelData[0][2] = new String[]{"40", "40", "40"}; // Valid passing mark level 5
        panelData[1][0] = new String[]{"COM6001", "COM6002", "COM6003"}; // Valid level 6 module codes
        panelData[1][1] = new String[]{"40", "5", "40"}; // Valid credit total level 6
        panelData[1][2] = new String[]{"40", "40", "40"}; // Valid passing mark level 6

        boolean isDirectEntry = false;
        int numOfModules = 3;

        String[][] result = gradeCalc.calculateGrade(panelData, isDirectEntry, numOfModules);

        // Assuming calculated marks are not n/a and pass the requirements
        assertNotNull(result);
        assertNotEquals("N/A",result[4][1]);
    }

    @Test
    void testCalculateNotDirectEntryPassingGrade(){
        // Tests Non-direct entry final results using valid credit totals and a passing grade
        String[][][] panelData = new String[2][3][3];
        // Mock valid panelData
        panelData[0][0] = new String[]{"COM5001", "COM5002", "COM5003"}; // Valid level 5 module codes
        panelData[0][1] = new String[]{"40", "40", "40"}; // Valid credit total level 5
        panelData[0][2] = new String[]{"40", "40", "40"}; // Valid passing mark level 5
        panelData[1][0] = new String[]{"COM6001", "COM6002", "COM6003"}; // Valid level 6 module codes
        panelData[1][1] = new String[]{"40", "40", "40"}; // Valid credit total level 6
        panelData[1][2] = new String[]{"40", "40", "40"}; // Valid passing mark level 6

        boolean isDirectEntry = true;
        int numOfModules = 3;

        String[][] result = gradeCalc.calculateGrade(panelData, isDirectEntry, numOfModules);

        // Assuming calculated marks are not n/a and pass the requirements
        assertNotNull(result);
        assertNotEquals("FAIL",result[4][1]);
    }

    @Test
    void testCalculateNotDirectEntryFailingGrade(){
        // Tests non direct entry final results using valid credit totals and a failing grade
        String[][][] panelData = new String[2][3][3];
        // Mock valid panelData
        panelData[0][0] = new String[]{"COM5001", "COM5002", "COM5003"}; // Valid level 5 module codes
        panelData[0][1] = new String[]{"40", "40", "40"}; // Valid credit total level 5
        panelData[0][2] = new String[]{"40", "39", "40"}; // invalid passing mark level 5
        panelData[1][0] = new String[]{"COM6001", "COM6002", "COM6003"}; // Valid level 6 module codes
        panelData[1][1] = new String[]{"40", "40", "40"}; // Valid credit total level 6
        panelData[1][2] = new String[]{"40", "39", "40"}; // invalid passing mark level 6

        boolean isDirectEntry = true;
        int numOfModules = 3;

        String[][] result = gradeCalc.calculateGrade(panelData, isDirectEntry, numOfModules);

        // Assuming calculated marks are not n/a and pass the requirements
        assertNotNull(result);
        //
        assertNotEquals("FAIL",result[4][1]);
    }

    @Test
    void testCalculateDirectEntryValidCredits(){
        // Tests direct entry final results using valid credit totals and a passing grade
        String[][][] panelData = new String[2][3][3];
        // Mock valid panelData
        panelData[0][0] = new String[]{"COM5001", "COM5002", "COM5003"}; // Valid level 5 module codes
        panelData[0][1] = new String[]{"40", "40", "40"}; // Valid credit total level 5
        panelData[0][2] = new String[]{"40", "40", "40"}; // Valid passing mark level 5
        panelData[1][0] = new String[]{"COM6001", "COM6002", "COM6003"}; // Valid level 6 module codes
        panelData[1][1] = new String[]{"40", "40", "40"}; // Valid credit total level 6
        panelData[1][2] = new String[]{"40", "40", "40"}; // Valid passing mark level 6

        boolean isDirectEntry = true;
        int numOfModules = 3;

        String[][] result = gradeCalc.calculateGrade(panelData, isDirectEntry, numOfModules);

        // Assuming calculated marks are not n/a and pass the requirements
        assertNotNull(result);
        assertNotEquals("N/A",result[4][1]);
    }

    @Test
    void testCalculateDirectEntryInvalidCredits(){
        // Tests direct entry final results using invalid credit totals and a passing grade
        String[][][] panelData = new String[2][3][3];
        // Mock valid panelData
        panelData[0][0] = new String[]{"COM5001", "COM5002", "COM5003"}; // Valid level 5 module codes
        panelData[0][1] = new String[]{"40", "5", "40"}; // Valid credit total level 5
        panelData[0][2] = new String[]{"40", "40", "40"}; // Valid passing mark level 5
        panelData[1][0] = new String[]{"COM6001", "COM6002", "COM6003"}; // Valid level 6 module codes
        panelData[1][1] = new String[]{"40", "5", "40"}; // Valid credit total level 6
        panelData[1][2] = new String[]{"40", "40", "40"}; // Valid passing mark level 6

        boolean isDirectEntry = true;
        int numOfModules = 3;

        String[][] result = gradeCalc.calculateGrade(panelData, isDirectEntry, numOfModules);

        // Assuming calculated marks are not n/a and pass the requirements
        assertNotNull(result);
        assertNotEquals("N/A",result[4][1]);
    }

    @Test
    void testCalculateDirectEntryPassingGrade(){
        // Tests direct entry final results using valid credit totals and a passing grade
        String[][][] panelData = new String[2][3][3];
        // Mock valid panelData
        panelData[0][0] = new String[]{"COM5001", "COM5002", "COM5003"}; // Valid level 5 module codes
        panelData[0][1] = new String[]{"40", "40", "40"}; // Valid credit total level 5
        panelData[0][2] = new String[]{"40", "40", "40"}; // Valid passing mark level 5
        panelData[1][0] = new String[]{"COM6001", "COM6002", "COM6003"}; // Valid level 6 module codes
        panelData[1][1] = new String[]{"40", "40", "40"}; // Valid credit total level 6
        panelData[1][2] = new String[]{"40", "40", "40"}; // Valid passing mark level 6

        boolean isDirectEntry = true;
        int numOfModules = 3;

        String[][] result = gradeCalc.calculateGrade(panelData, isDirectEntry, numOfModules);

        // Assuming calculated marks are not n/a and pass the requirements
        assertNotNull(result);
        assertNotEquals("FAIL",result[4][1]);
    }

    @Test
    void testCalculateDirectEntryFailingGrade(){
        // Tests direct entry final results using valid credit totals and a failing grade
        String[][][] panelData = new String[2][3][3];
        // Mock valid panelData
        panelData[0][0] = new String[]{"COM5001", "COM5002", "COM5003"}; // Valid level 5 module codes
        panelData[0][1] = new String[]{"40", "40", "40"}; // Valid credit total level 5
        panelData[0][2] = new String[]{"40", "39", "40"}; // invalid passing mark level 5
        panelData[1][0] = new String[]{"COM6001", "COM6002", "COM6003"}; // Valid level 6 module codes
        panelData[1][1] = new String[]{"40", "40", "40"}; // Valid credit total level 6
        panelData[1][2] = new String[]{"40", "39", "40"}; // invalid passing mark level 6

        boolean isDirectEntry = true;
        int numOfModules = 3;

        String[][] result = gradeCalc.calculateGrade(panelData, isDirectEntry, numOfModules);

        // Assuming calculated marks are not n/a and pass the requirements
        assertNotNull(result);
        //
        assertNotEquals("FAIL",result[4][1]);
    }

    @Test
    void testCalculateDirectEntryNonIntegerGrade(){
        // Tests direct entry final results using valid credit totals and a grade containing a non-integer character
        String[][][] panelData = new String[2][3][3];
        // Mock valid panelData
        panelData[0][0] = new String[]{"COM5001", "COM5002", "COM5003"}; // Valid level 5 module codes
        panelData[0][1] = new String[]{"40", "40", "40"}; // Valid credit total level 5
        panelData[0][2] = new String[]{"40", "3f9", "40"}; // Valid passing mark level 5
        panelData[1][0] = new String[]{"COM6001", "COM6002", "COM6003"}; // Valid level 6 module codes
        panelData[1][1] = new String[]{"40", "40", "40"}; // Valid credit total level 6
        panelData[1][2] = new String[]{"40", "3f9", "40"}; // Valid passing mark level 6

        boolean isDirectEntry = true;
        int numOfModules = 3;

        String[][] result = gradeCalc.calculateGrade(panelData, isDirectEntry, numOfModules);

        // Assuming calculated marks are not n/a and pass the requirements
        assertNotNull(result);
        //
        assertNotEquals("N/A",result[4][1]);
    }

    @Test
    void testCalculateDirectEntryInvalidModuleCode(){
        // Tests direct entry final results using valid credit totals and a grade containing a non-integer character
        String[][][] panelData = new String[2][3][3];
        // Mock valid panelData
        panelData[0][0] = new String[]{"COM5001", "COM5002", "COM5003"}; // Valid level 5 module codes
        panelData[0][1] = new String[]{"40", "40", "40"}; // Valid credit total level 5
        panelData[0][2] = new String[]{"40", "40", "40"}; // Valid passing mark level 5
        panelData[1][0] = new String[]{"COM600", "COM6002", "COM6003"}; // Valid level 6 module codes
        panelData[1][1] = new String[]{"40", "40", "40"}; // Valid credit total level 6
        panelData[1][2] = new String[]{"40", "40", "40"}; // Valid passing mark level 6

        boolean isDirectEntry = true;
        int numOfModules = 3;

        String[][] result = gradeCalc.calculateGrade(panelData, isDirectEntry, numOfModules);

        // Assuming calculated marks are not n/a and pass the requirements
        assertNotNull(result);
        //
        assertNotEquals("N/A",result[4][1]);
    }

    @Test
    void testCalculateNotDirectEntryInvalidModuleCode(){
        // Tests direct entry final results using valid credit totals and a grade containing a non-integer character
        String[][][] panelData = new String[2][3][3];
        // Mock valid panelData
        panelData[0][0] = new String[]{"COM500", "COM5002", "COM5003"}; // Valid level 5 module codes
        panelData[0][1] = new String[]{"40", "40", "40"}; // Valid credit total level 5
        panelData[0][2] = new String[]{"40", "40", "40"}; // Valid passing mark level 5
        panelData[1][0] = new String[]{"COM6001", "COM6002", "COM6003"}; // Valid level 6 module codes
        panelData[1][1] = new String[]{"40", "40", "40"}; // Valid credit total level 6
        panelData[1][2] = new String[]{"40", "40", "40"}; // Valid passing mark level 6

        boolean isDirectEntry = false;
        int numOfModules = 3;

        String[][] result = gradeCalc.calculateGrade(panelData, isDirectEntry, numOfModules);

        // Assuming calculated marks are not n/a and pass the requirements
        assertNotNull(result);
        //
        assertNotEquals("N/A",result[4][1]);
    }


}