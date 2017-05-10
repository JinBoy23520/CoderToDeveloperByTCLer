package tool;



import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.entity.Person;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * 采用SAX解析XML内容
 */

public class SAXService {
    public List<Person> getPerson(InputStream inputStream) throws Exception {
        //得到SAX解析工厂
        SAXParserFactory factory = SAXParserFactory.newInstance();
        //得到SAX解析器
        SAXParser parser = factory.newSAXParser();
        PersonParser personParser = new PersonParser();
        parser.parse(inputStream,personParser);
        inputStream.close();
        return personParser.getPersons();
    }
    private final class PersonParser extends DefaultHandler {

        private List<Person> persons = null;
        private String tag = null;//记录当前解析到了那个元素节点名称
        private Person person;

        public List<Person> getPersons(){
            return persons;
        }
        //一开始会执行这个方法，所以在这里面完成初始化
        @Override
        public void startDocument() throws SAXException {
            persons = new ArrayList<>();
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            //判断元素节点是否等于person
            if ("person".equals(localName)) {
                person = new Person();
                //获取数据,参数为索引下标
                person.setId(Integer.parseInt(attributes.getValue(0)));
            }
            tag = localName;
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if ("person".equals(localName)) {
                persons.add(person);
                person = null;
            }
            tag = null;
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            if (tag != null) {
                //获取文本节点的数据
                String data = new String(ch, start, length);
                if ("name".equals(tag)) {
                    person.setName(data);
                } else if ("age".equals(tag)) {
                    person.setAge(Short.parseShort(data));
                }
            }
        }
    }
}
