import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.GridLayout;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.swing.JMenuBar;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import java.awt.BorderLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.jgoodies.forms.factories.FormFactory;

import javax.swing.JTextArea;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class EditButton {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditButton window = new EditButton();
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
	public EditButton() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		
		JComboBox comboBox = new JComboBox();
		
		JLabel lblNewLabel = new JLabel("Enter New Name");
		
		JTextArea textArea = new JTextArea();
		
		JButton btnRename = new JButton("Rename");
		btnRename.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Abc ;
				Abc=comboBox.getSelectedItem().toString() ;
				String Abc1 ;
				Abc1=textArea.getText();
				Properties props = new Properties();

			    String propsFileName = "./src/titlesConfig.properties";
			    try {
			      //first load old one:
			      FileInputStream configStream = new FileInputStream(propsFileName);
			      props.load(configStream);
			      

			      //modifies existing or adds new property
			      props.remove(Abc) ;
			      props.setProperty(Abc,Abc1+",1");
			      
			      System.out.println("Done");
			      JOptionPane.showMessageDialog(null,"Name Changed ","Changed",JOptionPane.WARNING_MESSAGE);

			      //save modified property file
			      FileOutputStream output = new FileOutputStream(propsFileName);
			      props.store(output, null);
			      
			      output.close();
			      configStream.close();
			      ConfigView j = new ConfigView () ;
			      j.initialize();
			      
			      

			    } catch (IOException ex) {
			      ex.printStackTrace();
			    }
			  }
			
			
		});
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(40)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(comboBox, 0, 145, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnRename, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addGap(192))
								.addComponent(textArea, GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE))
							.addGap(23))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(32)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
					.addGap(32)
					.addComponent(btnRename)
					.addContainerGap(102, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
		comboBox.addItem("Select Field") ;
		
		Properties prop = new Properties();
		String propFileName = "titlesConfig.properties";

		InputStream in = getClass().getClassLoader().getResourceAsStream(propFileName);

		if (in != null) {
		try {
			prop.load(in);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		} else {
		try {
			throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		try {
			prop.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i=0 ; i<prop.keySet().size();i++)
		{String arr = (String) prop.keySet().toArray()[i];
		System.out.println(arr);
		if (arr.equals("FirstHardDrive")==false&&arr.equals("SecondHardDrive")==false&&arr.equals("ThirdHardDrive")==false&&arr.equals("FourthHardDrive")==false)
		{ comboBox.addItem(arr);}
		
		  } 
		
		
		
		
		  }
	}

