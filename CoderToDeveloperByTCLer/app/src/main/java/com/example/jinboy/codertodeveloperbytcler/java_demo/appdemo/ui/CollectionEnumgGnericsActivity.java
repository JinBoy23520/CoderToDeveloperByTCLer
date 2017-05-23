package com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.R;

import java.util.ArrayList;

import javademo.entity.Animal;
import javademo.entity.Cat;
import javademo.entity.Color;
import javademo.entity.Dog;

public class CollectionEnumgGnericsActivity extends Activity implements View.OnClickListener {
    private Button button, button1, button2;
    private TextView textView;
    private ArrayList<? super Animal> arrayList;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_enumg_gnerics);
        button = (Button) findViewById(R.id.button);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        textView = (TextView) findViewById(R.id.text);
        arrayList = new ArrayList<>();
        button.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
    }

    public void button() {
        i++;
        Dog dog = new Dog("多多" + i, "2", Color.green.toString());
        arrayList.add(dog);
        setText();
    }

    public void button1() {
        i++;
        Cat cat = new Cat("喵喵" + i, "3", Color.blue.toString());
        arrayList.add(cat);
        setText();
    }

    public void button2() {
        arrayList.clear();
        textView.setText("");
    }

    public void setText() {
        textView.setText("");
        String string = "";
        for (int i = 0; i < arrayList.size(); i++) {
            Animal animal = (Animal) arrayList.get(i);
            String text = "姓名" + animal.getName() + "   年龄" + animal.getAge() + "  颜色" + animal.getColor() + "\n";
            string += text;
        }
        textView.setText(string);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                button();
                break;
            case R.id.button1:
                button1();
                break;
            case R.id.button2:
                button2();
                break;
        }
    }
}