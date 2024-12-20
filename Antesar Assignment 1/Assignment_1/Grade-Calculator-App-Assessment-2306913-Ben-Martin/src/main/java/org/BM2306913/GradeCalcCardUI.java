package org.BM2306913;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;
import java.awt.*;

public class GradeCalcCardUI extends JFrame {
    private JPanel CalcUIMainPanel;

    private final int MAX_MODULES = 10;
    private int numOfModules = 5;
    private Boolean isDirectEntry = false;

    private JComboBox<String> studentDropdown;

    private GradePanel level5GradePanel;
    private GradePanel level6GradePanel;

    private CalculationPanel gradeCalculationPanel;

    private JCheckBox isDirectEntryCheck;
    private JSlider moduleNumSlider;


    public GradeCalcCardUI() {
        super("Grade Calc Card");
        initialiseFrame();
        addComponentsToFrame();
        studentSelected();
        setVisible(true);
    }

    private void initialiseFrame(){ // Initialises the core frame of the swing UI
        Dimension fixedWindowSize = new Dimension(1080, 600); // Fixed size for window
        setTitle("Grade Calculator"); // Sets title of frame/window.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Causes program to exit on frame/window closing.
        setLocationRelativeTo(null); // Centers the frame (Window)
        setSize(fixedWindowSize); // Sets frame to size determined by "fixedWindowSize"
        setResizable(false); // Prevents the frame from being resized.
    }

    private void addComponentsToFrame(){ //Creates and adds components to Swing UI frame
        JPanel mainPanel = createMainPanel();
        JPanel leftPanel = createLeftPanel();
        JPanel rightPanel = createRightPanel();
        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(rightPanel, BorderLayout.EAST);
        add(mainPanel);
    }

    private JPanel createMainPanel(){ // Creates "Main" panel that will contain both the left and right panels
        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(new TitledBorder("Leeds Trinity University"));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
        return mainPanel;
    }

    // --- "Left" panel relevant functions
    private JPanel createLeftPanel(){ // Contains student selection/creation/deletion, instructions, and grade calculations/final grade display
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setMinimumSize(new Dimension(400, 500));

        JPanel studentSelectionPanel = createStudentSelectPanel();
        JPanel instructionPanel = createInstructionPanel();
        gradeCalculationPanel = new CalculationPanel();

        leftPanel.add(studentSelectionPanel);
        leftPanel.add(instructionPanel);
        leftPanel.add(gradeCalculationPanel);

        return leftPanel;
    }

    private JPanel createStudentSelectPanel(){ //
        JPanel studentSelectPanel = new JPanel();
        studentSelectPanel.setLayout(new BorderLayout());
        studentSelectPanel.setBorder(new TitledBorder("Select Student"));
        studentSelectPanel.setPreferredSize(new Dimension(400, 75));
        studentSelectPanel.setMaximumSize(new Dimension(400, 75));

        // Student selection dropdown

        StudentData[] allStudentData = DataStorage.getAllStudents();

        String[] studentListNew = new String[100];
        for (int i = 0; i < allStudentData.length; i++) {
            studentListNew[i] = String.valueOf(allStudentData[i].getStudentID());
        }

        studentDropdown = new JComboBox<>(studentListNew);
        studentDropdown.setMaximumSize(new Dimension(400,50)); // Sets size for component

        // On selecting a student profile using the dropdown, the module information panel is updated.
        studentDropdown.addActionListener(e ->{
            studentSelected();
        });

        studentSelectPanel.add(studentDropdown, BorderLayout.NORTH);

        return studentSelectPanel;
    }

