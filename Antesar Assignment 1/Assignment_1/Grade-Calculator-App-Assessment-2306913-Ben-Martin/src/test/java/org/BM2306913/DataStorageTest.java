package org.BM2306913;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DataStorageTest {
    private DataStorage dataStorage;
    private StudentData studentData;

    @BeforeEach
    public void setUp() {
        // Set up a new DataStorage instance and create sample StudentData
        dataStorage = new DataStorage();

        studentData = new StudentData(12345, new String[][][]{{{"Grade A"}}}, 5, false);
    }

    @Test
    public void testSetAllStudents() {
        // Create an array of StudentData
        StudentData[] students = new StudentData[]{studentData};

        // Set the students and verify
        dataStorage.setAllStudents(students);
        assertArrayEquals(students, DataStorage.getAllStudents());
    }

    @Test
    public void testSetAllStudentsWithNull() {
        // Test null input, expecting IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> {
            dataStorage.setAllStudents(null);
        });
    }

    @Test
    public void testGetLevel5ModuleCodes() {
        // Verify level 5 module codes
        String[] expectedLevel5Modules = new String[]{"COM5001", "COM5002", "COM5003", "COM5004", "COM5005", "COM5006", "COM5007", "COM5008", "COM5009"};
        assertArrayEquals(expectedLevel5Modules, DataStorage.getLevel5ModuleCodes());
    }

    @Test
    public void testGetLevel6ModuleCodes() {
        // Verify level 6 module codes
        String[] expectedLevel6Modules = new String[]{"COM6001", "COM6002", "COM6003", "COM6004", "COM6005", "COM6006", "COM6007", "COM6008", "COM6009"};
        assertArrayEquals(expectedLevel6Modules, DataStorage.getLevel6ModuleCodes());
    }

    @Test
    public void testSetAllModuleCodes() {
        // Update the module codes
        String[][] newModuleCodes = {
                {"testCOM5001", "testCOM5002", "testCOM5003"},
                {"testCOM6001", "testCOM6002", "testCOM6003"}
        };

        dataStorage.setAllModuleCodes(newModuleCodes);

        // Verify that the module codes have been updated
        assertArrayEquals(newModuleCodes[0], DataStorage.getLevel5ModuleCodes());
        assertArrayEquals(newModuleCodes[1], DataStorage.getLevel6ModuleCodes());
    }

}