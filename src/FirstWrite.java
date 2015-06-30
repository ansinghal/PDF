

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
	
	public void write(String outputPath)
	{
	try{
	String first = null ;//the first column
	String second = null;//the second column
	String f = null;//f will contain the text from the first column and a comma as a delimiter
	String s = null;//s will contain the text from the second column and a comma as a delimiter
    String c=  null;//c will contain test cost+first column and comma as delimiter
    String m= null ;
	String cost = null;
    int i=1 ;
	DataInputStream in = new DataInputStream(fstream);
	BufferedReader br = new BufferedReader(new InputStreamReader(in));
	BufferedWriter out = new BufferedWriter(new FileWriter(outputPath));
	String strLine;
	String datacenter=null;
	String namedatacenter=null;
	int flag = 0;
	//Read File Line By Line
	while ((strLine = br.readLine()) != null)
	{
		//System.out.println(f);
		String[] delims = strLine.split(":");
		first = delims[0] ;//column names
		if(first.equals("NEXT"))
		{
			System.out.print("\n***********entered Next*********\n");
			if(flag==1)
				{
					out.newLine();
				}
			else
			{
				flag = 1;
			}
			out.write(datacenter);
			out.write(f);
			out.write(";");
			out.write(c);
			out.newLine();
			out.write(namedatacenter);
			out.write(s);
			out.write(";") ;
			out.write(m);
			f = null;
			i = 1;
			continue;
		}
		if(first.isEmpty())
		{
			continue;
		}
		if (first.equals("   Datacenter"))
		{
			datacenter = "Datacenter;";
			namedatacenter=delims[1]+";";
			continue;
		}
		
		second = delims[1];//quantity of the items
		cost = delims[2];//cost of items
		first=first.replace(" ","");//replace spaces with no spaces to remove extra whitespaces.
		//first=first.replace("&", "and");


	   //System.out.println("First word: "+first);
       count++ ;
        i++ ;//i = 2
        //if the value of i is 2;then just initialize f and s to be first and second
        if (i==2)
        {
        	f=first;
        	c=first + "cost";
        	s=second;
        	m=cost ;
        }
        else if(first.equals("Subtotals") || first.equals("Quantity") || 
        		first.equals("NewSubtotals"))
        {
        	c = c+";"+first;
        	//s = s + ";" + second;
            m = m + ";"+cost ;
        }
        else
        {
          
        	f = f+";"+first;
        	c= c+";"+first+"cost";
            s = s + ";" + second;
            m = m + ";"+cost ;
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