    private JPanel createInstructionPanel(){
        JPanel instructionPanel = new JPanel();
        instructionPanel.setLayout(new BorderLayout());
        instructionPanel.setBorder(new TitledBorder("Instructions"));
        Dimension instructionPanelDimension = new Dimension(400,200);
        instructionPanel.setMinimumSize(instructionPanelDimension);
        instructionPanel.setPreferredSize(instructionPanelDimension);
        instructionPanel.setMaximumSize(instructionPanelDimension);

        JLabel selectStudentLabel = new JLabel("Saved student data can be retrieved from the dropdown above.");
        JLabel fillingSectionsInstructLabel = new JLabel("Module cells accept alphanumeric characters.");
        JLabel fillingSectionsInstructLabel2 = new JLabel("Credit and Marks cells accept only integers.");
        JLabel completeCellsInstructLabel = new JLabel("Ensure all visible cells are filled.");
        JLabel completeCellsInstructLabel2 = new JLabel("Use the slider to modify the number of modules accepted.");
        JLabel modulesInstructLabel = new JLabel("Modules: Only valid module codes are accepted.");
        JLabel creditInstructLabel = new JLabel("Credits: Credit totals for level 5 and 6 must each amount to 120.");
        JLabel marksInstructLabel = new JLabel("Marks: Values lower than 40 will result in a failing grade.");

        JPanel instructionContainer = new JPanel();
        instructionContainer.setLayout(new BoxLayout(instructionContainer, BoxLayout.Y_AXIS));
        instructionContainer.add(selectStudentLabel);
        instructionContainer.add(Box.createVerticalGlue());
        instructionContainer.add(fillingSectionsInstructLabel);
        instructionContainer.add(fillingSectionsInstructLabel2);
        instructionContainer.add(Box.createVerticalGlue());
        instructionContainer.add(completeCellsInstructLabel);
        instructionContainer.add(completeCellsInstructLabel2);
        instructionContainer.add(Box.createVerticalGlue());
        instructionContainer.add(modulesInstructLabel);
        instructionContainer.add(Box.createVerticalGlue());
        instructionContainer.add(creditInstructLabel);
        instructionContainer.add(Box.createVerticalGlue());
        instructionContainer.add(marksInstructLabel);
        instructionContainer.add(Box.createVerticalGlue());
        instructionPanel.add(instructionContainer);
        return instructionPanel;
    }

    // --- "Left" panel end

    // --- "Right" panel relevant functions
    private JPanel createRightPanel(){
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        JPanel moduleInformationPanel = createModuleInformationPanel();
        JPanel moduleControls = createModuleControls();

        rightPanel.add(moduleInformationPanel, BorderLayout.NORTH);
        rightPanel.add(moduleControls, BorderLayout.SOUTH);

        return rightPanel;
    }

    private JPanel createModuleInformationPanel(){
        JPanel moduleInformationPanel = new JPanel();
        moduleInformationPanel.setLayout(new BoxLayout(moduleInformationPanel, BoxLayout.X_AXIS));
        moduleInformationPanel.setBorder(new TitledBorder("Grade Input"));
        moduleInformationPanel.setPreferredSize(new Dimension(650, 474));
        moduleInformationPanel.setMinimumSize(new Dimension(650, 474));
        moduleInformationPanel.setMaximumSize(new Dimension(650, 474));

        level5GradePanel = new GradePanel(5);
        level6GradePanel = new GradePanel(6);
        //level5GradePanel = createGradePanel(5);
        //level6GradePanel = createGradePanel(6);

        moduleInformationPanel.add(level5GradePanel);
        moduleInformationPanel.add(level6GradePanel);

        //studentSelected();
        //populateModuleInformationPanel();
        level5GradePanel.updateGradePanels();
        level6GradePanel.updateGradePanels();

        return moduleInformationPanel;
    }

    private void studentSelected(){
        int studentIndex = studentDropdown.getSelectedIndex();
        StudentData[] allStudentData = DataStorage.getAllStudents();
        StudentData currentStudent = allStudentData[studentIndex];
        populateModuleInformationPanel(currentStudent.getStudentGrades());
        setIsDirectEntry(currentStudent.getDirectEntry());
        setModuleNumber(currentStudent.getModuleNum());
    }

    private void setIsDirectEntry(boolean isDirect){
        isDirectEntryCheck.setSelected(isDirect); // Sets the checkbox for whether a student is on a direct entry pathway to true or false.
    }

