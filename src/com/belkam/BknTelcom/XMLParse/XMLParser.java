package com.belkam.BknTelcom.XMLParse;

import android.content.res.AssetManager;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created with IntelliJ IDEA.
 * User: tsg
 * Date: 14.07.14
 * Time: 9:28
 * To change this template use File | Settings | File Templates.
 */
public class XMLParser {
    //private InputStream iStream;
    private DocumentBuilder docBuilder = null;
    private Document document = null;

    public XMLParser() throws Exception {
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        docBuilderFactory.setNamespaceAware(true);
        docBuilder = docBuilderFactory.newDocumentBuilder();
    }

    //public void setIStream (InputStream is) { this.iStream = is;}

//    public XMLParser(InputStream iStream) throws Exception {
//        setIStream(iStream);
//
//        document = docBuilder.parse(this.iStream, null);
//
//    }

    public void parse (InputStream iStream) throws Exception {
        document = docBuilder.parse(iStream, null);
    }

    public NodeList parseXML(String xPath) throws Exception {
        XPath xpath = XPathFactory.newInstance().newXPath();
        XPathExpression expr = null;
        expr = xpath.compile(xPath);
        //this line takes lot of time
        NodeList result = null;
        result = (NodeList)expr.evaluate(this.document , XPathConstants.NODESET);
        return result;

    }





}
