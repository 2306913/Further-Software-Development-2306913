package org.example;

public class ValidatePassword {
    public static boolean isValid(String password){
        if (password.length() >= 5 && password.length() <= 10)
            return true;
        else
            return false;
    }
}
