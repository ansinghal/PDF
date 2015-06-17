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


public class Me {

 public JFrame frame;
 public JFrame frame2 ;
	public String data ;
	private JTextField textField;

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
		frame.setBackground(new Color(112, 128, 144));
		frame.setBounds(300, 250, 493, 319);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		
		JLabel lblNewLabel = new JLabel("Enter Path Seperated By Commas ");
		lblNewLabel.setBounds(10, 116, 209, 34);
		frame.getContentPane().add(lblNewLabel);
		
		
		
		JTextArea textArea = new JTextArea();
		textArea.setForeground(SystemColor.text);
		textArea.setBounds(209, 121, 258, 29);
		frame.getContentPane().add(textArea);
		
		JLabel lblNewLabel_1 = new JLabel("Quote Converter");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setForeground(Color.BLUE);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(158, 6, 136, 44);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnRun = new JButton("Run");
		btnRun.setBounds(50, 224, 89, 34);
		frame.getContentPane().add(btnRun);
		
		JLabel label = new JLabel("");
		label.setBounds(25, 224, 353, 14);
		frame.getContentPane().add(label);
		
		JLabel lblOutputPath = new JLabel("Output Path");
		lblOutputPath.setBounds(10, 183, 67, 14);
		frame.getContentPane().add(lblOutputPath);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(81, 172, 386, 29);
		frame.getContentPane().add(textPane);
		
		JButton btnConfigFile = new JButton("Config. File");
		btnConfigFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnConfigFile.setBounds(275, 224, 89, 34);
		frame.getContentPane().add(btnConfigFile);
		
		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(btnConfigFile, popupMenu);
		
		JButton btnViewConfigFile = new JButton("View Config File");
		popupMenu.add(btnViewConfigFile);
		
		JButton btnEditEntries = new JButton("Edit Entries");
		popupMenu.add(btnEditEntries);
		
		JButton btnDeleteEntries = new JButton("Delete Entries");
		popupMenu.add(btnDeleteEntries);
		
		JButton btnAddEntries = new JButton("Add Entries");
		popupMenu.add(btnAddEntries);
		
		JLabel lblEnterNumberOf = new JLabel("Enter No. Of Files");
		lblEnterNumberOf.setBounds(10, 54, 114, 34);
		frame.getContentPane().add(lblEnterNumberOf);
		
		textField = new JTextField();
		textField.setBounds(116, 57, 86, 29);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
	      btnRun.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {     
	            data = textArea.getText();
	            label.setText(data);
	            FirstParse pdfManager = new FirstParse();
	            pdfManager.setFilePath(data);
	            File file = new File("C:\\Users\\IBM_ADMIN\\Desktop\\Project\\output\\test.txt");
	 	       try {
				file.createNewFile();
			
				// TODO Auto-generated catch block
				
				 FileWriter fw = new FileWriter(file.getAbsoluteFile());
			       BufferedWriter bw = new BufferedWriter(fw);
			       bw.write(pdfManager.ToText());
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
			       
			    
	 	      } catch (IOException e1) {
			}
	            
	         }
	            
	           

		
	      }) ;
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
			
			public void mouseClicked(MouseEvent e) {
			}
		});
	}
}
