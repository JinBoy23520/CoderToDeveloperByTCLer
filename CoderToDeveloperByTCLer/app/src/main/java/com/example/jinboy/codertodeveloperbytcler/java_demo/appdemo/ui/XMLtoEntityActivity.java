package com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.R;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.entity.Person;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import tool.PULLService;

public class XMLtoEntityActivity extends AppCompatActivity {
    private TextView tv_show_entity;
    private String string = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xmlto_entity_activity);
        tv_show_entity = (TextView) findViewById(R.id.tv_show_entity);
    }

    public void startXML(View view){
//        SAXService saxService = new SAXService();
//        DOMService domService = new DOMService();
        PULLService pullService = new PULLService();
        try {
            InputStream inputStream = getAssets().open("Users.xml");
//            List<Person> persons = saxService.getPerson(inputStream);
//            List<Person> persons = domService.getPersons(inputStream);
            List<Person> persons = pullService.getPersons(inputStream);
            for (Person person : persons) {
                Log.e("TAG",person.toString());
                string += person.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        tv_show_entity.setText(string);
    }
}
