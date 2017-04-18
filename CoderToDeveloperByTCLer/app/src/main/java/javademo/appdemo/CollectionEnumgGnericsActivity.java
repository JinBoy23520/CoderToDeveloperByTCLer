package javademo.appdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.jinboy.codertodeveloperbytcler.R;

import java.util.ArrayList;

import javademo.Entity.Animal;
import javademo.Entity.Cat;
import javademo.Entity.Color;
import javademo.Entity.Dog;

public class CollectionEnumgGnericsActivity extends Activity implements View.OnClickListener {
    private Button button, button1;
    private TextView textView;
    private ArrayList<? super Animal> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_enumg_gnerics);
        button= (Button) findViewById(R.id.button);
        button1 = (Button) findViewById(R.id.button1);
        textView = (TextView) findViewById(R.id.text);
        arrayList = new ArrayList<>();
        button.setOnClickListener(this);
        button1.setOnClickListener(this);
    }

    public void button(){
        Dog dog = new Dog("多多","2", Color.green.toString());
        arrayList.add(dog);
        setText();
    }

    public void button1(){
        Cat cat = new Cat("喵喵","3", Color.blue.toString());
        arrayList.add(cat);
        setText();
    }

    public void setText(){
        textView.setText("");
        String string = "";
        for(int i=0;i <  arrayList.size();i++){
            Animal animal = (Animal) arrayList.get(i);
            String text = "姓名" + animal.getName() + "   年龄" +  animal.getAge() + "  颜色"  + animal.getColor() + "\n";
            string += text ;
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
        }
    }
}