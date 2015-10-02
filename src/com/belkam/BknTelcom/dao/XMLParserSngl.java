package com.belkam.BknTelcom.dao;

import com.belkam.BknTelcom.XMLParse.XMLParser;

/**
 * Created by tsg on 28.07.2014.
 */
public class XMLParserSngl {
    private static XMLParser instance;
    private XMLParserSngl () {
           }
    public static synchronized XMLParser getInstance() throws Exception {
        if (instance == null) {
            instance = new XMLParser();
        }
        return instance;
    }


}
