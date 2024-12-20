package org.BM2306913;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Utility class to allow JTextFields to mandate correct module code input.

class ModuleCodeDocumentFilter extends DocumentFilter {
    private String[] validModuleCodes; // Module codes that are valid for this JTextField

    public ModuleCodeDocumentFilter(String[] validModuleCodes) {
        this.validModuleCodes = validModuleCodes;
    }
    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
       if(string != null){
           string = string.toUpperCase();
           String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
           String newText = currentText.substring(0, offset) + string + currentText.substring(offset);
           //handleAutofill(fb, offset, newText, attr);

           if (isValid(newText)){
               super.insertString(fb, offset, string, attr);
           }
           else {
               System.out.println("Invalid module code: " + newText);
           }
       }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String string, AttributeSet attr) throws BadLocationException {
        if(string != null){
            string = string.toUpperCase();
            String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
            String newText = currentText.substring(0, offset) + string + currentText.substring(offset + length);
            //handleAutofill(fb, offset, newText, attr);

            if (isValid(newText)){
                super.replace(fb, offset, length, string, attr);
            }
            else {
                System.out.println("Invalid module code: " + newText);
            }
        }
    }

    @Override
    public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
        super.remove(fb, offset, length);
    }

    private boolean isValid(String input){
        if(input.isEmpty()){
            return true; // Always allow an empty input
        }
        // Allow input that matches the start of any valid entry in validModuleCodes
        return Arrays.stream(validModuleCodes).anyMatch(code -> code.toLowerCase().startsWith(input.toLowerCase()));
    }
    private void handleAutofill(FilterBypass fb, int offset, String newText, AttributeSet attr) throws BadLocationException {
        // Helper function to handle autofilling of JTextField based on valid module codes (As referenced in the "vaidModuleCodes" String[])
        List<String> matches = new ArrayList<>();
        for(String entry : validModuleCodes){ // Checks currently typed string against all entries in validModuleCodes and adds them to "matches" ArrayList
            if (entry.toLowerCase().startsWith(newText.toLowerCase())){
                matches.add(entry);
            }
        }

        // Only autofill if there is exactly one match
        if(matches.size() == 1){
            String match = matches.getFirst(); // Gets first string in "matches" (Though there will only be one)
            fb.replace(0, fb.getDocument().getLength(), match, attr);
            fb.replace(offset + newText.length(), match.length() - newText.length(), "", attr); // Highlights autofill
        }
        else if(matches.size() > 1){
            System.out.println("Multiple matches found. Keep typing to refine input.");
        }
        else {
            System.out.println("No valid match found.");
        }


//        for(String entry : validModuleCodes){
//            if(entry.toLowerCase().startsWith(newText.toLowerCase())){
//                fb.replace(0, fb.getDocument().getLength(), entry, attr);
//                fb.replace(offset + newText.length(), entry.length() - newText.length(), "", attr); // Highlights autofill
//                return;
//            }
//        }
//        // If there is no match, allow the input but display an error
//        System.out.println("Module code not valid: " + newText);
    }

    }
