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
				//System.out.println(((Element)((Element)doc.getElementsByTagName("Quote").item(0)).getChildNodes().item(0)));
				System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			 
				if (doc.hasChildNodes()) {
					 NodeList n = doc.getElementsByTagName("Quote");
					 int numQuotes = doc.getElementsByTagName("Quote").getLength();
					 /*Working:
					  * NamedNodeMap np = ((Element) n.item(0)).getElementsByTagName("Element").item(0).getAttributes();
					  
					 System.out.println(np.item(2).getNodeValue());
					*/
					// System.out.println(doc.getElementsByTagName("Quote").item(0).getAttribute("id"));
					String[][] matrix = new String[numQuotes*2][200];
					populateMatrix(matrix,n,numQuotes);
					 //printNote(doc.getChildNodes());
					
			 
				}
			 
			    } catch (Exception e) {
				System.out.println(e.getMessage());
			    }
			 
			  }
			 
			  private static void populateMatrix(String[][] matrix,NodeList n,int count) {
		// TODO Auto-generated method stub
				  int i = 0;
				  while(i < count)
				  {
					  NodeList kids = ((Element) n.item(i)).getElementsByTagName("Element");
					  System.out.println("Length of kids is ="+kids.getLength());
					  //System.out.println(kids.item(1).getAttributes().item(2).getNodeValue());
					  int j;
					  for(j = 0;j<kids.getLength();j++){
						  if(kids.item(j).hasAttributes())
						  {
							  NamedNodeMap nodeMap = kids.item(j).getAttributes();
							  for (int k = 1; k < nodeMap.getLength(); k++) {
									//good place to check if hidden == 0;change k = 0 for hidden nodes
								  	Node node = nodeMap.item(k);
									System.out.println("attr name : " + node.getNodeName());
									System.out.println("attr value : " + node.getNodeValue());
									System.out.println("i="+i+"j="+j+"k="+k);
									if(k==1)
										matrix[i][j]=node.getNodeName();
									if(k==2)
										matrix[i+1][j]=node.getNodeValue();
								}
							  
						  }
						  
					  }
					  //Node k = kids.item(0);
					  //System.out.println(kids.getLength());
					  i++;
					  
				  }
				  printMatrix(matrix);
		
	}

			private static void printMatrix(String[][] matrix) {
				// TODO Auto-generated method stub
				
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



