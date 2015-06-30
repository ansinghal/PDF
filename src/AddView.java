import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;


public class AddView {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddView window = new AddView();
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
	public AddView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setBounds(100, 100, 450, 300);
		
		JLabel lblNewLabel = new JLabel("Enter Name Of Field");
		
		JTextArea textArea = new JTextArea();
		
		JLabel lblNewLabel_1 = new JLabel("Select ");
		
		JComboBox comboBox = new JComboBox();
		comboBox.addItem("Show") ;
		comboBox.addItem("Hidden");
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String propsFileName = "./src/titlesConfig.properties";
				    try {
				    	Properties props = new Properties();
				      FileInputStream configStream = new FileInputStream(propsFileName);
				      props.load(configStream);
				      String Abc= textArea.getText();
				      String Abc2=comboBox.getSelectedItem().toString() ;
				      if (Abc2.equals("Hidden"))
				      {  
				      props.setProperty(Abc,Abc+",0");}
				    	  
				      if (Abc2.equals("Show"))
				      {

				      props.remove(Abc) ;
				      props.setProperty(Abc,Abc+",1");}
				      
				     
				      
				      System.out.println("Done");
				      JOptionPane.showMessageDialog(null,"Field Added ","Added",JOptionPane.WARNING_MESSAGE);

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
					.addGap(21)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(comboBox, 0, 263, Short.MAX_VALUE)
						.addComponent(textArea, GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE))
					.addGap(22))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(201, Short.MAX_VALUE)
					.addComponent(btnAdd)
					.addGap(182))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(37)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
					.addGap(42)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
					.addComponent(btnAdd)
					.addGap(19))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