    private void setModuleNumber(int moduleNumber){
        moduleNumSlider.setValue(moduleNumber); // Sets the module number slider to a given value
    }

    private void populateModuleInformationPanel(String[][][] currentStudentGrades){
        // Populates the fields of level5/6GradePanel based on loaded student data
        level5GradePanel.populatePanel(currentStudentGrades[0]);
        level6GradePanel.populatePanel(currentStudentGrades[1]);
        performCalculations();
    }

    private JPanel createModuleControls(){
        JPanel moduleControls = new JPanel();
        moduleControls.setLayout(new BoxLayout(moduleControls, BoxLayout.X_AXIS));
        moduleControls.setBorder(new TitledBorder("Controls"));

        //JCheckBox isDirectEntryCheck = new JCheckBox("Level 6 Direct Entry");
        isDirectEntryCheck = new JCheckBox("Level 6 Direct Entry");
        isDirectEntryCheck.addChangeListener(e ->
        { // Sets changelistener for control, allowing functionality to fire when the control is interacted with.
            if (isDirectEntryCheck.isSelected()){
                isDirectEntry = true;
                level5GradePanel.hidePanel(false);
            }
            else {
                isDirectEntry = false;
                level5GradePanel.hidePanel(true);
            }
            performCalculations();

        });
        JSeparator controlSeparator = new JSeparator(SwingConstants.VERTICAL);
        controlSeparator.setPreferredSize(new Dimension(2, moduleControls.getHeight()));
        moduleNumSlider = new JSlider(1,MAX_MODULES,numOfModules);
        moduleNumSlider.setToolTipText("Number of Modules");
        moduleNumSlider.setMajorTickSpacing(1);
        moduleNumSlider.setPaintTicks(true);
        moduleNumSlider.setPaintLabels(true);
        moduleNumSlider.addChangeListener(e ->{// Sets changelistener for control, allowing functionality to fire when the control is interacted with.
            numOfModules = moduleNumSlider.getValue();
            level5GradePanel.updateGradePanels();
            level6GradePanel.updateGradePanels();
            performCalculations();
        });
        JLabel numSliderLabel = new JLabel("Number of Modules: ");
        moduleControls.add(isDirectEntryCheck); // Add control to panel
        //moduleControls.add(numSliderLabel); // Add label to panel
        moduleControls.add(controlSeparator); // Add seperator
        moduleControls.add(numSliderLabel); // Add label to panel
        moduleControls.add(moduleNumSlider); // Add control to panel
        return moduleControls; // Return value
    }

    // --- "Right" panel end

    private void performCalculations(){
        // Collates data from Modules/Credits/Marks tables and sends them to GradeCalc for calculation
        String[][][] panelData = getGradePanelData();
        String[][] calculatedMarks = new String[5][];

        calculatedMarks = GradeCalc.calculateGrade(panelData, isDirectEntry, numOfModules);

        gradeCalculationPanel.updateGradeCalculationPanel(calculatedMarks); // Sends resultant data to gradeCalculationPanel for display
    }

    private String[][][] getGradePanelData(){
        String[][][] gradePanelData = new String[2][][];
        gradePanelData[0] = level5GradePanel.getPanelData();
        gradePanelData[1] = level6GradePanel.getPanelData();
        return gradePanelData;
    }

    private class GradePanel extends JPanel { // Extended class that handles the "Grades" panel

        private final GradeColumn modulesColumn;
        private final GradeColumn creditsColumn;
        private final GradeColumn marksColumn;
        private final JTextField creditsTotal;


