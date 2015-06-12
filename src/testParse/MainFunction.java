package testParse;

/*
 * Project: To convert a price quote to an excel workbook
 * This class contains the main function.
 */


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import xmlGenerator.XmlGenerator;


public class MainFunction {

	public static void main(String[] args) throws IOException {
		   FirstParse pdfManager = new FirstParse();
		   //input file
		   pdfManager.setFilePath("C:\\Users\\IBM_ADMIN\\Desktop\\Project\\test2.pdf");
	       //output file           
	       File file = new File("C:\\Users\\IBM_ADMIN\\Desktop\\Project\\output\\test.txt");
	       file.createNewFile();
	       //parsing:
	       FileWriter fw = new FileWriter(file.getAbsoluteFile());
	       BufferedWriter bw = new BufferedWriter(fw);
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
	       //xml file generation:
		   String xmlPath = "C:\\Users\\IBM_ADMIN\\Desktop\\project\\output\\test.xml";
		   String delimiter = ";";
		   XmlGenerator x = new XmlGenerator("C:\\Users\\IBM_ADMIN\\Desktop\\project\\output\\test.csv",xmlPath,delimiter);
	    
	}

}

