import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class csv2excel {
	
	public static void convert(String csvFileAddress,String xlsxFileAddress){
		try {
	        //String csvFileAddress = "C:\\Users\\IBM_ADMIN\\Desktop\\project\\output\\fwrite.csv"; //csv file address
	        //String xlsxFileAddress = "C:\\Users\\IBM_ADMIN\\Desktop\\project\\output\\fwrite.xlsx"; //xlsx file address
	        XSSFWorkbook workBook = new XSSFWorkbook();
	        XSSFSheet sheet = workBook.createSheet("sheet1");
	        String currentLine=null;
	        int RowNum=0;
	        BufferedReader br = new BufferedReader(new FileReader(csvFileAddress));
	        while ((currentLine = br.readLine()) != null) {
	            String str[] = currentLine.split(",");
	            RowNum++;
	            XSSFRow currentRow=sheet.createRow(RowNum);
	            for(int i=0;i<str.length;i++){
	                currentRow.createCell(i).setCellValue(str[i]);
	                }
	        }
	        int i = 0;
	        for(i=0;i<100;i++)
	        {
	        	sheet.autoSizeColumn(i);
	        }
	        
	        FileOutputStream fileOutputStream =  new FileOutputStream(xlsxFileAddress);
	        workBook.write(fileOutputStream);
	        fileOutputStream.close();
	        workBook.close();
	        br.close();
	        System.out.println("Done converting to excel file");
	    } 
		catch (Exception ex) 
	    {
	        System.out.println(ex.getMessage()+"Exception in try");
	    }
	}

}
