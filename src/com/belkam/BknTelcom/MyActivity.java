package com.belkam.BknTelcom;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.view.ViewGroup;
import android.widget.*;

import com.belkam.BknTelcom.XMLParse.XMLParser;

import com.belkam.BknTelcom.dao.IsubscriberDAO;
import com.belkam.BknTelcom.dao.SubscriberDaoXml;
import com.belkam.BknTelcom.model.Subscriber;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MyActivity extends Activity implements View.OnClickListener {
    EditText edtText;
    Button btnClear;
    Button btnOK;
    //ScrollView svResult;
    //LinearLayout llResult;
    ListView lvResult;
    final int MENU_QUIT_ID = 0;
    final int MENU_PROP_ID = 1;
    final String ATTRIBUTE_NAME_TAB = "tab";
    final String ATTRIBUTE_NAME_FIO = "fio";

    private static final String logTag = "BknTelcomTag";
    private IsubscriberDAO subscriberDAO;
    int[] colors = new int[2];

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        try {
            //InputStream is = new FileInputStream("people.xml");
            subscriberDAO =  new SubscriberDaoXml(this.getResources().openRawResource(R.raw.people_small));

            //parser =  new XMLParser(this.getResources().openRawResource(R.raw.people_small));
        } catch (Exception e) {
            //e.printStackTrace();
            //Log.d(logTag, e.getMessage());
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            finish();
        }

        colors[0] = Color.parseColor("#559966CC");
        colors[1] = Color.parseColor("#55336699");
        //Find view elements
        edtText = (EditText) findViewById(R.id.editText);
        btnClear = (Button)  findViewById(R.id.btnClear);
        btnOK = (Button) findViewById(R.id.btnOK);
        /*svResult = (ScrollView) findViewById(R.id.svResult);
        llResult = (LinearLayout) findViewById(R.id.llResult);*/


        //Set click listener
        edtText.setOnClickListener(this);
        btnClear.setOnClickListener(this);
        btnOK.setOnClickListener(this);


    }

    //Создание меню
    @Override
    public boolean  onCreateOptionsMenu(Menu menu) {
        //menu.add(0,MENU_PROP_ID,1, R.string.mnu_properties);
        menu.add(0,MENU_QUIT_ID,2, R.string.mnu_exit);
        return super.onCreateOptionsMenu(menu);
    }

    //Обработка нажатий на пункты меню
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d(logTag, ""+item.getTitle());
//        Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
        switch (item.getItemId()) {
            case MENU_QUIT_ID :
                finish();
            break;

          /*  case MENU_PROP_ID :
                Intent intent = new Intent(this, MyProperties.class);
                startActivity(intent);
            break;*/



        }
        return onContextItemSelected(item);
    }

    //Обработка нажатий на конопки
    @Override
    public void onClick(View v) {
        List<Subscriber> subscribers = new ArrayList<Subscriber>();

         switch (v.getId()) {
            /*case R.id.editText :
                //v.get
                Toast.makeText(this, "Начать поиск!", Toast.LENGTH_SHORT).show();
            break;
            */
            case R.id.btnClear :
                edtText.setText("");
                //lvResult.setAdapter(null);
                //lvResult.removeAllViews();6
                //llResult.removeAllViews();
            break;

            case R.id.btnOK :
                //Toast.makeText(this, "Нажата кнопка ОК", Toast.LENGTH_SHORT).show();
                //List<Subscriber> slist = null;
                //llResult.removeAllViews();  //Очищаем вывод
                //lvResult.removeAllViews();
                //lvResult.setAdapter(null);

                //LayoutInflater ltInflate = getLayoutInflater();
                try {
                    subscribers = subscriberDAO.findSubscriber(edtText.getText().toString());
                } catch (Exception e) {
                    //e.printStackTrace();
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
                    Log.d(logTag, e.getMessage());
                    break;
                }


                if (subscribers.isEmpty()) {
                    Toast.makeText(this, "Нет совпадений со словом " + edtText.getText() + " !", Toast.LENGTH_LONG).show();
                    break;
                }
                //Реализуем отображение через SimpleAdapter

                String[] from = {ATTRIBUTE_NAME_TAB, ATTRIBUTE_NAME_FIO};
                int[] to = {R.id.tvTab, R.id.tvFIO};
                //Упаковываем данные во понятные адаптеру структуру
                ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>(subscribers.size());
                Map <String, Object> m;

                for (int i=0; i<subscribers.size(); i++) {

                    m = new HashMap<String, Object>();

                    m.put(ATTRIBUTE_NAME_TAB, "таб: " + subscribers.get(i).getTabNum());
                    m.put(ATTRIBUTE_NAME_FIO, subscribers.get(i).getFio());
                    data.add(m);
                }
                //Создаем адаптер
                SimpleAdapter simpleAdapter = new SimpleAdapter(this, data, R.layout.item, from, to);
                //Определяем список и подсовываем ему адаптер
                //findViewById(R.id.svResult);
                lvResult = (ListView) findViewById(R.id.lvResult);
                lvResult.setAdapter(simpleAdapter);





                /*// перебираем
                Element element = null;

                for (int i = 0; i < nodeList.getLength(); i++) {
                    element = (Element) nodeList.item(i).getParentNode();
                    View item = ltInflate.inflate(R.layout.item, llResult, false);

                    TextView tvTab = (TextView) item.findViewById(R.id.tvTab);
                    tvTab.setText("таб: " + getVBN(element,"TABN"));

                    TextView tvFIO = (TextView) item.findViewById(R.id.tvFIO);
                    tvFIO.setText(getVBN(element,"FIO"));

                    TextView tvEmail = (TextView) item.findViewById(R.id.tvEmail);
                    tvEmail.setText(getVBN(element,"EMAIL"));

                    TextView tvRoom = (TextView) item.findViewById(R.id.tvRoom);
                    tvRoom.setText(getVBN(element,"ROOM"));

                    TextView tvVN = (TextView) item.findViewById(R.id.tvVN);
                    tvVN.setText("вн: " + getVBN(element,"VN"));

                    TextView tvGN = (TextView) item.findViewById(R.id.tvGN);
                    tvGN.setText("гн: " + getVBN(element,"GN"));

                    *//*TextView tv = new TextView(this);
                    tv.setText(getVBN(element,"TABN") + " " +  getVBN(element,"FIO"));*//*
                    item.getLayoutParams().width = FrameLayout.LayoutParams.MATCH_PARENT;
                    item.setBackgroundColor(colors[i % 2]);
                    llResult.addView(item);*/
//                    Subscriber subscriber = new Subscriber(getVBN(element,"TABN"), getVBN(element,"FIO"), getVBN(element,"EMAIL"), getVBN(element,"ABBR"), getVBN(element,"JOB"), getVBN(element,"VN"), getVBN(element,"GN"), getVBN(element,"ROOM"));
//                    listSubscribers.add(subscriber);
//                }



                /*for(Subscriber subscriber: slist) {
                    TextView tv = new TextView(this);
                    tv.setText(subscriber.toString());
                    llResult.addView(tv);
                }*/



            break;

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
