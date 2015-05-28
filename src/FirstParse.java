/*
 * This class contains the function that reads the pdf file and converts it into a text file that:
 * i)has trimmed all the irrelevant information.
 * ii)corrects and replaces the text in files($ for :) so that : can be used as a delimiter.
 * 
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

public class FirstParse {
	private PDFParser parser;
	   private PDFTextStripper pdfStripper;
	   private PDDocument pdDoc ;
	   private COSDocument cosDoc ;
	  
	   private String Text ;
	   private String filePath;
	   private File file;
	 
	    public FirstParse() 
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
	       // to read all the text:
	       pdfStripper.setEndPage(pdDoc.getNumberOfPages());
	       Text = pdfStripper.getText(pdDoc);
	       
	       //for not including the extra text at the start and end of the file:
	       int start = Text.indexOf("Computing Instance");
	       int end = Text.lastIndexOf("Powered by TCPDF");
	       Text = Text.substring(start,end);
	       
	       //quantity row doesn't have proper delimiters,inserting $ after every number
	       //except the last:
	       int index = Text.indexOf("Quantity");
	       //length of quantity + 1 blank space
	       index = index + 9;
	       int i;//loop index
	       //converting to stringbuilder to use the insert function
	       StringBuilder t = new StringBuilder(Text);
	       for(i=0;i<4;i++)
	       {
	    	   t.insert(index, " $");
	    	   index = index + 4;
	       }
	       
	       //replacing $ by :
	       String str = t.toString();//converting back to a string
	       Text = str.replace("$", " : ");
	       return Text;
	   }
	 
	    public void setFilePath(String filePath) {
	        this.filePath = filePath;
	    }
		
	  
	}
	