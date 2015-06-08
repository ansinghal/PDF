package xmlGenerator;

public class XmlGenerator {

	public XmlGenerator(String csvfilePath, String xmlfilePath, String delimiter) {
		// TODO Auto-generated constructor stub
		XMLCreators x = new XMLCreators();
		x.convertFile(csvfilePath,xmlfilePath,delimiter);
	}

}
