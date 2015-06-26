package xmlReader;

import java.io.*;

public class csvWriter {
	
	public csvWriter(String[][] matrix,String output)
	{
		try
		{
			BufferedWriter out = new BufferedWriter(new FileWriter(output));
			int row = 0;
			int col = 0;;
			for(row = 0;row<matrix.length;row++)
			{
				col = 0;
				while(matrix[row][col]!=null)
				{
				//	System.out.println("writing:"+matrix[row][col]);
					out.write(matrix[row][col]);
					out.write(";");
					col++;
				}
				out.write("\n");
			}
			out.close();
		}
		catch(IOException e)
		{
			System.out.println("Unable to create output file");
		}
	}

}
