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
		JTextArea textArea_1 = new JTextArea();
		
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
				      String Abc3= textArea_1.getText() ;
				      Integer Abc4 = Integer.parseInt(Abc3) ;
				      Integer Abc5 = props.keySet().size()+1 ;
				      if (Abc4>Abc5 )
				      {
				    	  JOptionPane.showMessageDialog(null,"Enter Correct Order Number ","Error",JOptionPane.WARNING_MESSAGE);
				    	  
				      }
				      else
				      {
				      
				      String Abc= textArea.getText();
				      String Abc2=comboBox.getSelectedItem().toString() ;
				      String Temp [] = new String [3] ;
				      String Temp1 [] = new String [3] ;
				      String Temp2 [] = new String [3] ;
				      
				      for (int i=0 ; i<props.keySet().size();i++)
				      {String arr = (String) props.keySet().toArray()[i];
				      System.out.println(arr);
				      String temp = props.getProperty(arr) ;
				      Temp=temp.split(",");
				      Integer Asd = Integer.parseInt(Temp[2]) ;
				      for (int j=0 ; j<props.keySet().size();j++)
				      {   String arr1 = (String) props.keySet().toArray()[i];
				          System.out.println(arr1);
				         
				          Integer Asd4= Abc4 ;
				          
				          String temp1 = props.getProperty(arr1) ;
				          Temp1=temp1.split(",");
				          Integer Asd1 = Integer.parseInt(Temp[2]) ;
				          if (Asd1>=Abc4)
				          
				          	
				          {Asd1=Asd1+1 ;
				          	props.setProperty(arr,Temp1[0]+","+Temp1[1]+","+Asd1) ;
				          }
				          	else if ((Asd1>Asd4))
				          	{
				          		if (Asd1>Asd4&&Asd1<=Abc4)
				          		Asd1=Asd1-1 ;
				          		props.setProperty(arr,Temp1[0]+","+Temp1[1]+","+Asd1) ;
				          	}
				          		
				          

				          }
				      }
				      if (Abc2.equals("Hidden"))
				      {  
				      props.setProperty(Abc,Abc+",0");}
				    	  
				      if (Abc2.equals("Show"))
				      {

				      props.remove(Abc) ;
				      props.setProperty(Abc,Abc+",1"+","+Abc3);}
				      
				     
				      
				      System.out.println("Done");
				      JOptionPane.showMessageDialog(null,"Field Added ","Added",JOptionPane.WARNING_MESSAGE);
				      

				      //save modified property file
				      FileOutputStream output = new FileOutputStream(propsFileName);
				      props.store(output, null);
				      
				      output.close();
				      configStream.close();
				      ConfigView j = new ConfigView () ;
				      j.initialize();
				      }
				      

				    } catch (IOException ex) {
				      ex.printStackTrace();
				    }
				  }
				
			
		});
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		JLabel lblNewLabel_2 = new JLabel("NOTE: Values will be updated only when program restarts");
		
		JLabel lblNewLabel_3 = new JLabel("Enter Order ");
		
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(41)
					.addComponent(btnExit)
					.addPreferredGap(ComponentPlacement.RELATED, 109, Short.MAX_VALUE)
					.addComponent(btnAdd)
					.addGap(182))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(21)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel_3)
							.addContainerGap())
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 374, GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
							.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(textArea, GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
										.addGap(22))
									.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
											.addComponent(textArea_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
											.addComponent(comboBox, 0, 263, Short.MAX_VALUE))
										.addContainerGap()))))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(37)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addGap(28)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(textArea_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
					.addComponent(lblNewLabel_2)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAdd)
						.addComponent(btnExit))
					.addGap(19))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
