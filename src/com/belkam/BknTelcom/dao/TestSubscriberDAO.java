package com.belkam.BknTelcom.dao;

import com.belkam.BknTelcom.dao.SubscriberDAO;
import com.belkam.BknTelcom.model.Subscriber;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tsg on 28.07.2014.
 */
public class TestSubscriberDAO {
    public static void main(String[] args)  {
        List<Subscriber> subscribers = new ArrayList<Subscriber>();
        try {

            InputStream is = new FileInputStream("people.xml");
            IsubscriberDAO subscriberDAO =  new SubscriberDaoXml (is);
            subscribers = subscriberDAO.findSubscriber("абаш");
            for (Subscriber subscriber : subscribers) {
                System.out.println(subscriber);
            }
            subscribers = subscriberDAO.getAllSubscriber();
            for (Subscriber subscriber : subscribers) {
                System.out.println(subscriber);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
