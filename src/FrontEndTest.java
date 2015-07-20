import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import testParse.FirstWrite;
import xmlGenerator.XmlGenerator;
import xmlReader.xmlRead;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;


public class FrontEndTest {

	public JFrame frame;
	private JTextField textField;
	public String data ;

	
    public String input[] ;
    public Integer count ;
    public String output ;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				 PrintStream out;
				try {
					out = new PrintStream(new FileOutputStream("C:\\Users\\IBM_ADMIN\\Desktop\\CONSOLE.txt"));
					System.setOut(out);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				 
				try {
					FrontEndTest window = new FrontEndTest();
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
public FrontEndTest() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setBounds(100, 100, 778, 368);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(45dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblNewLabel = new JLabel("Quote Converter");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		frame.getContentPane().add(lblNewLabel, "8, 4, center, center");
		
		JLabel lblNewLabel_4 = new JLabel("Enter Number Of Files");
		frame.getContentPane().add(lblNewLabel_4, "4, 12, default, center");
		
		textField = new JTextField();
		
		frame.getContentPane().add(textField, "8, 11, 1, 3");
		textField.setColumns(10);
		
		JTextArea textArea = new JTextArea();
		textArea.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				String abc = textField.getText();
				String regex = "[0-9]";
				
				if (abc.matches(regex)==false)
					
				
				{
					textField.requestFocus();
					JOptionPane.showMessageDialog(null,"Wrong Input","Enter Number Only",JOptionPane.WARNING_MESSAGE);
					
				}
				else {}
				
				
			}
		});
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		frame.getContentPane().add(textArea, "8, 18, 7, 5, fill, fill");
		
		JLabel lblNewLabel_1 = new JLabel("Enter Input Paths , Seperated by commas");
		frame.getContentPane().add(lblNewLabel_1, "4, 20, fill, top");
		
		JTextArea textPane = new JTextArea();
		textPane.setLineWrap(true);
		textPane.setWrapStyleWord(true);
		frame.getContentPane().add(textPane, "8, 27, 7, 3");
		
		JLabel lblNewLabel_2 = new JLabel("Enter Output Path");
		frame.getContentPane().add(lblNewLabel_2, "4, 28, default, center");
		
		JButton btnRun = new JButton("Run");
		frame.getContentPane().add(btnRun, "4, 34, default, fill");
		
		JToolBar toolBar = new JToolBar();
		frame.getContentPane().add(toolBar, "8, 34, default, fill");
		
		JLabel lblNewLabel_3 = new JLabel("Config. File");
		toolBar.add(lblNewLabel_3);
		
		JButton btnNewButton_1 = new JButton("View");
		btnNewButton_1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				frame.validate();
				ConfigView x = new ConfigView() ;
				x.initialize();
				x.main(null);
			}
		});
		toolBar.add(btnNewButton_1);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddView n = new AddView () ;
				n.main(null);
			}
		});
		toolBar.add(btnAdd);
		  btnRun.addActionListener(new ActionListener() {
			         public void actionPerformed(ActionEvent e) {     
		        	 frame.validate();
		        	 String abc = textField.getText();
		 			String regex = "[0-9]";
		 			if (abc.matches(regex)==false)
		 			
		 			{
		 				JOptionPane.showMessageDialog(null,"Enter Number Only In Number Of Files","Wrong Input",JOptionPane.WARNING_MESSAGE);
		 			}
		        	 
		        	 
		        	    

		            data = textArea.getText();
		            data = data+"," ;
		            output=textPane.getText();
		            StringBuffer s = new StringBuffer(output);
		        	   s = s.insert(2, "\\");
		        	   output=s.toString(); 
		           
		        	   
		        	   
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
		            	 StringBuffer t = new StringBuffer(input[i]);
			        	   t = t.insert(2, "\\");
			        	   input[i]=t.toString();             
		            	pdfManager.setFilePath(input[i]);
		            //System.out.print(input[i]);
				
					// TODO Auto-generated catch block
		               bw.append(pdfManager.ToText());
				       bw.write("\nNEXT:\n");
				       
				       
		            }
		                    
		            bw.close(); 
		           
		            
			        System.out.println("\nDoneParsing");
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
				   
				   xmlRead xlr = new xmlRead(xmlPath,output+".\\output.csv");
		 	      }
		    catch (FileNotFoundException e1){
		 	    	 JOptionPane.showMessageDialog(null,"Enter Correct Path","File Not Found",JOptionPane.WARNING_MESSAGE);
		 	    	}
		    
		    	
		    catch (IOException e1) {
					e1.printStackTrace();
				}
			 	      if (textPane.getText()=="")
			 	      {
			 	    	 JOptionPane.showMessageDialog(null,"Specify Output Path","Ouput Path Not Specified ",JOptionPane.WARNING_MESSAGE);
			 	      }
			 	      else
			    
			 	    	  
   	     JOptionPane.showMessageDialog(null,"Program Run Over ","End",JOptionPane.WARNING_MESSAGE);

		          	           frame.validate(); 
		          	         frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
				    
		 	      } 
		            
		   
		         

		}) ;

	}
	}
	
