package commandLine;

import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.Properties;
import java.util.Scanner;

import javax.swing.JOptionPane;

import testParse.FirstParse;
import testParse.FirstWrite;
import xmlGenerator.XmlGenerator;
import xmlReader.xmlRead;

public class MainCommand {
	
	public static void main(String[] args) throws IOException {
		   FirstParse pdfManager = new FirstParse();
		   //input file
		   Scanner scanner = new Scanner(System.in);
		   System.out.print("Enter number of files:");       
           int count=0;
           while(true)
           {
        	   try
        	   {
        		   count=scanner.nextInt() ;
        		   break;
        	   }
           catch(InputMismatchException e)
        	   {
	        	   System.out.println("Please enter a valid integer which is greater than 0");
	        	   scanner.nextLine();
        	   }
           }
           int i = 0;
           String[] input = new String[count];
           for(i=0;i<count;i++)
           {
        	   System.out.println("Enter the path of file number:"+(i+1));
        	   input[i]=scanner.next();
        	   StringBuffer s = new StringBuffer(input[i]);
        	   s = s.insert(2, "\\");
        	   input[i]=s.toString();
           }
           String output;
           System.out.println("Enter the path of the output folder:");
           output = scanner.next();
           StringBuffer s = new StringBuffer(output);
           s = s.insert(2,"\\");
           output = s.toString();
           new File(output).mkdir();
           
           System.out.println("Enter 1 to view or edit config file;otherwise enter 0:");
           int flag = 0;
           while(true)
           {
        	   try
        	   {
        		   flag=scanner.nextInt() ;
        		   break;
        	   }
           catch(InputMismatchException e)
        	   {
	        	   System.out.println("Please enter a valid integer");
	        	   scanner.nextLine();
        	   }
           }
           int z = 0;
           if(flag==1)
           {
        	   while(true)
        	   {
        		   if(z==1)
        			   break;
        		   int m = 0;
        		   System.out.println("Press 1 to view ConfigFile");
        		   System.out.println("Press 2 to edit Configfile");
        		   System.out.println("Add a field");
        		   System.out.println("Press -1 to continue execution");
        		   if(scanner.hasNextInt())
        			   m = scanner.nextInt();
        		   else
        		   {
        			   scanner.nextLine();
        			   System.out.println("Enter a valid integer");
        			   continue;
        		   }
        		   
        		   switch(m)
        		   {
	        		   case 1:
	        		   {
	        			   try{
	                		   viewConfigFile();
	                		   }
	                		   catch(Exception e){
	                		   System.out.println("Exception thrown while viewing config file");
	                		   System.out.print(e);
	                	   		}
	        			   break;
	        		   }
	        		   case 2:
	        		   {
	        			   //edit
	        			   break;
	        		   }
	        		   case 3:
	        		   {
	        			   //add
	        			   break;
	        		   }
	        		   case -1:
	        		   {
	        			   z = 1;
	        			   break;
	        		   }
        		   }
        	   }
           }
           
           File file = new File(output+".txt");
           file.delete();
          
   try {
   			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			       
			file.createNewFile();
			
           for (i =0;i<count;i++)
           {      
           	            
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
   catch (FileNotFoundException e1)
   {
	   System.out.println("File not found");
   }
   catch (IOException e1) 
   {
	   e1.printStackTrace();
   }
		    
         
	}

	private static void viewConfigFile() throws IOException{
		// TODO Auto-generated method stub
		Properties prop = new Properties();
		String propFileName = "./src/titlesConfig.properties";
		FileInputStream inputStream = new FileInputStream(propFileName);
		if (inputStream != null) 
			{
				prop.load(inputStream);				
			}
		System.out.println(String.format("%s%42s%25s","Key","Title","Hidden"));
		
		for (int i=0 ; i<prop.keySet().size();i++)
		{
			String arr = (String) prop.keySet().toArray()[i];
			String p = prop.getProperty(arr); 
			String x[] = p.split(",");
			
			String s = String.format("%s%40s%5s",arr,x[0].trim(),x[1].trim());
			System.out.println(s);
			//System.out.print("\t"+x[0]+"\t"+x[1]);
		  } 
	
	}
}