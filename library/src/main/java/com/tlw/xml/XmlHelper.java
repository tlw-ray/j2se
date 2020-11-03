package com.tlw.xml;

import java.io.IOException;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
public class XmlHelper {
    public static String node2XML(Node node) {
        String processedXML = null;
        try {
            // Transform the DOM tree into XML String
            TransformerFactory transFactory = TransformerFactory.newInstance();
            Transformer transformer = transFactory.newTransformer();
            DOMSource dSource = new DOMSource(node);
            StringWriter sw = new StringWriter();
            StreamResult sr = new StreamResult(sw);
            transformer.transform(dSource, sr);
            StringWriter anotherSW = (StringWriter) sr.getWriter();
            StringBuffer sBuffer = anotherSW.getBuffer();
            processedXML = sBuffer.toString();
            // Debug
            System.out
                    .println("===== the processed xml from XMLProcessor ===== ");
            // System.out.println( sBuffer.toString() );
            System.out.println("processedXML: " + processedXML);
        } catch (Exception ex) {
            System.out.println("Exception! >>> " + ex.getMessage());
            ex.printStackTrace();
        }
        return processedXML;
    }
    public static Document loadDocument(String xmlFile){
        try {
            DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
            DocumentBuilder db=dbf.newDocumentBuilder();
            System.out.println(db.toString());
            Document doc = db.parse(xmlFile);
            return doc;
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void saveDocumenByTransform(Document doc,String filename){
        try {
            TransformerFactory tFactory =TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer();
            System.out.println(transformer.toString());
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new java.io.File(filename));
            transformer.transform(source, result);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}
