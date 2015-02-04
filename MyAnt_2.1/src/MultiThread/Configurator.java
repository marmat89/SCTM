package MultiThread;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Configurator {

	public String getTagValueSTR(String value) {
		DocumentBuilderFactory documentFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder builder = null;
		try {
			builder = documentFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Document document = null;
		try {
			document = builder.parse(new File("conf.xml"));
		} catch (SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		NodeList node = document.getElementsByTagName("configuration");
		Element nodo = (Element) node.item(0);
		nodo.getElementsByTagName(value).item(0).getFirstChild().getNodeValue();
		return nodo.getElementsByTagName(value).item(0).getFirstChild()
				.getNodeValue();
	}

	public int getTagValueINT(String value) {
		DocumentBuilderFactory documentFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder builder = null;
		try {
			builder = documentFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Document document = null;
		try {
			document = builder.parse(new File("conf.xml"));
		} catch (SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		NodeList node = document.getElementsByTagName("configuration");
		Element nodo = (Element) node.item(0);
		nodo.getElementsByTagName(value).item(0).getFirstChild().getNodeValue();
		return Integer.parseInt(nodo.getElementsByTagName(value).item(0)
				.getFirstChild().getNodeValue());
	}

	public Brain getBrain() {
		Brain br = null;
		this.getTagValueINT("BRAIN_TYPE");
		switch (this.getTagValueINT("BRAIN_TYPE")) {
		// TODO Add Other Brain
		case 0: 
			br = new RandomBrain();
		       break;
		case 1: 
			br = new LinearBrain();
		       break;
		case 2: 
			br = new ExponentialBrain();
		       break;
		case 3: 
			br = new SqrtBrain();
		       break;
		case 4: 
			br = new FunBrain();
		       break;
		case 5: 
			br = new LogBrain();
		       break;
		
		}

		System.out.println(br.getClass());
		return br;
	}
}