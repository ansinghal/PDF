package commandLine;

import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
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
}