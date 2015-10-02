package com.belkam.BknTelcom.dao;

import com.belkam.BknTelcom.model.Subscriber;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

/**
 * Created by tsg on 30.07.2014.
 */
public class SubscriberDaoXml implements IsubscriberDAO  {
    private Document document;

    public SubscriberDaoXml(InputStream inputStream) throws ParserConfigurationException, IOException, SAXException { //Создаём конструктор в котором парсим XML из IS
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        docBuilderFactory.setNamespaceAware(true);
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        this.document = docBuilder.parse(inputStream, null);
    }

    @Override
    public List<Subscriber> getAllSubscriber() throws Exception {
        return this.findSubscriber("");
    }

    @Override
    public List<Subscriber> findSubscriber(String findStr) throws Exception {
        List<Subscriber> listSubscribers = new ArrayList<Subscriber>();
        String parseString;
        if (findStr.equals(""))  parseString ="//ROW/TABN[1]";
        else parseString ="//ROW/*[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZАБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЭЮЯ', 'abcdefghijklmnopqrstuvwxyzабвгдежзийклмнопрстуфхцчшщэюя'), '" + findStr.toLowerCase() + "')]";
        XPath xpath = XPathFactory.newInstance().newXPath();
        XPathExpression expr = null;
        expr = xpath.compile(parseString);
        NodeList nodeList = null;
        nodeList = (NodeList)expr.evaluate(this.document , XPathConstants.NODESET);
        Element element = null;
            for (int i = 0; i < nodeList.getLength(); i++) {
                element = (Element) nodeList.item(i).getParentNode();
                Subscriber subscriber = new Subscriber(getVBN(element, "TABN"), getVBN(element, "FIO"), getVBN(element, "EMAIL"), getVBN(element, "ABBR"), getVBN(element, "JOB"), getVBN(element, "VN"), getVBN(element, "GN"), getVBN(element, "ROOM"));
                listSubscribers.add(subscriber);
            }



        return listSubscribers;
    }

    private String getVBN(Element el, String name) { //getValueByName
        //System.out.println(name);
        try {
            return el.getElementsByTagName(name).item(0).getFirstChild().getTextContent();
        } catch (Exception e) {
            return "";
        }

    }
}
