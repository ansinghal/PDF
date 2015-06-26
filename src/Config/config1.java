package Config;
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


public class config1 {
    // Protected Properties

    protected DocumentBuilderFactory domFactory = null;
    protected DocumentBuilder domBuilder = null;

    public config1() {
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

    public void convertFile(String xmlFileName,
                    String delimiter) {

        
        
            Document doc = domBuilder.newDocument();
            // Root element
            Element rootElement = doc.createElement("Fields");
            doc.appendChild(rootElement);

            
           

            Element server = doc.createElement("Server");
            server.setAttribute("hidden", "1");
    		rootElement.appendChild(server);
    		
    		
    		Element Datacenter = doc.createElement("Datacenter");
    		Datacenter.setAttribute("hidden", "1");
    		rootElement.appendChild(Datacenter);
    		Element Surcharges = doc.createElement("Surcharges");
    		Surcharges.setAttribute("hidden", "1");
    		rootElement.appendChild(Surcharges);
    		Element os  = doc.createElement("OperatingSystem");
    		os.setAttribute("hidden", "1");
    		rootElement.appendChild(os);
    		Element RAM = doc.createElement("RAM");
    		RAM.setAttribute("hidden", "1");
    		rootElement.appendChild(RAM);
    		Element DiskController = doc.createElement("DiskController");
    		DiskController.setAttribute("hidden", "1");
    		rootElement.appendChild(DiskController);
    		Element FirstHardDrive = doc.createElement("FirstHardDrive");
    		FirstHardDrive.setAttribute("hidden", "1");
    		rootElement.appendChild(FirstHardDrive);
    		Element SecondHardDrive = doc.createElement("SecondHardDrive");
    		SecondHardDrive.setAttribute("hidden", "1");
    		rootElement.appendChild(SecondHardDrive);
    		Element ThirdHardDrive = doc.createElement("ThirdHardDrive");
    		ThirdHardDrive.setAttribute("hidden", "1");
    		rootElement.appendChild(ThirdHardDrive);
    		Element FourthHardDrive = doc.createElement("FourthHardDrive");
    		FourthHardDrive.setAttribute("hidden", "1");
    		rootElement.appendChild(FourthHardDrive);
    		Element PublicBandwidth = doc.createElement("PublicBandwidth");
    		PublicBandwidth.setAttribute("hidden", "1");
    		rootElement.appendChild(PublicBandwidth);
    		Element UplinkPortSpeeds= doc.createElement("UplinkPortSpeeds");
    		UplinkPortSpeeds.setAttribute("hidden", "1");
    		rootElement.appendChild(UplinkPortSpeeds);
    		Element PublicNetworkPort = doc.createElement("PublicNetworkPort");
    		PublicNetworkPort.setAttribute("hidden", "1");
    		rootElement.appendChild(PublicNetworkPort);
    		Element PrivateNetworkPort = doc.createElement("PrivateNetworkPort");
    		PrivateNetworkPort.setAttribute("hidden", "1");
    		rootElement.appendChild(PrivateNetworkPort);
    		Element RemoteManagement = doc.createElement("RemoteManagement");
    	    RemoteManagement.setAttribute("hidden", "1");
    		rootElement.appendChild(RemoteManagement);
    		Element PrimaryIPAddresses = doc.createElement("PrimaryIPAddresses");
    		PrimaryIPAddresses.setAttribute("hidden", "1");
    		rootElement.appendChild(PrimaryIPAddresses);
    		Element Monitoring = doc.createElement("Monitoring");
    		Monitoring.setAttribute("hidden", "1");
    		rootElement.appendChild(Monitoring);
    		Element Notification = doc.createElement("Notification");
    		Notification.setAttribute("hidden", "1");
    		rootElement.appendChild(Notification);
    		Element AdvancedMonitoring = doc.createElement("AdvancedMonitoring");
    		AdvancedMonitoring.setAttribute("hidden", "1");
    		rootElement.appendChild(AdvancedMonitoring);
    		Element Response = doc.createElement("Response");
    		Response.setAttribute("hidden", "1");
    		rootElement.appendChild(Response);
    		Element HardwareFirewalls = doc.createElement("HardwareSoftwareFirewalls");
    		HardwareFirewalls.setAttribute("hidden", "1");
    		rootElement.appendChild(HardwareFirewalls);
    		Element VPNManagement = doc.createElement("VPNManagement");
    		VPNManagement.setAttribute("hidden", "1");
    		rootElement.appendChild(VPNManagement);
    		Element VulnerabilityAssessmentsManagement = doc.createElement("VulnerabilityAssessmentsManagement");
    		VulnerabilityAssessmentsManagement.setAttribute("hidden", "1");
    		rootElement.appendChild(VulnerabilityAssessmentsManagement);
    		Element ComputingInstance = doc.createElement("ComputingInstance");
    		ComputingInstance.setAttribute("hidden", "1");
    		rootElement.appendChild(ComputingInstance);
    		Element AntiVirusSpywareProtection = doc.createElement("Anti-VirusSpywareProtection");
    		AntiVirusSpywareProtection.setAttribute("hidden", "1");
    		rootElement.appendChild(AntiVirusSpywareProtection);
    		Element ControlPanelSoftware = doc.createElement("ControlPanelSoftware");
    		ControlPanelSoftware.setAttribute("hidden", "1");
    		rootElement.appendChild(ControlPanelSoftware);
    		Element Insurance = doc.createElement("Insurance");
    		Insurance.setAttribute("hidden", "1");
    		rootElement.appendChild(Insurance);
    		Element IntrusionDetectionProtection = doc.createElement("IntrusionDetectionProtection");
    		IntrusionDetectionProtection.setAttribute("hidden", "1");
    		rootElement.appendChild(IntrusionDetectionProtection);
    		Element EVault = doc.createElement("EVault");
    		 EVault.setAttribute("hidden", "1");
    		rootElement.appendChild(EVault);
    		Element Evaultplugin = doc.createElement("Evaultplugin");
    		 Evaultplugin.setAttribute("hidden", "1");
    		rootElement.appendChild(Evaultplugin);
    		Element ServerSecurity = doc.createElement("ServerSecurity");
    		ServerSecurity.setAttribute("hidden", "1");
    		rootElement.appendChild(ServerSecurity);
    		Element OSSpecificAddon = doc.createElement("OSSpecificAddon");
    		OSSpecificAddon.setAttribute("hidden", "1");
    		rootElement.appendChild(OSSpecificAddon);
    		Element CDPAddon = doc.createElement("CDPAddon");
    		CDPAddon.setAttribute("hidden", "1");
    		rootElement.appendChild(CDPAddon);
    		Element PublicSecondaryIPAddresses = doc.createElement("PublicSecondaryIPAddresses");
    		PublicSecondaryIPAddresses.setAttribute("hidden", "1");
    		rootElement.appendChild(PublicSecondaryIPAddresses);
    		Element PowerSupply = doc.createElement("PowerSupply");
    		PowerSupply.setAttribute("hidden", "1");
    		rootElement.appendChild(PowerSupply);
    		
                        
                       
                        
                        

                    

                
            

            

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

                Source src = new DOMSource(doc);
                Result result = new StreamResult(osw);
                aTransformer.transform(src, result);

                osw.flush();
                //write to xmlPathFile:
                BufferedWriter out = new BufferedWriter(new FileWriter(xmlFileName));
                out.write(baos.toString());
                out.close();
                

            } catch (Exception exp1) {
                exp1.printStackTrace();
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

            }
        
        
        // "XLM Document has been created" + rowsCount;
    }



