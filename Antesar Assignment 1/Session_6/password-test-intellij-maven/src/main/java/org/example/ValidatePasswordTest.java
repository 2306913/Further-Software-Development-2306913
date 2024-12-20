package org.example;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.fail;

class ValidatePasswordTest {

//    @Test
//    public void test(){
//        fail("Not yet implemented");
//    }

    @Test
    void testPasswordLength() {
        //ValidatePassword vp = new ValidatePassword();
        assertEquals(true, ValidatePassword.isValid("Abc12"));
    }
}
