package xmlGenerator;


import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.util.StringTokenizer;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
 

public class XMLCreators {
    // Protected Properties

    protected DocumentBuilderFactory domFactory = null;
    protected DocumentBuilder domBuilder = null;

    public XMLCreators() {
        try {
            domFactory = DocumentBuilderFactory.newInstance();
            domBuilder = domFactory.newDocumentBuilder();
        } catch (FactoryConfigurationError exp) {
            System.err.println(exp.toString());
        } catch (ParserConfigurationException exp) {
            System.err.println(exp.toString());
        } catch (Exception exp) {
            System.err.println(exp.toString());
        }

    }

    public int convertFile(String csvFileName, String xmlFileName,
                    String delimiter) {

        int rowsCount = -1;
        try {
            Document newDoc = domBuilder.newDocument();
            // Root element
            Element rootElement = newDoc.createElement("XMLCreators");
            newDoc.appendChild(rootElement);
            // Read csv file
            BufferedReader csvReader;
            csvReader = new BufferedReader(new FileReader(csvFileName));

            int line = 0;
            List<String> headers = new ArrayList<String>(5);

            String text = null;
            int i =0;
            while ((text = csvReader.readLine()) != null) 
            {

               // System.out.println("LINE:"+line);
            	String[] st = text.split(",");
              //number of comma seperated values in this line of text:
                String[] rowValues = new String[st.length];
                int index = 0;
                int len = 0;
                while (len<st.length) {

                    String next = st[len];
                   // System.out.println("next:"+next);
                    rowValues[index++] = next;
                    len++;

                }

                if (line%2 == 0) { // Header row
                	headers.clear();
                	//System.out.println("entered headers for line:"+line);
                	i++;
                    for (String col : rowValues) {
                         	headers.add(col);
                         	//System.out.println("headers:"+headers);
                    	
                    }

                } else { // Data row

                    rowsCount++;

                    Element rowElement = newDoc.createElement("Quote");
                    rowElement.setAttribute("id", Integer.toString(i));
                    //Element rowElement= newDoc.createElement("Title") ;
                    rootElement.appendChild(rowElement);
                    //rowElement1.appendChild(rowElement);
                    
                   
                    
                    /*The following code is used to access the 
                      config file and throw a file not found exception,
                       in the for loop that follows a method searches this file 
                       and aasign 0 or 1 to the hidden field. bedefault if the header 
                       is missing gin the file it assigns 1 */
                    
                    Properties prop = new Properties();
                	String propFileName = "titlesConfig.properties";
                	InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
                	if (inputStream != null) {
                		prop.load(inputStream);
                		} else {
                		throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
                		}
                    
                   
                	
                	 String[] special = new String[200];
                	for (int col = 0; col < headers.size(); col++)
                    {

                        String header = headers.get(col);
                        String value = null;

                        if (col < rowValues.length && rowValues[col] != "")
                        {

                            value = rowValues[col];

                        } 
                        else 
                        {
                            // ?? Default value
                            value = "NA";
                        }

                        Element curElement = newDoc.createElement("Element");
                       
                       
                        if((header.trim()).equals("IntrusionDetection&Protection"))
                        { special[1]=value;  }
                        
                        	
                        	
                        		String titval = (prop.getProperty(header.replace(" ","")));
                        		//System.out.println(titval==null);
                        		String [] titlevalue = new String[3] ;
                        		
                        		if (titval==null)
                        		{
                        			                        		
                        			titlevalue[0]=header;
                        			titlevalue[1]="1";
                        		}
                        		else
                        		{ titlevalue=titval.split(",");}
                        		
                        		
                        		
   
                        	
                        		 if ((header.trim()).equals("IntrusionDetection&Protectionprice"))
                                 { curElement.setAttribute("title", special[1]+" price"); }
                        		 else 
                        { curElement.setAttribute("title", titlevalue[0]);}
                        /*Element title = newDoc.createElement("title");
                        title.appendChild(newDoc.createTextNode(header));
                        curElement.appendChild(title);*/
                       
                        		 System.out.println("Header::"+ header );
                        curElement.setAttribute("order", titlevalue[2]);
                        		 
                        		 
                        		 
                        curElement.setAttribute("value", value);
                        /*Element valuetag = newDoc.createElement("value");
                        valuetag.appendChild(newDoc.createTextNode(value));
                        curElement.appendChild(valuetag);*/
                        
                        
                        /* if(hiddenVal==null)
                         {String  head=header.replace("price","");
                           hiddenVal = prop.getProperty(head);
                          if (hiddenVal==null)
                          { System.out.println(header + "\t  Please add this to config file and read again");
                            hiddenVal="1";
                          }
                         
                         }*/
                        curElement.setAttribute("hidden", titlevalue[1]);
                        /*Element hidden = newDoc.createElement("hidden");
                        hidden.appendChild(newDoc.createTextNode("1"));
                        curElement.appendChild(hidden);*/
                        
                        rowElement.appendChild(curElement);

                    }

                }
                line++;

            }

            ByteArrayOutputStream baos = null;
            OutputStreamWriter osw = null;

            try {

                baos = new ByteArrayOutputStream();
                osw = new OutputStreamWriter(baos);

                TransformerFactory tranFactory = TransformerFactory.newInstance();
                Transformer aTransformer = tranFactory.newTransformer();
                //for formatiing:
                aTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
                aTransformer.setOutputProperty(OutputKeys.METHOD, "xml");
                aTransformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

                Source src = new DOMSource(newDoc);
                Result result = new StreamResult(osw);
                aTransformer.transform(src, result);

                osw.flush();
                //write to xmlPathFile:
                BufferedWriter out = new BufferedWriter(new FileWriter(xmlFileName));
                out.write(baos.toString());
                out.close();
                csvReader.close();

            } catch (Exception exp) {
                exp.printStackTrace();
            } finally {
                try {
                    osw.close();
                } catch (Exception e) {
                }
                try {
                    baos.close();
                } catch (Exception e) {
                }
            }

            } catch (IOException exp) {
            System.err.println(exp.toString());
        } catch (Exception exp) {
            System.err.println(exp.toString());
        }
        return rowsCount;
        
        // "XLM Document has been created" + rowsCount;
    }
}
