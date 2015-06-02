package xmlGeneration;

import java.io.File;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;

public class Insert2Xml {
	public static void insert(String[] str)
	{
		try{
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			// root elements
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement(str[0]);
			doc.appendChild(rootElement);
			// header elements
			Element header = doc.createElement("header");
			rootElement.appendChild(header);
			Attr attr = doc.createAttribute("id");
			attr.setValue(str[0]);
			header.setAttributeNode(attr);
			// price elements
			Element price = doc.createElement("price");
			rootElement.appendChild(price);
			Attr value = doc.createAttribute("value");
			value.setValue(str[2]);
			price.setAttributeNode(value);
			//property elements
			Element property = doc.createElement("property");
			rootElement.appendChild(property);
			property.setNodeValue(str[1]);
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("C:\\Users\\IBM_ADMIN\\Desktop\\Project\\output\\file.xml"));
			 
			// Output to console for testing
			//StreamResult result1 = new StreamResult(System.out);
	 
			transformer.transform(source, result);
	 
			System.out.println("File saved!");
	 
			
			
			
			
		}
		catch (ParserConfigurationException pce)
		{
			pce.printStackTrace();
		} 
		catch (TransformerException tfe)
		{
			tfe.printStackTrace();
		}
		catch (DOMException e)
		{
			e.printStackTrace();
		}
	}

}
