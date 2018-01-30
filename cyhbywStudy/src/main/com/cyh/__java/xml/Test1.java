package com.cyh.__java.xml;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
public class Test1 {
	
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		File f = new File("D:/work/kpi-201508270400_96/PM201508270402+030096PLMN.xml");
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(f);
		NodeList n1 = doc.getElementsByTagName("");
		for(int e=0, len=n1.getLength(); e < len; e++) {
			System.out.println(doc.getElementsByTagName("").item(e).getFirstChild().getNodeValue());
		}
		
		
	}
	
}
	
	
