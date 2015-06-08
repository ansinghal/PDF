package xmlGenerator;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		XMLCreators x = new XMLCreators();
		String csvfilepath = "C:\\Users\\IBM_ADMIN\\Desktop\\project\\output\\test.csv";
		String xmlfilepath = "C:\\Users\\IBM_ADMIN\\Desktop\\project\\output\\test.xml";
		x.convertFile(csvfilepath,xmlfilepath,",");
	}

}
