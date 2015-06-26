import java.awt.*;

import Config.configcreate;

import javax.swing.*;

import java.io.*;

import javax.swing.JLabel;





import java.awt.Color;

import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import testParse.FirstWrite;
import xmlGenerator.XmlGenerator;
import xmlReader.xmlRead;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JTextPane;
import javax.swing.JTextField;

import java.awt.SystemColor;

import javax.swing.JPopupMenu;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JToolBar;
import javax.swing.JPanel;
import javax.swing.JOptionPane ;

public class Me {

 public JFrame frame;
 public JFrame frame2 ;
	public String data ;

	private JTextField textField; 
    public String input[] ;
    public Integer count ;
    public String output ;

	
	

	/**
	 * Launch the application.
	 */
	public String returnx ()
	{
		return data;
		
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Me window = new Me();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Me() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frame = new JFrame();
		frame.setBackground(new Color(204, 0, 51));
		frame.setBounds(300, 250, 493, 319);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		
		JLabel lblNewLabel = new JLabel("Enter Path Seperated By Commas ");
		lblNewLabel.setBounds(10, 116, 209, 34);
		frame.getContentPane().add(lblNewLabel);
		
		
		

		JTextArea textArea = new JTextArea();
		textArea.setForeground(new Color(0, 0, 51));
		textArea.setBounds(209, 121, 258, 29);
		frame.getContentPane().add(textArea);
		
		JLabel lblNewLabel_1 = new JLabel("Quote Converter");
		lblNewLabel_1.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 24));
		lblNewLabel_1.setForeground(Color.BLUE);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(158, 6, 191, 44);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnRun = new JButton("Run");
		btnRun.setBounds(50, 224, 89, 34);
		frame.getContentPane().add(btnRun);
		
		JLabel lblOutputPath = new JLabel("Output Path");
		lblOutputPath.setBounds(10, 183, 67, 14);
		frame.getContentPane().add(lblOutputPath);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(81, 172, 386, 29);
		frame.getContentPane().add(textPane);
		
		JLabel lblEnterNumberOf = new JLabel("Enter No. Of Files");
		lblEnterNumberOf.setBounds(10, 54, 114, 34);
		frame.getContentPane().add(lblEnterNumberOf);
		
	
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(149, 212, 318, 58);
		frame.getContentPane().add(toolBar);
		
		JLabel lblConfigfile = new JLabel("Config.File");
		toolBar.add(lblConfigfile);
		
		JButton btnView = new JButton("View");
		toolBar.add(btnView);
		
		JButton btnAdd = new JButton("Add");
		toolBar.add(btnAdd);
		
		JButton btnEdit = new JButton("Edit");
		toolBar.add(btnEdit);
		
		JButton btnDelete = new JButton("Delete");
		toolBar.add(btnDelete);
		
		JTextArea textField = new JTextArea();
		textField.setBounds(117, 61, 76, 27);
		frame.getContentPane().add(textField);
		
	      btnRun.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {     

	            data = textArea.getText();
	            data = data+"," ;
	            output=textPane.getText();
	            new File(output).mkdir();
	            

	            

	            
	            
	            input=data.split(",") ;
	            
	            
	            count=Integer.parseInt(textField.getText()) ;
	            if (input.length<count )
	            {
	            	System.out.print("Please enter more files");
	            	JOptionPane.showMessageDialog(null,"Enter More Files","Insufficient number of files",JOptionPane.WARNING_MESSAGE);
	            }
	            
	            FirstParse pdfManager = new FirstParse();
	            File file = new File(output+".txt");
	            file.delete();
	           
		 	       try {
		 	    	  FileWriter fw = new FileWriter(file.getAbsoluteFile());
				       BufferedWriter bw = new BufferedWriter(fw);
				       
					file.createNewFile();
				
	            for (int i =0;i<count;i++)
	            {      
	            
	            
	            
	            
	            
	            pdfManager.setFilePath(input[i]);
	            System.out.print(input[i]);
			
				// TODO Auto-generated catch block
				
				
				
			       bw.append(pdfManager.ToText());
			       bw.write("\nNEXT:\n");
			       
			       
	            }
	          
	            
	            bw.close(); 
	           
	            
		        System.out.println("DoneParsing");
		        //writing to produce a csv file
		        FirstWrite fwrite = new FirstWrite(file);
		        String outputPath =output+"\\test.csv";
		        fwrite.write(outputPath);
		        System.out.println("DoneWriting");
			  
		       //xml file generation:
			   String xmlPath = output+"\\test.xml";
			   String delimiter = ";";
			   String xmlPath1 = output+"\\test1.xml";
			   XmlGenerator x = new XmlGenerator(outputPath,xmlPath,delimiter);
			   configcreate y = new configcreate(xmlPath1,delimiter);
			   xmlRead xlr = new xmlRead(xmlPath,output+".\\output.csv");

			   
		    
		
			       
			    
	 	      } catch (FileNotFoundException e1){
	 	    	 JOptionPane.showMessageDialog(null,"Enter Correct Path","File Not Found",JOptionPane.WARNING_MESSAGE);
	 	    	    
	 	    	    
	 	    	} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		 	      if (textPane.getText()=="")
		 	      {
		 	    	 JOptionPane.showMessageDialog(null,"Specify Output Path","Ouput Path Not Specified ",JOptionPane.WARNING_MESSAGE);
		 	      }
		 	      
		 	    	  
		 	     JOptionPane.showMessageDialog(null,"Find the converted file in the specified path Ronit is awesome  ","File Converted Succesfully  ",JOptionPane.WARNING_MESSAGE);

	          	            
			    
	 	      } 
	            
	   
	         

	}) ;

}
}
