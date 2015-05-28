/**
 * This function generates the csv file from the text file using : as a delimiter.It removes all
 * the blanks(spaces) from the column names.
 * input:
 * text file generated by PDFManager.java
 * output: 
 * csv file that contains the columns seperated by commas
 *
 */
import java.io.*;


public class FirstWrite{
	static FileInputStream fstream;
	static int count=0 ;//count*2 will the number of columns
	
	public FirstWrite(){
		System.out.println("Input file name not given");
	}
	
	public FirstWrite(File f) throws FileNotFoundException
	{
		fstream = new FileInputStream(f);
	} 
	
	public void write()
	{
	try{
	String first = null ;//the first column
	String second = null;//the second column
	String f = null;//f will contain the text from the first column and a comma as a delimiter
	String s = null;//s will contain the text from the second column and a comma as a delimiter
    String c=  null;//c will contain test cost+first column and comma as delimiter
	String cost = null;
    int i=1 ;
	//FileInputStream fstream = new FileInputStream("C:\\Users\\IBM_ADMIN\\Desktop\\project\\output\\test.txt");
	// Get the object of DataInputStream
	DataInputStream in = new DataInputStream(fstream);
	BufferedReader br = new BufferedReader(new InputStreamReader(in));
	BufferedWriter out = new BufferedWriter(new FileWriter("C:\\Users\\IBM_ADMIN\\Desktop\\project\\output\\fwrite.csv"));
	String strLine;
	//Read File Line By Line
	while ((strLine = br.readLine()) != null) {
	String[] delims = strLine.split(":");
	first = delims[0] ;//column names
	if (first.equals("   Datacenter"))
			{
				continue;
			}
	second = delims[1];//quantity of the items
	cost = delims[2];//cost of items
	first=first.replace(" ","");//replace spaces with no spaces to remove extra whitespaces.
	
	//first line is blank:
	if (i==1) {
		i++ ;//i = 2
	}
	else {
			   //System.out.println("First word: "+first);
		       count++ ;
		        i++ ;//i = 3
		//if the value of i is 3;then just initialize f and s to be first and second
		        if (i==3)
		        {
		        	f=first;
		        	c=first + "(cost)";
		        	s=second;
		        }
		        else
		        {   
		        	if (first.equals("Subtotals"))
		               {
		        		out.write(f);
		        		out.write(",");
		        		out.write(c);
		        		out.newLine();
		        		out.write(s);
		               }
		        
		        	f = f+","+first;
		        	c= c+","+first+"(cost)";
		            s = s + "," + second;
		        }
		
	}
	}
	
	// writing f and s to a new file
	out.close();
	System.out.println(count*2) ;//prints the number of columns to the console
	in.close();
	
	}
	catch (Exception e)
	{//Catch exception if any
		System.err.println("Error: " + e.getMessage());
	}
	
	
}
}


