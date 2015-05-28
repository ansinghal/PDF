/*
 * Project: To convert a price quote to an excel workbook
 * This class contains the main function.
 */


import java.io.IOException;
import java.io.*;


public class MainFunction {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		   FirstParse pdfManager = new FirstParse();
		   pdfManager.setFilePath("C:\\Users\\IBM_ADMIN\\Desktop\\Project\\test2.pdf");
	                  
	       File file = new File("C:\\Users\\IBM_ADMIN\\Desktop\\Project\\output\\test.txt");
	       file.createNewFile();
	       //parsing:
	       FileWriter fw = new FileWriter(file.getAbsoluteFile());
	       BufferedWriter bw = new BufferedWriter(fw);
	       bw.write(pdfManager.ToText());
	       bw.close();
	       System.out.println("DoneParsing");
	       //writing to produce a csv file
	       FirstWrite fwrite = new FirstWrite(file);
	       fwrite.write();
	       System.out.println("DoneWriting");
			 
	       
	    
	}

}
