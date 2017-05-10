package tool;

import android.util.Xml;


import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.entity.Person;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 采用PULL解析XML内容
 */

public class PULLService {
    public List<Person> getPersons(InputStream inputStream) throws Exception {
        List<Person> persons = null;
        Person person = null;
        //得到PULL解析器
        XmlPullParser parser = Xml.newPullParser();
        parser.setInput(inputStream,"UTF-8");
        //产生事件
        int eventType = parser.getEventType();
        //如果不是文档结束事件就循环推进
        while (eventType != XmlPullParser.END_DOCUMENT) {
            switch (eventType) {
                case XmlPullParser.START_DOCUMENT://开始文档事件
                    persons = new ArrayList<>();
                    break;
                case XmlPullParser.START_TAG://开始元素事件
                    //获取解析器当前指向的元素的名称
                    String name = parser.getName();
                    if ("person".equals(name)) {
                        person = new Person();
                        person.setId(Integer.parseInt(parser.getAttributeValue(0)));
                    }
                    if (person != null) {
                        if ("name".equals(name)) {
                            //获取解析器当前指向元素的下一个文本节点的值
                            person.setName(parser.nextText());
                        }
                        if ("age".equals(name)) {
                            person.setAge(Short.parseShort(parser.nextText()));
                        }
                    }
                    break;
                case XmlPullParser.END_TAG://结束元素事件
                    //判断是都是person的结束事件
                    if ("person".equals(parser.getName())) {
                        persons.add(person);
                        person = null;
                    }
                    break;
            }
            //进入下一个元素并触发相应的事件
            eventType = parser.next();
        }
        return persons;
    }
}
