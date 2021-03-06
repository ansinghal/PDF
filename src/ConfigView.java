import java.awt.EventQueue;

import javax.print.DocFlavor.URL;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.CharBuffer;
import java.util.Arrays;
import java.util.Properties;

import javax.swing.DefaultListModel;
import javax.swing.JScrollBar;
import javax.swing.BoxLayout;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.JList;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;


public class ConfigView {

	public JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConfigView window = new ConfigView();
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
	public ConfigView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
		
		
		
			
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



		// get the property value and print it out

		//BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));




		try {
			prop.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JTable table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		 DefaultTableModel dtm = new DefaultTableModel(0, 0);

		// add header of the table
		String header[] = new String[] { "Field","Title","Shown","Order" };

		// add header in table model     
		 dtm.setColumnIdentifiers(header);
		  
		       table.setModel(dtm);
		       int len =prop.keySet().size() ;
		       String arr1[] = new String[len] ;
		       		
		for (int i=0 ; i<prop.keySet().size();i++)
		{String arr = (String) prop.keySet().toArray()[i];
		System.out.println(arr);
		arr1[i] = arr;}
		Arrays.sort(arr1);
		
		for (int i=0 ; i<prop.keySet().size();i++)
		{  
			String p = prop.getProperty(arr1[i]); 
			String x[] = p.split(",");
			int hidden = Integer.parseInt(x[1].trim());
			String str = null;
			if(hidden == 1)
			{
				str = "YES";
			}
			else
				str = "NO";
				
			dtm.addRow(new Object[] { arr1[i],x[0],str,x[2] });
		}
		
		
		
		  
	try {
		in.close();
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		frame.getContentPane().add(scrollPane);
		
		JButton btnUpdate = new JButton("EDIT");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditButton a = new
				EditButton () ;
				a.main(null);
				
				
				
				
				
			}
		});
		scrollPane.setRowHeaderView(btnUpdate);
		
		
	}

}
