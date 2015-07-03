package testParse;

/*
 * Project: To convert a price quote to an excel workbook
 * This class contains the main function.
 */


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;

import xmlGenerator.XmlGenerator;
import xmlReader.xmlRead;


public class MainFunction {

	public static void main(String[] args) throws IOException {
		   FirstParse pdfManager = new FirstParse();
		   //writing console output to a file:
		  // PrintStream out = new PrintStream(new FileOutputStream("C:\\Users\\IBM_ADMIN\\Desktop\\project\\output\\CONSOLE.txt"));
		   //System.setOut(out);
		   //input file
		 String [] fname = new String[4];
		 
		   fname[3] = "C:\\Users\\IBM_ADMIN\\Desktop\\Project\\test.pdf";
		  fname[0] = "C:\\Users\\IBM_ADMIN\\Desktop\\Project\\test2.pdf";
		  fname[2] = "C:\\Users\\IBM_ADMIN\\Desktop\\Project\\test3.pdf";
		  fname[1] = "C:\\Users\\IBM_ADMIN\\Desktop\\Project\\test4.pdf";
			 
			  File file = new File("C:\\Users\\IBM_ADMIN\\Desktop\\Project\\output\\test.txt");
			  
			 for (int g=0;g<4;g++)
		  {    
				  
				  if(g==0) //file will be created first time 
	                 {  
					  	file.delete();
        	           file.createNewFile();
                      }
				  
				  FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
			  BufferedWriter bw = new BufferedWriter(fw);
				
				  pdfManager.setFilePath(fname[g]);
		    
	         //output file           
	        
	        
	       
	        
	        
	        //parsing:
	      
	       
	        bw.write(pdfManager.ToText());
	        //writing next finally
	        bw.write("\nNEXT:\n");
	        bw.close();
	        System.out.println("DoneParsing");
	        //writing to produce a csv file
	        FirstWrite fwrite = new FirstWrite(file);
	        String outputPath = "C:\\Users\\IBM_ADMIN\\Desktop\\project\\output\\test.csv";
	        fwrite.write(outputPath);
	        System.out.println("DoneWriting");
		  }
	       //xml file generation:
		   String xmlPath = "C:\\Users\\IBM_ADMIN\\Desktop\\project\\output\\test.xml";
		   String delimiter = ",";
		   XmlGenerator x = new XmlGenerator("C:\\Users\\IBM_ADMIN\\Desktop\\project\\output\\test.csv",xmlPath,delimiter);
		   //reading the generated xml and creating the appropriate csv files:
		   xmlRead xlr = new xmlRead(xmlPath,"C:\\Users\\IBM_ADMIN\\Desktop\\project\\output\\test.csv");
		 
	}

}

