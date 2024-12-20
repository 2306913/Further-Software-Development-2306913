//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;


public class Main {
    public static void main(String[] args) {

        Frame frame = new Frame("Graphical_Calculator");

        frame.setLayout(new BorderLayout());

        // Creates the text field to display values, and a second for results
        Panel displayPanel = new Panel();
        displayPanel.setLayout(new GridLayout(2, 1));
        TextField textField = new TextField();
        displayPanel.add(textField);
        Label resultLabel = new Label("Result: ");
        displayPanel.add(resultLabel);
        //textField.setBounds(50, 100, 200, 30);
        frame.add(displayPanel, BorderLayout.NORTH);

        // Create Number panel
        Panel numPanel = new Panel();
        numPanel.setLayout(new GridLayout(4, 3));
        // Adds numbers 0 through 9 to button array
        Button[] numButtons = new Button[10];
        for (int i = 0; i < numButtons.length; i++) {

            // Create number buttons
            numButtons[i] = new Button(String.valueOf(i));
            //Button button = new Button("" + i);
            numPanel.add(numButtons[i]);

            // Add action listener for a given button
            int index = i; // Current value of i
            numButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Appends value of button to text field
                    String currentText = textField.getText();
                    textField.setText(currentText + numButtons[index].getLabel());
                }
            });
        }
        frame.add(numPanel, BorderLayout.CENTER);

        // Create operation panel
        Panel opPanel = new Panel();
        opPanel.setLayout(new GridLayout(2, 3));

        Button[] opButtons = new Button[4];
        Button addButton = new Button("+");
        opButtons[0] = addButton;
        Button subButton = new Button("-");
        opButtons[1] = subButton;
        Button divButton = new Button("/");
        opButtons[2] = divButton;
        Button multButton = new Button("*");
        opButtons[3] = multButton;



        for (int i = 0; i < opButtons.length; i++) {

            opPanel.add(opButtons[i]);
            // Add action listener for a given button
            int index = i; // Current value of i
            opButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Appends value of button to text field
                    String currentText = textField.getText();
                    textField.setText(currentText + opButtons[index].getLabel());
                }
            });
        }

        // Clear text box button
        Button clearButton = new Button("Clear");
        opPanel.add(clearButton);
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // clears text box of data
                //String currentText = textField.getText();
                textField.setText("");
            }
        });

        // Calculate sum button
        Button calcButton = new Button("Calculate");
        opPanel.add(calcButton);
        calcButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Attempts to solve the equation in the text field

                try {
                    String equation = textField.getText();

                    // Attempt to evaluate expression using ScriptEngine
                    //ScriptEngineManager manager = new ScriptEngineManager();
                    //ScriptEngine engine = manager.getEngineByName("JavaScript");
                    //Object result = engine.eval(equation);
                    Object result = eval(equation);

                    resultLabel.setText("Result: " + result);


                } catch (ScriptException ex){
                    resultLabel.setText("Error: Invalid Expression");
                }

            }
        });


        frame.add(opPanel, BorderLayout.SOUTH);



        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null); // Centers the window
        //frame.setLayout(null);
        //frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose(); // Releases native screen resources
            }
        });

        frame.setVisible(true);
        frame.setResizable(false);


    }
}