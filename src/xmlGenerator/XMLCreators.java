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
            while ((text = csvReader.readLine()) != null) {

                StringTokenizer st = new StringTokenizer(text, delimiter, false);
              //number of comma seperated values in this line of text:
                String[] rowValues = new String[st.countTokens()];
                int index = 0;
                while (st.hasMoreTokens()) {

                    String next = st.nextToken();
                    rowValues[index++] = next;

                }

                if (line == 0) { // Header row

                    for (String col : rowValues) {
                       //we need to implement find logic here:
                    	headers.add(col);
                    }

                } else { // Data row

                    rowsCount++;

                    Element rowElement = newDoc.createElement("row");
                    rootElement.appendChild(rowElement);
                    for (int col = 0; col < headers.size(); col++) {

                        String header = headers.get(col);
                        String value = null;

                        if (col < rowValues.length) {

                            value = rowValues[col];

                        } else {
                            // ?? Default value
                            value = "NA";
                        }

                        Element curElement = newDoc.createElement(header);
                        curElement.appendChild(newDoc.createTextNode(value));
                        rowElement.appendChild(curElement);

                    }

                }
                line++;

            }

            ByteArrayOutputStream baos = null;
            OutputStreamWriter osw = null;
////what is this:
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
                //System.out.println(new String(baos.toByteArray()));

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