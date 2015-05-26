import java.io.IOException;
import java.io.*;


public class PdfTextDemo {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		   PDFManager pdfManager = new PDFManager();
		   pdfManager.setFilePath("C:\\Users\\IBM_ADMIN\\Desktop\\project\\test.pdf");
	                  
	       File file = new File("C:\\Users\\IBM_ADMIN\\Desktop\\project\\output\\test.txt");
	       file.createNewFile();
	       
	       FileWriter fw = new FileWriter(file.getAbsoluteFile());
	       BufferedWriter bw = new BufferedWriter(fw);
	       bw.write(pdfManager.ToText());
	       bw.close();
	       System.out.println("Done");
			
			 
	       
	    
	}

}
