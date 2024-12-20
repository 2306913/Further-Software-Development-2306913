package org.BM2306913;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class JSONHandlerTest {

    static String studentDataFilepath = "studentDataJSON.json";
    static String moduleCodeFilepath = "moduleCodeJSON.json";

    private JSONHandler jsonHandler;

    @BeforeEach
    public void setUp() {
        jsonHandler = new JSONHandler();
    }

    @Test
    public void testLoadAllStudentData() { // Checks if studentDataJSON.json can be loaded. moduleCodeJSON.json has the same method and is not tested alongside
        // Ensure the file exists and can be read
        StudentData[] studentData = jsonHandler.loadAllStudentData();

        // Ensure data is not a null value
        assertNotNull(studentData);

        // Check to ensure data is not of 0 length (i.e, a failed load)
        assertTrue(studentData.length > 0, "No student data found in the file.");
    }

}