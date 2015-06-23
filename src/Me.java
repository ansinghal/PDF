import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import xmlGenerator.XmlGenerator;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.io.BufferedWriter;
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


public class Me {

 public JFrame frame;
 public JFrame frame2 ;
	public String data ;
	private JTextField textField; 
    public String input[] ;
    public Integer count ;

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
		
		textField = new JTextField();
		textField.setBounds(116, 57, 86, 29);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
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
		
	      btnRun.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {     
	            data = textArea.getText();
	            input=data.split(",") ;
	            count=Integer.parseInt(textField.getText()) ;
	            FirstParse pdfManager = new FirstParse();
	            File file = new File("C:\\Users\\IBM_ADMIN\\Desktop\\Project\\output\\test.txt");
	           
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
			       
			       
	            }
	            
	            bw.close(); 
	           
			       System.out.println("DoneParsing");
			       FirstWrite fwrite = new FirstWrite(file);
			       String outputPath = "C:\\Users\\IBM_ADMIN\\Desktop\\project\\output\\test.csv";
			       fwrite.write(outputPath);
			       System.out.println("DoneWriting");
			       //converting the csv file to xlsx
			       String in = outputPath;
			       String finalOut = "C:\\Users\\IBM_ADMIN\\Desktop\\project\\output\\test.xlsx";
				  // csv2excel.convert(in,finalOut);
				   //xml file generation:
				   String xmlPath = "C:\\Users\\IBM_ADMIN\\Desktop\\project\\output\\test.xml";
				   String delimiter = ",";
				   XmlGenerator y = new XmlGenerator(outputPath,xmlPath,delimiter);
				   file.delete() ;
			       
			    
	 	      } catch (IOException e1) {
			}
		 	       
	            
	         }
	            
	           

		
	      }) ;
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
	}
}
