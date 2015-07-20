package xmlReader;
import xmlGenerator.XMLCreators;
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
	public xmlRead(String xmlPath,String output)
	{
		// TODO Auto-generated method stub
		 try {
			 	File file = new File(xmlPath);
				DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance()
			                             .newDocumentBuilder();
			 
				Document doc = dBuilder.parse(file);
				//System.out.println(((Element)((Element)doc.getElementsByTagName("Quote").item(0)).getChildNodes().item(0)));
				//System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			 
				if (doc.hasChildNodes()) {
					 NodeList n = doc.getElementsByTagName("Quote");
					 int numQuotes = doc.getElementsByTagName("Quote").getLength();
					 /*Working:
					  * NamedNodeMap np = ((Element) n.item(0)).getElementsByTagName("Element").item(0).getAttributes();
					  
					 System.out.println(np.item(2).getNodeValue());
					*/
					// System.out.println(doc.getElementsByTagName("Quote").item(0).getAttribute("id"));
					String[][] matrix = new String[numQuotes*2][200];
					populateMatrix(matrix,n,numQuotes,output);
					 //printNote(doc.getChildNodes());
					
			 
				}
			 
			    } catch (Exception e) {
			    	//System.out.println("Config file corrupted");
			    	e.printStackTrace();
			    }
			 
			  }
			 
			private void populateMatrix(String[][] matrix,NodeList n,int count,String output) {
		// TODO Auto-generated method stub
				  int i = 0;
				  int[][] arr = new int[count*2][1000];
				  int[][] order = new int[count*2][1000];
				   while(i < count)
				  {
					  NodeList kids = ((Element) n.item(i)).getElementsByTagName("Element");
					  //System.out.println("Length of kids is ="+kids.getLength());
					  //System.out.println(kids.item(1).getAttributes().item(2).getNodeValue());
					  int j;
					  for(j = 0;j<kids.getLength();j++){
						  if(kids.item(j).hasAttributes())
						  {
							  NamedNodeMap nodeMap = kids.item(j).getAttributes();
							  for (int k = 0; k < nodeMap.getLength(); k++) {
									//good place to check if hidden == 0;change k = 0 for hidden nodes
								    	Node node = nodeMap.item(k);
								  	//Displays with each iteration values where i=quote no j=title pair k=value/title
								  	//System.out.println("\n\ni="+i+" j = "+j+" k="+k);
								  	//System.out.println("attr name : " + node.getNodeName());
								  	//System.out.println("attr value : " + node.getNodeValue());
									//int flag = 0;
									
								  	//hidden
								  	if(k==0)
									{
										if(Integer.parseInt(node.getNodeValue().trim())==0)
										{
											arr[i*2][j]=0;
											//System.out.println("setting to 1 because hidden = 0 "+"\tj="+j);
										}
										else
											arr[i*2][j]=1;
									}
								  	
								  	//order
								  	if(k==1)
								  	{
								  		//System.out.println("attr name : " + node.getNodeName());
								  		//System.out.println(node.getNodeValue().trim());
								  		order[i*2][j] = Integer.parseInt(node.getNodeValue().trim());
								  	}
								  	
								  	
								  	//title
									if(k==2)
									{
										matrix[i*2][j]=node.getNodeValue();
										///System.out.println("attr name : " + node.getNodeValue());
										//System.out.println(matrix[i*2][j]+"\t"+i*2+"\t"+(j));
									}
									
									//value
									if(k==3)
									{
										matrix[i*2+1][j]=node.getNodeValue();
										//System.out.println(matrix[i*2+1][j]+"\t"+(i*2+1)+"\t"+(j));
									}
								}
							  
						  }
						  
					  }
					  //Node k = kids.item(0);
					  //System.out.println(kids.getLength());
					  i++;
					  
				  }
				  // printMatrix(matrix);
				  // System.out.println(matrix[14][21]);

				   createLinkedList(matrix,output,arr,order);
		
	}

			private void createLinkedList(String[][] matrix,String output,int[][] arr,int[][] order){
				  LinkedList<String> l=new LinkedList<String>();
					int row = 0;
					int col = 0;
					//System.out.println("matrix length:"+matrix.length);
					int m = maxColumnSize(matrix);
					//System.out.println("Max size of columns is:"+m);
					for(col=0;col<m;col++)
					{
						//System.out.println("column="+col);
						
						for(row=0;row<(matrix.length);row++)
						{	
							//System.out.println("row="+row);
							//System.out.println("arr["+row+"]["+col+"]:"+arr[row][col]);
							if(arr[row][col]==1)
							{

								if(row%2==0)
								{
									if(l.contains(matrix[row][col]))
									{
										//System.out.println("Contains:"+matrix[row][col]);
										continue;
									}										
									else
									{
										//System.out.println("Added:"+matrix[row][col]);
										l.add(matrix[row][col]);
									}
								}
							}
						}
							
						
					
					}
					//rearrange the linkedList:
					try{
						l=reArrange(l,order,matrix);
					}
					catch(IOException e){
						e.printStackTrace();
					}
					//printing the linkedlist:
					int i = 0;
					for(i=0;i<l.size();i++)
					{
					//System.out.println("List l:"+" "+i+" "+l.get(i));
					}
					try{
					reduceMatrix(l,matrix,output);
					}
					catch(IOException e)
					{
						System.out.println("ConfigFile not found while writing csv");
					}
				}

			private void reduceMatrix(LinkedList<String> l,String[][] matrix,String output) throws IOException
			{
				// generate the final form of matrix which has the first row as header;and rest as values.
				int i = 0;
				String finalMatrix[][] = new String[matrix.length/2+1][200];
				int flag = 0;
				
				 Properties prop = new Properties();
             	String propFileName = "titlesConfig.properties";
             	InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
             	if (inputStream != null) {
             		prop.load(inputStream);
             		} else {
             		throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
             		}
             	//merging computing instance and server
             	String Ci = (prop.getProperty("ComputingInstance"));
             	String ci = null;
             	if(Ci==null)
             	{
             		ci = "ComputingInstance";             		
             	}
             	else
             	{
             		ci = Ci.split(",")[0];
             	}
             	
                String Serv = (prop.getProperty("Server"));
                String serv = null;
                if(Serv==null)
                {
                	serv="Server";
                }
                else
                {
                	serv = Serv.split(",")[0];
                }
				if(l.contains(serv) && l.contains(ci))
					{
						flag = 1;
						l.remove(ci);
					}
				
				//merging computing instance price and server price
				
				String CiPrice = (prop.getProperty("ComputingInstanceprice"));
             	String ciPrice = null;
             	if(CiPrice==null)
             	{
             		ciPrice = "ComputingInstanceprice";             		
             	}
             	else
             	{
             		ciPrice = CiPrice.split(",")[0];
             	}
             	
                String ServPrice = (prop.getProperty("Serverprice"));
                String servPrice = null;
                if(ServPrice==null)
                {
                	servPrice="Server";
                }
                else
                {
                	servPrice = ServPrice.split(",")[0];
                }
                int flagPrice = 0;
                if(l.contains(servPrice) && l.contains(ciPrice))
				{
					flagPrice = 1;
					l.remove(ciPrice);
				}
								
				for(i=0;i<l.size();i++)
				{
					String head  = l.get(i);
					//System.out.println("finalmatrix[0]["+i+"]="+head);
					finalMatrix[0][i]=head;
					int row = 0;
					for(row=0;row<matrix.length;row++)
					{
						if(row%2==0)
							{
								String val = findInMatrix(matrix,head,row);
								if(flag == 1)
								{
									if(head.equals(serv))
									{
										val = findInMatrix(matrix,serv,row);
										if(val.equals("NA"))
											val = findInMatrix(matrix,ci,row);
										finalMatrix[row/2+1][i] = val;
									}
									else
									{
										finalMatrix[row/2+1][i] = val;
									}															
								}
								if(flagPrice==1)
								{
									if(head.equals(servPrice))
									{
										val = findInMatrix(matrix,servPrice,row);
										if(val.equals("0.00"))
											val = findInMatrix(matrix,ciPrice,row);
										finalMatrix[row/2+1][i] = val;
									}
									else
									{
										finalMatrix[row/2+1][i] = val;
									}															
								}	
								finalMatrix[row/2+1][i] = val;
								//System.out.println("head:"+head+" row:"+row+" i:"+i+" finalMatrix[row/2+1][i]:"+val);
							}
					}
					
				}
				//printMatrix(finalMatrix);
				int j = 0;
				i=0;
				//renaming the datacenter:
				String Dc = prop.getProperty("Datacenter");
				String dc = null;
				if(Dc==null)
				{
					dc = "Datacenter";
				}
				else
				{
					dc = Dc.split(",")[0];
				}
				for(j= 0;j<l.size();j++)
				{
					//System.out.println("checking for match:"+dc+"with"+matrix[i][j]);
					if(dc.equals(finalMatrix[i][j]))
					{
						int row = 0;
						for(row=1;row<finalMatrix.length;row++)
						{
							int len = finalMatrix[row][j].length();
							//System.out.println("rename:"+finalMatrix[row][j]);
							finalMatrix[row][j] = finalMatrix[row][j].substring(len-6, len-1);
							//System.out.println("renamed as:"+finalMatrix[row][j]);
						}
						
					}
				}
				//antivirus yes or no:
				String Av = prop.getProperty("Anti-Virus&SpywareProtection");
				String av = null;
				if(Av==null)
					av = "Anti-Virus & Spyware Protection";
				else
					av = Av.split(",")[0];
				for(j= 0;j<l.size();j++)
				{
					
					if(av.equals(finalMatrix[i][j]))
					{
						int row = 0;
						for(row=1;row<finalMatrix.length;row++)
						{
							if(finalMatrix[row][j]=="NA")
								finalMatrix[row][j]="No";
							else
								finalMatrix[row][j]="Yes";
						}
						
					}
				}
				//for advanced monitoring
				String Am = prop.getProperty("AdvancedMonitoring");
				String am = null;
				if(Am == null)
					am = "AdvancedMonitoring";
				else
					am = Am.split(",")[0];
				
				for(j= 0;j<l.size();j++)
				{
					if(am.equals(finalMatrix[i][j]))
					{
						int row = 0;
						for(row=1;row<finalMatrix.length;row++)
						{
							if(finalMatrix[row][j]!="NA")
							{
								int len = finalMatrix[row][j].length();
								finalMatrix[row][j]=finalMatrix[row][j].substring(21);
							}
						}
						
					}
				}
				
				//for RAM
				String Ram =  prop.getProperty("RAM");
				String ram = null;
				if(Ram==null)
					ram = "RAM";
				else
					ram = Ram.split(",")[0];
				for(j= 0;j<l.size();j++)
				{
					if(ram.equals(finalMatrix[i][j]))
					{
						int row = 0;
						for(row=1;row<finalMatrix.length;row++)
						{
							finalMatrix[row][j] = finalMatrix[row][j].substring(0,finalMatrix[row][j].indexOf("G")-1);
						}
						
					}
				}
				int trap = 0;
				int u = 0;
				while(finalMatrix[0][u]!=null)
				{
					if(finalMatrix[0][u].contains("Hard Drive"))
					{
						trap = 1;
					}
					u++;
				}
				if(trap==1)
					{
						finalMatrix = mergeHardDrive(finalMatrix);
						finalMatrix = mergepriceHardDrive(finalMatrix);
					}
				csvWriter d = new csvWriter(finalMatrix,output);
			
			
			}
	
			private String[][] mergepriceHardDrive(String[][] finalMatrix) 
			
			{
				
				 String[][] hdValues = new String[100][finalMatrix.length];
					int j=0;
					int k=0;
					//System.out.println("Inside Merge function");
				    int count=0; //to count no. of hardrives
				    while(finalMatrix[0][j]!=null)
					{  //System.out.println("Inside while loop");
				    	 int y=0 ;
				    	    String find = finalMatrix[0][j] ;
					          if (find.contains("Hard Drive price"))
					           { 
					                  
					                   count++;
						                for (int z=1 ; z< finalMatrix.length; z++)
						                  {  //System.out.println("Z==" +  z  +  "J==" + j);
						                  		
							                 hdValues[k][y] =finalMatrix[z][j];
							                 //System.out.println(hdValues[k][y]+" k:"+k+" y:"+y);
							                 y++;
						                  }
						                
						                     k++;
					                  
					          }
						   
					               j++;
					      }
				    
				 
				    int p=0;
				    String[][] singledrive= new String[40][200];
				   
				  
				    while(hdValues[0][p]!=null) 
				   {  
				          if (hdValues[0][p].equals("NA"))
				                 {      }
				       else
				     {
					           for (int s=0;s<count;s++)
				                   {      
						   
						                 String comp=hdValues[s][p];
				    	                 String xyz =comp;
				                             if (comp.equals("NA"))
				    	                            { }
				                             else 
                                             {
                                                   for (int c=s;c<count;c++)
    	                                                {   if (hdValues[c+1][p]==null || hdValues[c+1][p].equals("NA"))
    	                                                            {}
    	                                                             else 
    		                                                            { if(comp.equals(hdValues[c+1][p]))
    		                                                                { //System.out.println("Xyz ::"+xyz);
    			                                                                xyz=xyz + "," + hdValues[c+1][p];
    		   
                                                                                 hdValues[c+1][p]="NA";			    		
    		                                                                 }
    		                                                             }
                                                        }
				    		
				    		                     hdValues[s][p]=(xyz.split(",")[0]+"X"+xyz.split(",").length);
				    	
				    		                    // System.out.println( ">>>>>>>>>>>>>>>>>>>>>" + hdValues[s][p]+ "S="+s + "P="+p); 
				    		                      
				    		
				                               }	
				    		
				      
				       
				                   }	
				    	
				              }
					  
					p++ ; 
				   } //while ends
				    String Arrt [] = new String [20] ;
				    
				    for (int i=0;i<p;i++)
				    {
				    	
				    	for (int o=0;o<count;o++)
				    	{
				    		if ((hdValues[o][i].equals("NA")==true))
				    		{ }
				    		else
				    			if (o==0)
				    				{Arrt[i]=hdValues[o][i] ;}
				    			else
				    		Arrt[i]=Arrt [i] + "+"+hdValues[o][i] ;
				    		
				    		
				    	}
				    	if (Arrt[i]==null)
				    	{Arrt[i]="NA" ;}
				    	
				 
				    	Arrt[i] = Arrt[i].replaceAll("[X]", "*");
				    	StringBuilder s = new StringBuilder(Arrt[i]);
				    	s = s.insert(0, "=");
				    	Arrt[i]=s.toString();
				    	//System.out.print(Arrt[i]);
				    	//System.out.print("\n");
				    		
				    }
				    j=0;
				    String finalMatrix2[][] = new String[finalMatrix.length][200];
				    while(!finalMatrix[0][j].equals("Hard Drive price"))
					{
						//System.out.println("J:"+j+"finalMatrix:"+finalMatrix[0][j]);
						finalMatrix2[0][j] = finalMatrix[0][j];
						int i = 0;
						for(i =1 ;i<finalMatrix.length;i++)
						{
							finalMatrix2[i][j]=finalMatrix[i][j];
						}
						j++;
					}
					int i = 0;
					finalMatrix2[i][j] = "Hard Drive price";
					for(i=0;i<p;i++)
					{
						finalMatrix2[i+1][j]=Arrt[i];
					}
					j++;
					int skip = 0;
				    while(finalMatrix[0][j]!=null)
				    {
				    	if(finalMatrix[0][j].contains("Hard Drive") && finalMatrix[0][j].contains("price"))
				    		{
				    			j++;
				    			skip++;
				    			continue;
				    		}
				    	   
				    		for(i =0 ;i<finalMatrix.length;i++)			    	
								{
									finalMatrix2[i][j-skip]=finalMatrix[i][j];
									//System.out.println(finalMatrix2[i][skip]);
								}
						    	
				    	
				    	   j++;
				    	  
				    }
				   // printMatrix(finalMatrix2);
					return finalMatrix2;
			
			}

			private String[][] mergeHardDrive(String[][] finalMatrix)
			{  
			    String[][] hdValues = new String[100][finalMatrix.length];
				int j=0;
				int k=0;
				//System.out.println("Inside Merge function");
			    int count=0; //to count no. of hardrives
			    while(finalMatrix[0][j]!=null)
				{  //System.out.println("Inside while loop");
			    	 int y=0 ;
			    	    String find = finalMatrix[0][j] ;
				          if (find.contains("Hard Drive"))
				           { if (find.contains("price") || find.contains("price"))
				               {break;}
				                  else
				                  { count++;
					                for (int z=1 ; z< finalMatrix.length; z++)
					                  {  //System.out.println("Z==" +  z  +  "J==" + j);
					                  		
						                 hdValues[k][y] =finalMatrix[z][j];
						                // System.out.println(hdValues[k][y]+" k:"+k+" y:"+y);
						                 y++;
					                  }
					                
					                     k++;
				                  }
				          }
					   
				               j++;
				      }
			    
			 
			    int p=0;
			    String[][] singledrive= new String[40][200];
			   
			  
			    while(hdValues[0][p]!=null) 
			   {  
			          if (hdValues[0][p].equals("NA"))
			                 {      }
			       else
			     {
				           for (int s=0;s<count;s++)
			                   {      
					   
					                 String comp=hdValues[s][p];
			    	                 String xyz =comp;
			                             if (comp.equals("NA"))
			    	                            { }
			                                         else 
			                                             {
			                                                   for (int c=s;c<count;c++)
			    	                                                {   if (hdValues[c+1][p]==null || hdValues[c+1][p].equals("NA"))
			    	                                                            {}
			    	                                                             else 
			    		                                                            { if(comp.equals(hdValues[c+1][p]))
			    		                                                                { //System.out.println("Xyz ::"+xyz);
			    			                                                                xyz=xyz + "," + hdValues[c+1][p];
			    		   
                                                                                             hdValues[c+1][p]="NA";			    		
			    		                                                                 }
			    		                                                             }
			                                                        }
			    		
			    		                     hdValues[s][p]=(xyz.split(",")[0]+"X"+xyz.split(",").length);
			    	
			    		                    // System.out.println( ">>>>>>>>>>>>>>>>>>>>>" + hdValues[s][p]+ "S="+s + "P="+p); 
			    		                      
			    		
			                                              }	
			    		
			      
			       
			                   }	
			    	
			              }
				  
				p++ ; 
			   } //while ends
			    String Arrt [] = new String [20] ;
			    
			    for (int i=0;i<p;i++)
			    {
			    	
			    	for (int o=0;o<count;o++)
			    	{
			    		if ((hdValues[o][i].equals("NA")==true))
			    		{ }
			    		else
			    			if (o==0)
			    				{Arrt[i]=hdValues[o][i] ;}
			    			else
			    		Arrt[i]=Arrt [i] + "+"+hdValues[o][i] ;
			    		
			    		
			    	}
			    	if (Arrt[i]==null)
			    	{Arrt[i]="NA" ;}
			    	
			 
			    	Arrt[i] = Arrt[i].replaceAll("[X]", "*");
			    	
			    //	System.out.print(Arrt[i]);
			    	//System.out.print("\n");
			    		
			    }
			    j=0;
			    String finalMatrix2[][] = new String[finalMatrix.length][200];
				while(!finalMatrix[0][j].equals("Hard Drive"))
				{
					//System.out.println("J:"+j+"finalMatrix:"+finalMatrix[0][j]);
					finalMatrix2[0][j] = finalMatrix[0][j];
					int i = 0;
					for(i =1 ;i<finalMatrix.length;i++)
					{
						finalMatrix2[i][j]=finalMatrix[i][j];
					}
					j++;
				}
				int i = 0;
				finalMatrix2[i][j] = "Hard Drive";
				for(i=0;i<p;i++)
				{
					finalMatrix2[i+1][j]=Arrt[i];
				}
				j++;
				int skip = 0;
			    while(finalMatrix[0][j]!=null)
			    {
			    	if(finalMatrix[0][j].contains("Hard Drive") && !finalMatrix[0][j].contains("price"))
			    		{
			    			j++;
			    			skip++;
			    			continue;
			    		}
			    	   
			    		for(i =0 ;i<finalMatrix.length;i++)			    	
							{
								finalMatrix2[i][j-skip]=finalMatrix[i][j];
								//System.out.println(finalMatrix2[i][skip]);
							}
					    	
			    	
			    	   j++;
			    	  
			    }
			   // printMatrix(finalMatrix2);
				return finalMatrix2;
			} 

			private static String findInMatrix(String[][] matrix, String head,int row)
			{
				// TODO:given the "head" string;find the value in the appropriate row in the matrix;
				int col = 0;
				while(matrix[row][col] != null)
				{
					if(matrix[row][col].equals(head))
					{
						//System.out.println("found:"+head+" row:"+row+" col:"+col);
						//System.out.println("returned:"+matrix[row+1][col]);
						return matrix[row+1][col];
					}
					//System.out.println("Entered while to find:"+head+" in row:"+row+" in col:"+col);						
					col++;	
				}
				//System.out.println("notfound:"+head+" row:"+row+" col:"+col);
				if(head.endsWith("price"))
				{
					return "0.00";
				}
				return "NA";
			}

			public LinkedList<String> reArrange(LinkedList<String> l,int[][] order,String[][] matrix) throws IOException
			{
				//Iterator<String> itr = l.iterator();
				LinkedList<String> nl = new LinkedList<String>();
				int i = 0;
				int m = maxColumnSize(matrix);
				int col;
				int row;
				int size=0;
				for(i=0;i<l.size();i++)
				{
					//System.out.println("List l:"+" "+i+" "+l.get(i));
				}
				XMLCreators x = new XMLCreators();
				size = x.getSize();
					System.out.println("SIZE:"+size);
				for(i=1;i<size+1;i++)
				{
					
					for(col=0;col<m;col++)
					{
						for(row=0;row<matrix.length;row = row+2)
						{
							if(i==order[row][col])
								
							{
								//System.out.println("found i:"+i+"at"+" row:"+row+" col:"+col);
								//System.out.println(matrix[row][col]);
								if(l.contains(matrix[row][col]))
								{
									
									if(!nl.contains(matrix[row][col]))
									{
										nl.add(matrix[row][col]);
										//System.out.println("ADDDED:"+matrix[row][col]);
									}
									//else
										//System.out.println("Contains:"+matrix[row][col]);
									
								}
							}
						}
							
					}
				}
				
				/*for(col=0;col<m;col++)
				{
					System.out.println("col:"+col);
					for(row=0;row<matrix.length;row++)
					{
						System.out.println("order["+row+"]"+"["+col+"]="+order[row][col]);
					}
						
				}*/
				//System.out.println(order[100][100]);
				/*for(i=0;i<l.size();i++)
				{
					String str = l.get(i);
					if(str.contains("price"))
						continue;
					else if(str.contains("Disk"))
						continue;
					else
					{
						if(str.equals("Subtotals") || str.equals("Quantity") || str.equals("NewSubtotals"))
							break;
						//System.out.println("Adding:"+str);
						nl.add(str);
					}
			
					
				}
				for(i=0;i<l.size();i++)
				{
					String str = l.get(i);
					if(str.contains("price"))
						continue;
					else if(str.contains("Disk"))
						nl.add(str);
					else
					{
						if(str.equals("Subtotals") || str.equals("Quantity") || str.equals("NewSubtotals"))
							break;
						//System.out.println("Adding:"+str);
					}
			
					
				}
				//itr = l.iterator();
				//String s = l.get(69);
				//System.out.println(l.size());
				for(i=0;i<l.size();i++)
				{
					String str = l.get(i);
					if(str==null)
						continue;
					//System.out.println("i="+i+";str="+l.get(i));
					if(str.contains("price"))
					{
						nl.add(str);
						//System.out.println("Adding:"+str);
					}
					/*if(str.equals("Subtotals") || str.equals("Quantity") || str.equals("NewSubtotals"))
						break;
					//System.out.println("String:"+str);
					
				}
				nl.add("Subtotals");
				nl.add("Quantity");
				nl.add("NewSubtotals");*/
				// TODO Auto-generated method stub
				System.out.println("The size of l is:"+l.size()+"\nThe size of nl is:"+nl.size());
				return nl;
			}

			private int maxColumnSize(String[][] matrix) {
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

			private void printMatrix(String[][] matrix) 
			
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



