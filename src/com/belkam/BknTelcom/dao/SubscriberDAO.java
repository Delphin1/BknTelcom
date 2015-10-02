package com.belkam.BknTelcom.dao;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import com.belkam.BknTelcom.R;
import com.belkam.BknTelcom.XMLParse.XMLParser;
import com.belkam.BknTelcom.model.Subscriber;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tsg
 * Date: 11.07.14
 * Time: 15:25
 * To change this template use File | Settings | File Templates.
 */
public class SubscriberDAO /*implements IsubscriberDAO */{
    private XMLParser parser = null;



//    @Override

    public void SubscriberDAO(InputStream is) throws Exception {
        parser =  XMLParserSngl.getInstance();
        parser.parse(is);
    }

    public List<Subscriber> getAllSubscriber() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }


    public List<Subscriber> findSubscriber(String findStr) throws Exception {
        NodeList nodeList = null;
        List<Subscriber> listSubscribers = new ArrayList<Subscriber>();

        nodeList = parser.parseXML("//ROW/*[contains(translate(., 'ABCDEFGHIJKLMNOPQRSTUVWXYZАБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЭЮЯ', 'abcdefghijklmnopqrstuvwxyzабвгдежзийклмнопрстуфхцчшщэюя'), '" + findStr.toLowerCase() + "')]");
        Element element = null;

        for (int i = 0; i < nodeList.getLength(); i++) {
            element = (Element) nodeList.item(i).getParentNode();
            Subscriber subscriber = new Subscriber(getVBN(element,"TABN"), getVBN(element,"FIO"), getVBN(element,"EMAIL"), getVBN(element,"ABBR"), getVBN(element,"JOB"), getVBN(element,"VN"), getVBN(element,"GN"), getVBN(element,"ROOM"));
            listSubscribers.add(subscriber);
        }
        return listSubscribers;  //To change body of implemented methods use File | Settings | File Templates.
    }

    private String getVBN(Element el, String name) { //getValueByName
        return el.getElementsByTagName(name).item(0).getFirstChild().getTextContent();
    }
}
