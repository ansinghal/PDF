/* Generate Xml properties file
 */

package xmlGeneration;
import java.io.*;

public class GenerateXml {

	public static void main(String[] args)
	{
		String path = "C:\\Users\\IBM_ADMIN\\Desktop\\Project\\output\\test.csv";
		String currentLine=null;
		try{
			BufferedReader br = new BufferedReader(new FileReader(path));
			 while ((currentLine = br.readLine()) != null)
			 {
		            String str[] = currentLine.split(",");
		            Insert2Xml.insert(str);
			 }
			br.close();
		}
		catch(FileNotFoundException ex)
		{
			System.out.println("File not found");
		}
		catch(IOException ex)
		{
			System.out.println("IOexception encountered");
		}
		
		
		
		
		
	}

}
