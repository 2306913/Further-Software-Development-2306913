package org.BM2306913;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;

// Utility class to allow JTextFields to prevent non-double input.

class IntegerDocumentFilter extends DocumentFilter {

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
       if(isValidInteger(fb.getDocument().getText(0,fb.getDocument().getLength()) + string)){
           int value = Integer.parseInt(fb.getDocument().getText(0,fb.getDocument().getLength()) + string);
           if(value <= 100) { // Checks if value is greater than 100
               super.insertString(fb, offset, string, attr); // allows insertion of string
           }
           else{
               Toolkit.getDefaultToolkit().beep(); // Beeps to inform the user that this value is greater than 100 and therefore invalid
           }
       }
       else{
           Toolkit.getDefaultToolkit().beep(); // Beeps to inform the user that this was an invalid input
       }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String string, AttributeSet attr) throws BadLocationException {
        // Simulate the text after the replacement
        String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
        StringBuilder newText = new StringBuilder(currentText);
        newText.replace(offset, offset + length, string); // Simulate the replacement

        // Validate the new text
        if (isValidInteger(newText.toString())) {
            double value = Integer.parseInt(newText.toString());
            if(value <= 100) { // Checks if value is greater than 100
                super.replace(fb, offset, length, string, attr); // Replace string
            }
            else{
                Toolkit.getDefaultToolkit().beep(); // Beeps to inform the user that this value is greater than 100 and therefore invalid
            }

        } else {
            Toolkit.getDefaultToolkit().beep(); // Beeps to inform the user of invalid input
        }
    }

    @Override
    public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
        super.remove(fb, offset, length);
    }

    // Helper method to check if a particular input is a valid Integer
    private boolean isValidInteger(String text) {
        if(text.isEmpty()){
            return true; // Allow the field to be empty
        }
        try{
            Integer.parseInt(text); // Checks to see if "text" is a valid Integer by attempting to parse it as one
            return true;
        }
        catch(NumberFormatException e){
            return false;
        }
    }

    }
