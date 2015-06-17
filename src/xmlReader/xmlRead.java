package xmlReader;

import java.io.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.pdfbox.util.Matrix;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
 


public class xmlRead 
{

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		 try {
			 
				File file = new File("C:\\Users\\IBM_ADMIN\\Desktop\\Project\\output\\test.xml");
				File output = new File("C:\\Users\\IBM_ADMIN\\Desktop\\Project\\output\\final.csv");
				DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance()
			                             .newDocumentBuilder();
			 
				Document doc = dBuilder.parse(file);
			 
				System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			 
				if (doc.hasChildNodes()) {
					 NodeList d = doc.getChildNodes();
					 //int numQuotes = ((Document) d).getElementsByTagName("Quote").getLength();
					 System.out.println(d.item(0).getChildNodes().item(2).getNodeName());
					// String[][] matrix = new String[numQuotes*2][200];
					// populateMatrix(matrix,((Document)d),numQuotes);
					 //printNote(doc.getChildNodes());
					
			 
				}
			 
			    } catch (Exception e) {
				System.out.println(e.getMessage());
			    }
			 
			  }
			 
			  private static void populateMatrix(String[][] matrix,Document d,int count) {
		// TODO Auto-generated method stub
				  int i = 0;
				  while(i < count)
				  {
					  //Node tempNode = d.
				  }
		
	}

			private static void printNote(NodeList nodeList) {
				  
				 
			 
			    for (int count = 0; count < nodeList.getLength(); count++) {
			    Node tempNode = nodeList.item(count);
			 
				// make sure it's element node.
				if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
					String name = tempNode.getNodeName();
					// get node name and value
					System.out.println("\nNode Name =" + name + " [OPEN]");
					
					//System.out.println("Node Value =" + tempNode.getTextContent());
			 
					if (tempNode.hasAttributes()) {
			 
						// get attributes names and values
						NamedNodeMap nodeMap = tempNode.getAttributes();
			 
						for (int i = 0; i < nodeMap.getLength(); i++) {
			 
							Node node = nodeMap.item(i);
							System.out.println("attr name : " + node.getNodeName());
							System.out.println("attr value : " + node.getNodeValue());
			 
						}
			 
					}
			 
					if (tempNode.hasChildNodes()) {
			 
						// loop again if has child nodes
						printNote(tempNode.getChildNodes());
			 
					}
			 
					System.out.println("Node Name =" + tempNode.getNodeName() + " [CLOSE]");
								 
				}
			 
			}
			 
	}
			 
			
}



