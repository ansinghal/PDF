
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SwingControlDemo {

   public JFrame mainFrame;
   public JLabel headerLabel;
   public JLabel statusLabel;
   public JPanel controlPanel;
   public String x ;
   public String y ;

   public  SwingControlDemo(){
      prepareGUI();
   }
   public String returnx ()
   {
	   return x ;
   }

   public String returny () {
	   return y ;
   }
   public static void main(String[] args){
      SwingControlDemo  swingControlDemo = new SwingControlDemo();      
      swingControlDemo.showTextFieldDemo();
   }

   public void prepareGUI(){
      mainFrame = new JFrame("Java Swing Examples");
      mainFrame.setSize(400,400);
      mainFrame.setLayout(new GridLayout(3, 1));
      mainFrame.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent windowEvent){
            System.exit(0);
         }        
      });    
      headerLabel = new JLabel("", JLabel.CENTER);        
      statusLabel = new JLabel("",JLabel.CENTER);    

      statusLabel.setSize(350,100);

      controlPanel = new JPanel();
      controlPanel.setLayout(new FlowLayout());

      mainFrame.add(headerLabel);
      mainFrame.add(controlPanel);
      mainFrame.add(statusLabel);
      mainFrame.setVisible(true);  
   }

   public void showTextFieldDemo(){
      headerLabel.setText("Quote to Excel "); 

      JLabel  namelabel= new JLabel("PDF Path", JLabel.RIGHT);
      JLabel  passwordLabel = new JLabel("Output Path", JLabel.CENTER);
      final  JTextField userText = new JTextField(6);
      final JTextField passwordText = new JTextField(6);      

      JButton loginButton = new JButton("Submit");
      loginButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {     
            String data = "Input Path " + userText.getText();
            data += ", Output Path " 
            + new String(passwordText.getText()); 
            statusLabel.setText(data); 

         }
      }); 

      controlPanel.add(namelabel);
      controlPanel.add(userText);
      controlPanel.add(passwordLabel);       
      controlPanel.add(passwordText);
      controlPanel.add(loginButton);
      mainFrame.setVisible(true);  
      x = userText.getText() ;
      y = passwordText.getText() ;
   }
}