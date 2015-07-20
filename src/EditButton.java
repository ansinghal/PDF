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
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
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
JTextArea textArea1 = new JTextArea();
textArea1.addFocusListener(new FocusAdapter() {
	@Override
	public void focusLost(FocusEvent e) {
		String ert = textArea1.getText() ;
		Integer erti = Integer.parseInt(ert) ;
		
		
		
		
	}
});

JLabel lblNewLabel = new JLabel("Enter New Name");
JTextArea textArea = new JTextArea();
JComboBox comboBox_1 = new JComboBox();
comboBox_1.addItem("Show") ;

comboBox_1.addItem("Hidden");
JButton btnRename = new JButton("Update");
btnRename.addActionListener(new ActionListener() {
public void actionPerformed(ActionEvent e) {
String Abc ;
String Abc2 ;
String Abc3 ;
Abc3=textArea1.getText();
Integer Abc4=Integer.parseInt(Abc3) ;

Abc=comboBox.getSelectedItem().toString() ;
Abc2=comboBox_1.getSelectedItem().toString() ;
if (Abc.equals("Select Field")==true)
{JOptionPane.showMessageDialog(null,"Enter Field ","Error",JOptionPane.WARNING_MESSAGE);
}
else{
String Abc1 ;
Abc1=textArea.getText();
Properties props = new Properties();
if(Abc.endsWith("Disk")) /* This if else block,3 lines of code is used so that
// the order in which the Vm disk comes in the quotation
// is not changed when a person renames them.*/
{
if (Abc1.endsWith("Disk"))
{}
else
Abc1= Abc1 +"Disk";
}
String propsFileName = "titlesConfig.properties";
try {
//first load old one:
FileInputStream configStream = new FileInputStream(propsFileName);
props.load(configStream);
String Temp [] = new String [3] ;
String Temp1 [] = new String [3] ;
String Temp2 [] = new String [3] ;
String Name = new String () ;
if (Abc4>props.keySet().size())
	JOptionPane.showMessageDialog(null,"Wrong Order Value","Error",JOptionPane.WARNING_MESSAGE);
else
	
{
for (int i=0 ; i<props.keySet().size();i++)
{


	
	String arr1 = (String) props.keySet().toArray()[i];
    System.out.println("arr1:"+arr1);
    String arr3= props.getProperty(Abc) ;
    Temp2=arr3.split(",") ;
    String Name1=Temp2[0] ;
    Integer Asd1= Integer.parseInt(Temp2[2]) ;
    
    String temp1 = props.getProperty(arr1) ;
    Temp1=temp1.split(",");
    Integer Asd4 = Integer.parseInt(Temp1[2]) ;
    
     Name = Temp1[0];
    
    if (Abc4>Asd1)
    {
    	
    	if (Asd4>Asd1&&Asd4<=Abc4)
    {
    	
    		Asd4=Asd4-1 ;
    		if (Abc2.equals("Hidden"))
    		{ 
    		props.setProperty(arr1,Name+",0"+","+Asd4);}
    		if (Abc2.equals("Show"))
    		{
    		
    		props.setProperty(arr1,Name+",1"+","+Asd4);}
    	
    }
    }
    	else if ((Abc4<Asd1))
    	{
    		if (Asd4<=Asd1&&Asd4>Abc4)
    		{Asd4=Asd4+1 ;
    		if (Abc2.equals("Hidden"))
    		{ 
    		props.setProperty(arr1,Name+",0"+","+Asd4);}
    		if (Abc2.equals("Show"))
    		{
    		
    		props.setProperty(arr1,Name+",1"+","+Asd4);}}
    		
    		
    	}
    	 
    
			
    
    	

    

    





System.out.println("Done");
}


}


props.setProperty(Abc,Abc1+",1"+","+Abc4);



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
}
});
JLabel lblNewLabel_1 = new JLabel("Select one ");
JButton btnNewButton = new JButton("Exit");
btnNewButton.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
		System.exit(0);
	}
});
JLabel lblNewLabel_2 = new JLabel("NOTE: Program needs to restart for values to be updated");
JLabel lblEnterOrderNumber = new JLabel("Enter Order Number");

GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
groupLayout.setHorizontalGroup(
	groupLayout.createParallelGroup(Alignment.LEADING)
		.addGroup(groupLayout.createSequentialGroup()
			.addGap(27)
			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(lblEnterOrderNumber)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(textArea1, GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
					.addContainerGap())
				.addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(4)
							.addComponent(lblNewLabel_1)))
					.addGap(4)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(textArea, GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnRename, GroupLayout.PREFERRED_SIZE, 276, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(23, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(comboBox, 0, 276, Short.MAX_VALUE)
								.addComponent(comboBox_1, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE))
							.addGap(23))))))
);
groupLayout.setVerticalGroup(
	groupLayout.createParallelGroup(Alignment.LEADING)
		.addGroup(groupLayout.createSequentialGroup()
			.addGap(6)
			.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
			.addGap(13)
			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(textArea, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
				.addComponent(lblNewLabel, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
			.addPreferredGap(ComponentPlacement.UNRELATED)
			.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addComponent(lblNewLabel_1))
			.addPreferredGap(ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
				.addComponent(lblEnterOrderNumber)
				.addComponent(textArea1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
			.addGap(26)
			.addComponent(lblNewLabel_2)
			.addGap(18)
			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
				.addComponent(btnRename)
				.addComponent(btnNewButton))
			.addGap(34))
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
if (arr.equals("FirstHardDrive")==false&&arr.equals("SecondHardDrive")==false&&arr.equals("ThirdHardDrive")==false&&arr.equals("FourthHardDrive")==false&&arr.contains("HardDrive")==false)
{ comboBox.addItem(arr);}
try {
	in.close();
} catch (IOException e1) {
	// TODO Auto-generated catch block
	e1.printStackTrace();
}

}


comboBox.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent e) {
		Properties props = new Properties();
		try {
			String arrt [] ;
			String propsFileName = "titlesConfig.properties";
			InputStream in = getClass().getClassLoader().getResourceAsStream(propFileName);
			//first load old one:
			
			props.load(in);
			String aib = (String)comboBox.getSelectedItem() ;
			String Name2=props.getProperty(aib) ;
			String properties []=new String [3] ;
			properties=Name2.split(",") ;
			Integer y = Integer.parseInt(properties[1]) ;
			if (aib.equals("Select Field"))
			
			{textArea.setText("");
			} 
			else
			{
			String aic = props.getProperty(aib) ;
			arrt=aic.split(",") ;
			textArea.setText(arrt[0]);
			textArea1.setText(arrt[2]);
			}
			if (y==0)
			{comboBox_1.setSelectedItem("Hidden"); }
			
			
		
			
			
			
			FileOutputStream output = new FileOutputStream(propsFileName);
			props.store(output, null);
			
			output.close();
			
			


			} catch (IOException ex) {
			ex.printStackTrace();
			}
		
		
		
	}		
});
}
}