        private GradePanel(int moduleLevel){

            String panelTitle = "Level " + moduleLevel;
            this.setBorder(new TitledBorder(panelTitle));
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            Dimension gradePanelDimension = new Dimension(320,500);
            this.setPreferredSize(gradePanelDimension);
            this.setMinimumSize(gradePanelDimension);
            this.setMaximumSize(gradePanelDimension);

            if (moduleLevel == 5){
                this.setBackground(Color.YELLOW);
            }
            else if (moduleLevel == 6){
                this.setBackground(Color.BLUE);
            }

            JPanel moduleCreditsMarksContainer = new JPanel();
            moduleCreditsMarksContainer.setLayout(new BoxLayout(moduleCreditsMarksContainer, BoxLayout.X_AXIS));

            modulesColumn = new GradeColumn("Modules:", moduleLevel);
            creditsColumn = new GradeColumn("Credits:", moduleLevel);
            marksColumn = new GradeColumn("Marks:", moduleLevel);

            moduleCreditsMarksContainer.add(modulesColumn);
            moduleCreditsMarksContainer.add(creditsColumn);
            moduleCreditsMarksContainer.add(marksColumn);

            JPanel totalCreditsPanel = new JPanel();
            totalCreditsPanel.setLayout(new BoxLayout(totalCreditsPanel, BoxLayout.X_AXIS));
            totalCreditsPanel.setBorder(new TitledBorder("Total Credits:"));
            creditsTotal = new JTextField();
            creditsTotal.setEditable(false);
            Dimension creditTotalDimension = new Dimension(100,25);
            creditsTotal.setPreferredSize(creditTotalDimension);
            creditsTotal.setMinimumSize(creditTotalDimension);
            creditsTotal.setMaximumSize(creditTotalDimension);
            totalCreditsPanel.add(creditsTotal);

            this.add(moduleCreditsMarksContainer);
            this.add(Box.createVerticalGlue()); // Locks next element to bottom of panel
            this.add(totalCreditsPanel);
            //this.add(modulesColumn);
            //this.add(creditsColumn);
            //this.add(marksColumn);
        }

        public String[][] getPanelData(){
            String[][] panelArray = new String[3][];
            panelArray[0] = modulesColumn.getRows();
            panelArray[1] = creditsColumn.getRows();
            calculateCreditTotals(panelArray[1]);
            panelArray[2] = marksColumn.getRows();
            return panelArray;
        }

        public void populatePanel(String[][] studentGrades){
            // Fills each constituent column of the GradePanel with relevant studentGrade data
            //System.out.println(studentGrades[0]);
            modulesColumn.populateColumn(studentGrades[0]);
            creditsColumn.populateColumn(studentGrades[1]);
            marksColumn.populateColumn(studentGrades[2]);

        }

        private void updateGradePanels(){
            modulesColumn.displayColumnElements();
            creditsColumn.displayColumnElements();
            marksColumn.displayColumnElements();
            this.revalidate();
            this.repaint();
        }

        private void calculateCreditTotals(String[] creditTotal){
            // Ensures that the total credit values for each level are equal to 120
            int totalCredits = 0;
            for(int i = 0; i < numOfModules; i++){
                try {
                    totalCredits += Integer.parseInt(creditTotal[i]);
                }
                catch (NumberFormatException e){
                    // Add nothing
                }
            }

            if(totalCredits !=120){
                creditsTotal.setBackground(Color.RED);
                creditsTotal.setText(String.valueOf(totalCredits));
            }
            else {
                creditsTotal.setBackground(Color.GREEN);
                creditsTotal.setText(String.valueOf(totalCredits));
            }

        }

        private void hidePanel(boolean hide){
            this.setVisible(hide);
            this.revalidate();
            this.repaint();

        }
    }

    private class GradeColumn extends JPanel {
        private final String columnType;
        private final JTextField[] rows = new JTextField[MAX_MODULES];

