package Config;

import xmlGenerator.XMLCreators;

public class configcreate {

	public configcreate(String xmlfilePath, String delimiter) {
		// TODO Auto-generated constructor stub
		config1 x = new config1();
		x.convertFile(xmlfilePath,delimiter);
	}

}
