import java.io.IOException;


public class PdfTextDemo {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		 PDFManager pdfManager = new PDFManager();
		pdfManager.setFilePath("C:\\sample.pdf");
	       System.out.println(pdfManager.ToText());      
	       
	    
	}

}
