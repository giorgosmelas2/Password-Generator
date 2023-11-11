import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 *
 * @author giorgos
 */
public class PasswordGeneratorGUI extends javax.swing.JFrame {

    private JLabel description, specialCharacter, characterNumber, uppers, numbers, generatedPassword;
    private JTextField characterNumberText, passwordField;
    private JPanel northPanel, fatherPanel, leftPanel, centerPanel, rightPanel;
    private JButton generatorButton;
    private JSeparator separator1;
    private JCheckBox specialCharacterCheckYes, specialCharacterCheckNo, uppersCheckYes,uppersCheckNo, numbersCheckYes, numbersCheckNo;
            
    public PasswordGeneratorGUI() {
        initComponentsNew();
    }
    
    private void initComponentsNew(){
        setSize(640,380);
        setLocation(700,300);
        setTitle("Password Generator");
        
        setLayout(new BorderLayout());
        
        //Top panel for app description
        northPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        add(northPanel, BorderLayout.NORTH);
        
        description = new JLabel("This appliction generates strong passwords as you want.");
        northPanel.add(description);
        
        fatherPanel = new JPanel(new BorderLayout());
        add(fatherPanel, BorderLayout.CENTER);
        
        //Left panel 
        leftPanel = new JPanel(new GridBagLayout());
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setPreferredSize(new Dimension(30 * getWidth() / 100, getHeight()));
        fatherPanel.add(leftPanel, BorderLayout.WEST);
        
        //Empty space
        leftPanel.add(Box.createVerticalStrut(4 * getHeight() / 100));
        
        //Number of characters
        characterNumber = new JLabel("Number of characters?");
        leftPanel.add(characterNumber);
        
        characterNumberText = new JTextField();
        characterNumberText.setMaximumSize(new Dimension(95 * getWidth() / 100, 10 *getHeight() / 100));        
        leftPanel.add(characterNumberText);
        
        //Empty space
        leftPanel.add(Box.createVerticalStrut(4 * getHeight() / 100));
        
        //Special character
        specialCharacter = new JLabel("Special Character (!,@,#...)?");
        leftPanel.add(specialCharacter);
        
        specialCharacterCheckYes = new JCheckBox("Yes");
        leftPanel.add(specialCharacterCheckYes);
        
        specialCharacterCheckYes.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e){
               if(specialCharacterCheckYes.isSelected()){
                   specialCharacterCheckNo.setEnabled(false);
               }else{
                   specialCharacterCheckNo.setEnabled(true);
               }
                
           }   
        });
        
        specialCharacterCheckNo = new JCheckBox("No");
        leftPanel.add(specialCharacterCheckNo);
        
        specialCharacterCheckNo.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e){
                if(specialCharacterCheckNo.isSelected()){
                    specialCharacterCheckYes.setEnabled(false);
                }else{
                    specialCharacterCheckYes.setEnabled(true);
                }
            }
        });
        
        //Empty space
        leftPanel.add(Box.createVerticalStrut(4 * getHeight() / 100));
       
        //Uppers
        uppers = new JLabel("Uppers?");
        leftPanel.add(uppers);
        
        uppersCheckYes = new JCheckBox("Yes");
        leftPanel.add(uppersCheckYes);
        
        uppersCheckYes.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e){
                if(uppersCheckYes.isSelected()){
                    uppersCheckNo.setEnabled(false);
                }else{
                    uppersCheckNo.setEnabled(true);                    
                }
            }
        });
        
        uppersCheckNo = new JCheckBox("No");
        leftPanel.add(uppersCheckNo);
        
        uppersCheckNo.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e){
                if(uppersCheckNo.isSelected()){
                    uppersCheckYes.setEnabled(false);
                }else{
                    uppersCheckYes.setEnabled(true);                    
                }
            }
        });
        
        //Empty space
        leftPanel.add(Box.createVerticalStrut(4 * getHeight() / 100)); 
        
        //Numbers
        numbers = new JLabel("Numbers?");
        leftPanel.add(numbers);
        
        numbersCheckYes = new JCheckBox("Yes");
        leftPanel.add(numbersCheckYes);
        
        numbersCheckYes.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e){
                if(numbersCheckYes.isSelected()){
                    numbersCheckNo.setEnabled(false);
                }else{
                    numbersCheckNo.setEnabled(true);
                }
            }
        });
        
        numbersCheckNo = new JCheckBox("No");
        leftPanel.add(numbersCheckNo);
        
        numbersCheckNo.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e){
                if(numbersCheckNo.isSelected()){
                    numbersCheckYes.setEnabled(false);
                }else{
                    numbersCheckYes.setEnabled(true);
                }
            }
        });
        
        //Empty space
        leftPanel.add(Box.createVerticalStrut(4 * getHeight() / 100)); 
        
        //Generator Button
        generatorButton = new JButton("Generate");
        leftPanel.add(generatorButton);
        
        generatorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(validateNumber() && oneCheckBoxisSelected()){
                    generatePassword();
                }   
            }
        });
           
        // Center panel 
        centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setPreferredSize(new Dimension(2 * getWidth() / 100, getHeight()));
        fatherPanel.add(centerPanel, BorderLayout.CENTER);

        // Separator
        separator1 = new JSeparator(SwingConstants.VERTICAL);
        centerPanel.add(separator1);

        // Right panel 
        rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setPreferredSize(new Dimension(68 * getWidth() / 100, getHeight()));
        fatherPanel.add(rightPanel, BorderLayout.EAST);

        
        //Empty space
        rightPanel.add(Box.createVerticalStrut(4 * getHeight() / 100));
        
        //Genarated password
        generatedPassword = new JLabel("Password");
        rightPanel.add(generatedPassword);
        
        passwordField = new JTextField();
        passwordField.setMaximumSize(new Dimension(95 * getWidth() / 100, 10 *getHeight() / 100));        
        rightPanel.add(passwordField);

        setVisible(true);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    } 
    
    //Setting password in JTextField
    public void setPassword(String password){
        passwordField.setText(password);
    }
    
    //Length of password
    public int getLength(){
        return Integer.parseInt(characterNumberText.getText());
    }
    
    //Password generation
    private void generatePassword() {
        SwingWorker<String, Void> worker = new SwingWorker<String, Void>() {
        @Override
        protected String doInBackground() throws Exception {
            // Perform password generation in the background thread
            return PasswordGenerator.generatePassword(getLength(), specialCharacterCheckYes.isSelected(), uppersCheckYes.isSelected(), numbersCheckYes.isSelected());
        }

        @Override
        protected void done() {
            try {
                // Update the GUI with the generated password
                String password = get();
                setPassword(password);
                } catch (Exception ex) {
                ex.printStackTrace();
                }
            }   
        };
        worker.execute(); // Start the background task
    }
    
    //Check if al least one checkbox of each option is checked
    public boolean oneCheckBoxisSelected(){
        boolean uppersSelect = uppersCheckYes.isSelected() || uppersCheckNo.isSelected();
        boolean specialCharacterSelect = specialCharacterCheckYes.isSelected() || specialCharacterCheckNo.isSelected();
        boolean numbersSelect = numbersCheckYes.isSelected() || numbersCheckNo.isSelected();
        
        if (!uppersSelect || !specialCharacterSelect || !numbersSelect) {
            JOptionPane.showMessageDialog(this, "Select at least one checkbox in each option", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }
    
    //Check if password is at least 8 numbers
    public boolean validateNumber(){
        try{
            int number = Integer.parseInt(characterNumberText.getText());
            if (number < 8){
                JOptionPane.showMessageDialog(this, "Password must be over 8 characters!","Warning", JOptionPane.WARNING_MESSAGE);
                return false;
            } 
        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(this, "Invalid input! Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE );
            return false;
        }
        return true;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PasswordGeneratorGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PasswordGeneratorGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PasswordGeneratorGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PasswordGeneratorGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
         SwingUtilities.invokeLater(new Runnable()  {
            public void run() {
                new PasswordGeneratorGUI().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}