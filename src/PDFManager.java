import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

public class PDFManager {
	private PDFParser parser;
	   private PDFTextStripper pdfStripper;
	   private PDDocument pdDoc ;
	   private COSDocument cosDoc ;
	  
	   private String Text ;
	   private String filePath;
	   private File file;
	 
	    public PDFManager() 
	    { } 
	        
	    
	   public String ToText() throws IOException
	   {
	       this.pdfStripper = null;
	       this.pdDoc = null;
	       this.cosDoc = null;
	      
	       file = new File(filePath);
	       parser = new PDFParser(new FileInputStream(file));
	      
	       parser.parse();
	       cosDoc = parser.getDocument();
	       pdfStripper = new PDFTextStripper();
	       pdDoc = new PDDocument(cosDoc);
	       pdDoc.getNumberOfPages();
	       pdfStripper.setEndPage(pdDoc.getNumberOfPages());
	       // reading text from page 1 to 10
	       // if you want to get text from full pdf file use this code
	       // pdfStripper.setEndPage(pdDoc.getNumberOfPages());
	      
	       Text = pdfStripper.getText(pdDoc);
	       //for not including the extra text at the start and end of the file:
	       int start = Text.indexOf("Computing Instance");
	       int end = Text.lastIndexOf("Powered by TCPDF");
	       Text = Text.substring(start,end);
	       
	       //quantity row doesn't have proper delimiters,inserting $ after every number
	       //except the last:
	       int index = Text.indexOf("Quantity");
	       index = index + 9;
	       //System.out.println("index of quantity +9=" + index);
	       int i;
	       StringBuilder t = new StringBuilder(Text);
	       for(i=0;i<4;i++)
	       {
	    	   t.insert(index, " $");
	    	   index = index + 4;
	       }
	       Text = t.toString();
	       return Text;
	   }
	 
	    public void setFilePath(String filePath) {
	        this.filePath = filePath;
	    }
		
	  
	}
	