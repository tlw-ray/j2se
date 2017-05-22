package com.tlw.eg.dom;

import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
@Author: 唐力伟 (tlw_ray@163.com)
@since:2009-9-15
@version:2009-9-15
Description:
 */
public class DOMCreateAndExport {
	public static void main(String[] args) throws ParserConfigurationException, FactoryConfigurationError, TransformerFactoryConfigurationError, TransformerException {
		DocumentBuilder db=DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document doc=db.newDocument();
		Element elNode1=doc.createElement("PointGroups");
		doc.appendChild(elNode1);
		Element elNode=doc.createElement("pointGroups");
		elNode.setAttribute("name", "gps1");
		elNode1.appendChild(elNode);
		Element elG1=doc.createElement("PointGroup");
		elG1.setAttribute("name", "默认");
		Element elP1=doc.createElement("Point");
		Element elP2=doc.createElement("Point");
		elP1.setAttribute("name", "W3.UNIT1.A0001");
		elP2.setAttribute("name","W3.UNIT1.A0002");
		elNode.appendChild(elG1);
		elG1.appendChild(elP1);
		elG1.appendChild(elP2);
		StringWriter sw=new StringWriter();
		DOMSource source=new DOMSource(doc);
		StreamResult result=new StreamResult(sw);
		Transformer trans=TransformerFactory.newInstance().newTransformer();
		trans.setOutputProperty(OutputKeys.INDENT,"yes");
		trans.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		trans.transform(source, result);
		System.out.println(sw.toString());
	}
}
