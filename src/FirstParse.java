
/*
 * This class contains the function that reads the pdf file and converts it into a text file that:
 * i)has trimmed all the irrelevant information.
 * ii)corrects and replaces the text in files($ for  so that : can be used as a delimiter.
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
	       int indices[] = new int[100];
	       //to get total number of price quotes in the given pdf:
	       int count = getNumberQuotes(Text,indices);
	       //System.out.println("total number of price quotes is:"+count);


	       //for not including the extra text at the start and end of the file:
	       int description = Text.indexOf("Description");
	       if(description == -1)
	       {
	    	   System.out.println("Keyword Description is not found");
	       }

	       int s = Text.indexOf("Computing Instance:");
	       int start = Text.indexOf("Server:");
	       if(start == -1 && s==-1)
	       {
	    	   System.out.println("Error!!");
	    	   System.exit(-1);
	       }
	       else if(start==-1)
	       {
	    	   start = s;
	       }
	       if(start > s && s>0)
	       {
	    	   start  =  s;
	       }
	       indices[0]=start;


	      // int end = Text.lastIndexOf("New Subtotals");
	       int j=0;
	       String Text1=null;
	       String Finish=null;
	       do
	    	   {
	    	   		//System.out.println("\n\nend="+indices[j]);
	    	   		if(j!=0)
	    	   			start = indices[j]+45;
	    	   		start = indices[j];
	    	   		int ends = indices[j+1];
	    	   		//System.out.println("start="+start+"\nends="+ends);
	    	   		Text1 = Text.substring(start,ends);
	    	   		//System.out.print("\nStart="+start+"  End"+ends);
	    	   		//to remove line of texts which come up when a price quote continues over 2 different pages:
	    	   		Text1=removeGarbageData(Text1);
	    	   		//System.out.println(Text1);

			       //quantity row doesn't have proper delimiters,inserting $ after every number
			       //except the last:
			       int index = Text1.indexOf("Quantity");
			       //length of quantity + 1 blank space
			       index = index + 9;
			       int i;//loop index
			       //converting to stringbuilder to use the insert function
			       StringBuilder t = new StringBuilder(Text1); 
			       for(i=0;i<4;i++)
				       {
				    	   t.insert(index, " $");
				    	   index = index + 4;
				       }

	       //replacing $ by :
	       Text1 = t.toString();//converting back to a string
	       Text1 = Text1.replace("$", " : ");
	       if (j==0)  // TO REMOVE NULL 
	    	   Finish=Text1;
	       else
	       Finish= Finish + Text1;
	       j++;
	    	   }
	       while (j < count);


	       cosDoc.close();

	       Finish = writeNext(Finish);	       
	       return Finish;
	   }




	   private String writeNext(String str) {
		   int server = str.indexOf("Server:");
		   int comp = str.indexOf("Instance:");
		   //System.out.printf("Server="+server+"Comp="+comp);
		   if(comp<server && comp>-1)
		   {
			   StringBuffer s = new StringBuffer(str);
			   //System.out.println("writing in comp<server comparison");
			   s.insert(server, "\nNEXT:\n");
			   server = server + "\nNEXT:\n".length();
			   comp = comp + "\nNEXT:\n".length();
			   str = s.toString();
		   }

		   if(server < comp && server>-1)
		   {
			   StringBuffer s = new StringBuffer(str);
			  // System.out.println("writing in server>comp comparison");
			   s.insert(comp-"Computation".length(), "\nNEXT:\n");
			   server = server + "\nNEXT:\n".length();
			   comp = comp + "\nNEXT:\n".length();
			   str = s.toString();
		   }
		   while(server != -1)
		   {
			   server = str.indexOf("Server:",server + "server:".length());
			   if(server==-1)
			   {
				   break;
			   }
			   StringBuffer s = new StringBuffer(str);
			   s.insert(server, "\nNEXT:\n");
			   server = server + "\nNEXT:\n".length();
			   comp = comp + "\nNEXT:\n".length();
			   str = s.toString();
		   }
		   while(comp !=-1)
		   {
			   comp = str.indexOf("Instance:",comp+"Instance:".length());
			   if(comp==-1)
			   {
				   break;
			   }
			   StringBuffer s = new StringBuffer(str);
			   s.insert(comp-"Computation".length(), "\nNEXT:\n");
			   server = server + "\nNEXT:\n".length();
			   comp = comp + "\nNEXT:\n".length();
			   str=s.toString();
		   }
		return str;
	}


	public void setFilePath(String filePath) {
	        this.filePath = filePath;
	    }
	    //remove text which comes up when a price quote is broken over 2 different pages:
	    public String removeGarbageData(String str)
	    {
	    	int i = str.indexOf("Proposal continues on next page.");
	    	if(i>=0)
	    	{
	    		//One-timetax will always come at the end of the text so:
	    		int j = str.indexOf("Tax",i+1);
	    		j = str.indexOf("Tax",j+1);
	    		j = j+"Tax".length()+2;
	    		StringBuffer s = new StringBuffer(str);
	    		//System.out.println("i="+i+" j="+j);
	    		s.delete(i,j);
	    		str = s.toString();
	    		return str;
	    	}
	     	else{
	    		return str;
	    	}
	    }

	    public int getNumberQuotes(String str,int[] indices)
	    {
	    	 int count = 0;
		       int j = Text.indexOf("New Subtotals");
		       //System.out.println(j);
		       if(j==-1)
		       {
		    	   System.exit(-1);
		       }
		      int c=1;
		      indices[0] = 0;
		       while(j >= 0) 
		       {
		    	    //System.out.println("index of new subtotal = "+j);
		    	    indices[c]=j;
		    	    //System.out.print("\nc= "+c+" "+indices[c]);
		    	    j = Text.indexOf("New Subtotals", j+1);
		            c++;
		            count ++;
		       }

		       //for getting the text after the final new subtotals:
		       indices[c-1]=Text.indexOf("Order Subtotals");

		       //for getting order subtotals:
		       //indices[c]=Text.indexOf("Total Recurring");

		       return count;
	    }

}