import java.io.IOException;
import java.io.*;


public class PdfTextDemo {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		 PDFManager pdfManager = new PDFManager();
		pdfManager.setFilePath("C:\\test.pdf");
	            
	       
	       File file = new File("c:\\newfile.txt");
	       file.createNewFile();
	       
	       FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(pdfManager.ToText());
			bw.close();
			System.out.println("Done");
			
			 
	       
	    
	}

}
