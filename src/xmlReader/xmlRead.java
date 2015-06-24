package xmlReader;
import java.util.*;
import java.io.*;
import java.util.LinkedList;

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
								  	//Displays with each iteration values where i=quote no j=title pair k=value/title
								  	//System.out.println("\n\ni="+i+" j = "+j+" k="+k);
								  	//System.out.println("attr name : " + node.getNodeName());
									//System.out.println("attr value : " + node.getNodeValue());
									
									if(k==1)
									{
										matrix[i*2][j]=node.getNodeValue();
										//System.out.println("k=1;attr name : " + node.getNodeValue());
										//System.out.println(matrix[i*2][j]+"\t"+i*2+"\t"+j);
									}
									if(k==2)
									{
										matrix[i*2+1][j]=node.getNodeValue();
										//System.out.println(matrix[i*2+1][j]+"\t"+(i*2+1)+"\t"+j);
									}
								}
							  
						  }
						  
					  }
					  //Node k = kids.item(0);
					  //System.out.println(kids.getLength());
					  i++;
					  
				  }
				  printMatrix(matrix);
				  createLinkedList(matrix);
		
	}

			  private static void createLinkedList(String[][] matrix){
				  LinkedList<String> l=new LinkedList<String>();
					int row = 0;
					int col = 0;
					System.out.println("matrix length:"+matrix.length);
					int m = maxColumnSize(matrix);
					System.out.println("Max size of columns is:"+m);
					for(col=0;col<m;col++)
					{
						System.out.println("column="+col);
						for(row=0;row<(matrix.length)-1;row++)
						{	
							System.out.println("row="+row);
							if(row%2==0)
							{
								if(l.contains(matrix[row][col]))
								{
									System.out.println("Contains:"+matrix[row][col]);
									continue;
								}										
								else
								{
									System.out.println("Added:"+matrix[row][col]);
									l.add(matrix[row][col]);
								}
							}
						}
						
						
					
					}
					//rearrange the linkedList:
					l=reArrange(l);
					//printing the linkedlist:
					//System.out.println("   "+l.size());
					int i = 0;
					for(i=0;i<l.size();i++)
					{
						System.out.println("List l:"+" "+i+" "+l.get(i));
					}  
					
				}

			private static LinkedList<String> reArrange(LinkedList<String> l)
			{
				//Iterator<String> itr = l.iterator();
				LinkedList<String> nl = new LinkedList<String>();
				int i = 0;
				/*for(i=0;i<l.size();i++)
				{
					System.out.println("List l:"+" "+i+" "+l.get(i));
				}*/
				for(i=0;i<l.size();i++)
				{
					String str = l.get(i);
					if(str.contains("cost"))
						continue;
					else
					{
						if(str.equals("Subtotals") || str.equals("Quantity") || str.equals("NewSubtotals"))
							break;
						//System.out.println("Adding:"+str);
						nl.add(str);
					}
			
					
				}
				//itr = l.iterator();
				//System.out.println("ahahahahahha");
				for(i=0;i<l.size();i++)
				{
					String str = l.get(i);
					if(str.equals("Subtotals") || str.equals("Quantity") || str.equals("NewSubtotals"))
						break;
					//System.out.println("String:"+str);
					if(str.contains("cost"))
					{
						nl.add(str);
						//System.out.println("Adding:"+str);
					}
				}
				nl.add("Subtotals");
				nl.add("Quantity");
				nl.add("NewSubtotals");
				// TODO Auto-generated method stub
				System.out.println("The size of l is:"+l.size()+"\nThe size of nl is:"+nl.size());
				return nl;
			}

			private static int maxColumnSize(String[][] matrix) {
				// ma
				int row = 0;
				int max = -1;
				for(row=0;row<matrix.length;row++)
				{
					int count = 0;
					while(matrix[row][count]!=null)
					{
						count++;
					}
					if(count>max)
						max = count;
				}
				return max;
			}

			private static void printMatrix(String[][] matrix) 
			
			{
				for(int i = 0; i<matrix.length; i++)
				{    
					int j=0;
				    while(matrix[i][j]!=null)
				    {
				        System.out.print(matrix[i][j]+",");
				        j++;
				    }
				    System.out.println();
				}
						
				
				
			}
			
}