        private GradeColumn(String columnType, int moduleLevel){ // Factory Class that Creates a column of Modules/Credits/Marks
            this.columnType = columnType;
            this.setBorder(new TitledBorder(columnType));
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            Dimension rowDimension = new Dimension(75,25);

            for(int i=0; i<rows.length; i++){
                rows[i] = new JTextField();
                rows[i].setPreferredSize(rowDimension);
                rows[i].setMinimumSize(rowDimension);
                rows[i].setMaximumSize(rowDimension);
                //addTextChangeListener(rows[i],i);
                //fieldActionListener(rows[i],i);
//                rows[i].addActionListener(e -> {
//                    performCalculations();
//                });
                rows[i].addFocusListener(new FocusListener() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        // Do nothing
                    }

                    @Override
                    public void focusLost(FocusEvent e) {
                        // Attempts to perform calculations using field data
                        performCalculations();
                    }
                });

                switch (this.columnType){
                    case "Modules:":
                        if(moduleLevel == 5){ // Implement level 5 module code document filter
                            String[] level5Modules = DataStorage.getLevel5ModuleCodes();
                            ((AbstractDocument) rows[i].getDocument()).setDocumentFilter(new ModuleCodeDocumentFilter(level5Modules));
                        }
                        else if (moduleLevel == 6){ // Implement level 6 module code document filter
                            String[] level6Modules = DataStorage.getLevel6ModuleCodes();
                            ((AbstractDocument) rows[i].getDocument()).setDocumentFilter(new ModuleCodeDocumentFilter(level6Modules));
                        }

                        break;
                    case "Credits:":
                        // Sets a document filter to prevent values that cannot convert to an integer being input
                        ((AbstractDocument) rows[i].getDocument()).setDocumentFilter(new IntegerDocumentFilter());
                        break;
                    case "Marks:":
                        // Sets a document filter to prevent values that cannot convert to an integer being input
                        ((AbstractDocument) rows[i].getDocument()).setDocumentFilter(new IntegerDocumentFilter());
                        break;
                }
                this.add(rows[i]);
            }

        }

        public void populateColumn(String[] columnData){

            for(int i=0; i<rows.length; i++){
                //System.out.println(columnData[i]);
                DocumentFilter filter = ((AbstractDocument) rows[i].getDocument()).getDocumentFilter();
                ((AbstractDocument) rows[i].getDocument()).setDocumentFilter(null); // Temporarily disable documentFilter to prevent issues filling row
                rows[i].getDocument().putProperty("parentUI", this);

                if(columnData[i] != null && !columnData[i].isEmpty()){
                    rows[i].setText(columnData[i]);
                }
                else{
                    rows[i].setText("");
                }
                ((AbstractDocument) rows[i].getDocument()).setDocumentFilter(filter);
                // Re-enables documentFilter
//                try {
//                    rows[i].setText(columnData[i]); // Sets text to new values
//                }
//                catch (Exception e){
//                    rows[i].setText(""); // Sets row as blank
//                }
            }
        }

        public String[] getRows(){
            String[] rowData = new String[MAX_MODULES];
            for(int i=0; i<rows.length; i++){
                rowData[i] = rows[i].getText();
            }
            return rowData;
        }
        public void displayColumnElements(){ // Sets the visibility of JTextfields according to the number of modules selected
            for(int i=0; i<rows.length; i++){
                if(i < numOfModules){
                    rows[i].setVisible(true);
                }
                else{rows[i].setVisible(false);}
            }
        }
    }

    private class CalculationPanel extends JPanel{

        public CalculationSection calcA;
        public CalculationSection calcB;
        public CalculationSection calcC;
        public CalculationSection calcD;
        public CalculationSection calcFinal;
        public CalculationSection calcError;

        public CalculationPanel(){
            this.setLayout(new BorderLayout());
            this.setBorder(new TitledBorder("Calculations and Final Grade"));
            Dimension gradeCalculationPanelDimension = new Dimension(400,265);
            this.setPreferredSize(gradeCalculationPanelDimension);
            this.setMinimumSize(gradeCalculationPanelDimension);
            this.setMaximumSize(gradeCalculationPanelDimension);
            // Subpanel using Gridlayout to align labels vertically
            JPanel calcLabelGrid = new JPanel();
            calcLabelGrid.setLayout(new GridLayout(7, 1));

            calcA = new CalculationSection("Method A: ");
            calcB = new CalculationSection("Method B: ");
            calcC = new CalculationSection("Method C: ");
            calcD = new CalculationSection("Method D: ");
            calcFinal = new CalculationSection("Final Grade: ");

            calcError = new CalculationSection("");

            JButton calcExplanations = new JButton("Calculation Method Explanations");
            calcExplanations.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Create modal dialog
                    JDialog calcExplanationsDialog = new JDialog(GradeCalcCardUI.this, "Calculations",true);
                    calcExplanationsDialog.setSize(1200,200);
                    calcExplanationsDialog.setLayout(new GridLayout(6, 1));
                    JLabel methodAExplanation = new JLabel("Method A: The average mark of all module marks achieved at Level 5 and Level 6, (L6 average + L5 average) ÷2 = overall mark ");
                    JLabel methodBExplanation = new JLabel("Method B: The average mark of all module marks achieved at Level 5 and Level 6, weighted in the 2:1 favour of level 6 credits, (L5\n" +
                            "average + L6 average + L6 average) ÷ 3 = Overall mark ");
                    JLabel methodCExplanation = new JLabel("Method C: Applicable ONLY to students who enter directly onto Level 6. The average mark of all module marks achieved a Level 6 ");
                    JLabel methodDExplanation = new JLabel("Method D: Mark profiling for each of the above methods. Please note that if method D gives a higher classification than any of the\n" +
                            "other methods (A-C) this will be your final classification ");
                    JLabel finalMarkExplanation = new JLabel("Final Grade: Highest grade achieved through methods A, B, C, or D.");
                    calcExplanationsDialog.add(methodAExplanation);
                    calcExplanationsDialog.add(methodBExplanation);
                    calcExplanationsDialog.add(methodCExplanation);
                    calcExplanationsDialog.add(methodDExplanation);
                    calcExplanationsDialog.add(finalMarkExplanation);

                    JButton closeButton = new JButton("Close");
                    closeButton.addActionListener(e1 -> calcExplanationsDialog.dispose());
                    calcExplanationsDialog.add(closeButton);
                    calcExplanationsDialog.setLocationRelativeTo(GradeCalcCardUI.this);
                    calcExplanationsDialog.setVisible(true);

                }
            });
            calcLabelGrid.add(calcA);
            calcLabelGrid.add(calcB);
            calcLabelGrid.add(calcC);
            calcLabelGrid.add(calcD);
            calcLabelGrid.add(calcFinal);
            calcLabelGrid.add(calcError);
            calcLabelGrid.add(calcExplanations);

            this.add(calcLabelGrid);
        }

        public void updateGradeCalculationPanel(String[][] calculatedMarks) {
            calcA.modifyText(calculatedMarks[0]);
            calcB.modifyText(calculatedMarks[1]);
            calcC.modifyText(calculatedMarks[2]);
            calcD.modifyText(calculatedMarks[3]);
            calcFinal.modifyText(calculatedMarks[4]);
        }


    }

    private class CalculationSection extends JPanel {

        private JLabel panelValue = new JLabel();

        private CalculationSection(String displayText){
            this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

            JLabel panelDescriptor = new JLabel();
            panelDescriptor.setText(displayText);
            panelValue.setText("N/A");
            this.add(panelDescriptor);
            this.add(panelValue);
        }

        public void modifyText(String[] value){

            if(value != null) {
                try { // Attempt to truncate value[0] and display as a percentage
                    double gradeValue = Double.parseDouble(value[0]); // Parses grade from String to Double
                    double truncatedGradeValue = Math.floor(gradeValue * 100) / 100.0; // Using math.floor to truncate grade to 2 decimal places without rounding
                    panelValue.setText(truncatedGradeValue + "% of maximal marks, Award class: " + value[1]);
                }
                catch (NumberFormatException e){ // if value[0] is not a number then output the following
                    panelValue.setText(value[0] + " " + value[1]);
                }
            }
            else{panelValue.setText("N/A");}
        }
    }
}

