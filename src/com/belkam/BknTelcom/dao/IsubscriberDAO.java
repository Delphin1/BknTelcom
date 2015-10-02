package com.belkam.BknTelcom.dao;

import android.content.res.AssetManager;
import com.belkam.BknTelcom.model.Subscriber;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tsg
 * Date: 11.07.14
 * Time: 15:21
 * To change this template use File | Settings | File Templates.
 */
public interface IsubscriberDAO {
    public List<Subscriber> getAllSubscriber() throws Exception;
    public List<Subscriber> findSubscriber(String findStr) throws Exception;
}
