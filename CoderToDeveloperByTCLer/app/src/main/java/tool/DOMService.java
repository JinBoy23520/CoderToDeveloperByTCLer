package tool;




import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.entity.Person;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * 采用DOM解析XML内容
 */

public class DOMService {
    public List<Person> getPersons(InputStream inputStream) throws Exception {

        List<Person> persons = new ArrayList<>();

        //获取DOM解析器工厂
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        //获DOM解析器
        DocumentBuilder builder = factory.newDocumentBuilder();
        //将解析树放入内存，通过返回值Document来描述结果
        Document document = builder.parse(inputStream);
        //取得根元素<personos>
        Element root = document.getDocumentElement();
        //取得所有person节点集合
        NodeList personNodes = root.getElementsByTagName("person");
        for (int i = 0; i < personNodes.getLength(); i++) {
            Person person = new Person();
            //取得person节点元素
            Element personElement = (Element) personNodes.item(i);
            //取得属性值并设置ID
           person.setId(Integer.parseInt(personElement.getAttribute("id")));
            //获取person的子节点
            NodeList personChilds = personElement.getChildNodes();
            for (int j = 0; j < personChilds.getLength(); j++) {
                //判断当前节点是否是元素类型的节点
                if (personChilds.item(j).getNodeType() == Node.ELEMENT_NODE) {
                    Element childElement = (Element) personChilds.item(j);
                    if ("name".equals(childElement.getNodeName())) {
                        //获取孙节点的值
                        person.setName(childElement.getFirstChild().getNodeValue());
                    } else if ("age".equals(childElement.getNodeName())) {
                        person.setAge(Short.parseShort(childElement.getFirstChild().getNodeValue()));
                    }
                }
            }
            persons.add(person);
        }
        return persons;
    }
}
