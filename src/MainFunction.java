import java.io.IOException;
import java.io.*;


public class MainFunction {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		   FirstParse pdfManager = new FirstParse();
		   pdfManager.setFilePath("C:\\Users\\IBM_ADMIN\\Desktop\\project\\test.pdf");
	                  
	       File file = new File("C:\\Users\\IBM_ADMIN\\Desktop\\project\\output\\test.txt");
	       file.createNewFile();
	       
	       FileWriter fw = new FileWriter(file.getAbsoluteFile());
	       BufferedWriter bw = new BufferedWriter(fw);
	       bw.write(pdfManager.ToText());
	       bw.close();
	       System.out.println("DoneParsing");
	       
	       FirstWrite fwrite = new FirstWrite(file);
	       fwrite.write();
	       System.out.println("DoneWriting");
			 
	       
	    
	}

}
