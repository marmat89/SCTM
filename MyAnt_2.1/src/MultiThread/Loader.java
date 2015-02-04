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


public class Loader {

	public GraphM load(String path, GraphM g)  {
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
			document = builder.parse(new File(path));
		} catch (SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		NodeList node = document.getElementsByTagName("NODE");
		for (int i = 0; i < node.getLength(); i++) {
			Element nodo = (Element) node.item(i);
			g.addNodo(nodo.getElementsByTagName("NAME").item(0).getFirstChild()
					.getNodeValue());
		}
		for
		(int i = 0; i < node.getLength(); i++) {
			Element nodo = (Element) node.item(i);
			NodeList adiacs = nodo.getElementsByTagName("LINK");
			for (int j = 0; j < adiacs.getLength(); j++) {
				Element adiac = (Element) adiacs.item(j);
				g.addLink(
						nodo.getElementsByTagName("NAME").item(0)
								.getFirstChild().getNodeValue(),
						adiac.getAttributes().getNamedItem("DEST")
								.getNodeValue(),
						Integer.parseInt(adiac.getAttributes()
								.getNamedItem("DIST").getNodeValue()));

			}
		}
		return g;
	}
}
