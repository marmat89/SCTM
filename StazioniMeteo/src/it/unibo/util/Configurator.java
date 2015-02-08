package it.unibo.util;

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
	public Configurator(String filePath, String elementName) {
		super();
		this.filePath = filePath;
		this.elementName = elementName;
	}

	String filePath;
	String elementName;

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
			document = builder.parse(new File(filePath));
		} catch (SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		NodeList node = document.getElementsByTagName(elementName);
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
			document = builder.parse(new File(filePath));
		} catch (SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		NodeList node = document.getElementsByTagName(elementName);
		Element nodo = (Element) node.item(0);
		nodo.getElementsByTagName(value).item(0).getFirstChild().getNodeValue();
		return Integer.parseInt(nodo.getElementsByTagName(value).item(0)
				.getFirstChild().getNodeValue());
	}

}