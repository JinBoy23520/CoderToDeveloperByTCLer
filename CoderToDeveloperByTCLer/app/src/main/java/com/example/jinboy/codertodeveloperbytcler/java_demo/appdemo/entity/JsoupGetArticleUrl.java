package com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.entity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by whm on 2017/8/31.
 */
public class JsoupGetArticleUrl {
    /**
     * 获取所有个人博客地址
     * @return
     */
    public static List<String> getCsdnBlogsUrl(){
        List<String> urls = new ArrayList<String>();
        List<String> urls1 = new ArrayList<String>();
        List<String> urls2 = new ArrayList<String>();
        List<String> urls3 = new ArrayList<String>();
//        try {
//            Document doc = getDoc("http://blog.csdn.net/dt235201314");
//            Element body = doc.body();
//            Pattern compile = Pattern.compile("/dt235201314/article/details/\\d{8}$");
//            Elements es=body.select("a");
//            /**
//             * 用set去重
//             */
//            HashSet<String> set = new HashSet<String>();
//            for (Iterator it = es.iterator(); it.hasNext();) {
//                Element e = (Element) it.next();
//                if (compile.matcher(e.attr("href")).find()){
//                    set.add("http://blog.csdn.net" + e.attr("href"));
//                }
//            }
//            urls.addAll(set);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        /**
         * Android studio 相关操作
         */
        urls.add("https://blog.csdn.net/dt235201314/article/details/80195146");



        /**
         * Java源码系列
         */
        urls.add("https://blog.csdn.net/dt235201314/article/details/79867960");
        urls.add("https://blog.csdn.net/dt235201314/article/details/78741922");
        urls.add("https://blog.csdn.net/dt235201314/article/details/78330377");
        urls.add("https://blog.csdn.net/dt235201314/article/details/78318399");
        urls.add("https://blog.csdn.net/dt235201314/article/details/80421037");
        urls.add("https://blog.csdn.net/dt235201314/article/details/80452010");

        urls.add("https://blog.csdn.net/dt235201314/article/details/80661157");
        urls.add("https://blog.csdn.net/dt235201314/article/details/80690157");
//        urls.add("https://blog.csdn.net/dt235201314/article/details/80452010");



        /**
         * Android转后台
         */
        urls.add("https://blog.csdn.net/dt235201314/article/details/78850036");

        /**
         * 面试系列
         */
        urls.add("https://blog.csdn.net/dt235201314/article/details/79767958");
        urls.add("https://blog.csdn.net/DT235201314/article/details/79807777");
        urls.add("https://blog.csdn.net/dt235201314/article/details/79669044");

        /**
         * 雏鹰系列
         */
        urls.add("https://blog.csdn.net/dt235201314/article/details/66477296");
        urls.add("https://blog.csdn.net/dt235201314/article/details/69162998");
        urls.add("https://blog.csdn.net/dt235201314/article/details/70210256");
        urls.add("https://blog.csdn.net/dt235201314/article/details/70228260");

        urls.add("https://blog.csdn.net/dt235201314/article/details/70841306");
        urls.add("https://blog.csdn.net/dt235201314/article/details/71480961");
        urls.add("https://blog.csdn.net/dt235201314/article/details/71588252");
        urls.add("https://blog.csdn.net/dt235201314/article/details/69951885");
        urls.add("https://blog.csdn.net/dt235201314/article/details/72833218");
        urls.add("https://blog.csdn.net/dt235201314/article/details/73176149");
        urls.add("https://blog.csdn.net/dt235201314/article/details/73823653");
        urls.add("https://blog.csdn.net/dt235201314/article/details/75305222");
        urls.add("https://blog.csdn.net/dt235201314/article/details/76528029");

        urls.add("https://blog.csdn.net/dt235201314/article/details/76885747");
        urls.add("https://blog.csdn.net/dt235201314/article/details/77161904");
        urls.add("https://blog.csdn.net/dt235201314/article/details/78133932");
        urls.add("https://blog.csdn.net/dt235201314/article/details/78190492");
        urls.add("https://blog.csdn.net/dt235201314/article/details/78678419");
        urls.add("https://blog.csdn.net/dt235201314/article/details/78718066");
        urls.add("https://blog.csdn.net/dt235201314/article/details/78962509");
        urls.add("https://blog.csdn.net/dt235201314/article/details/79003591");

        urls.add("https://blog.csdn.net/dt235201314/article/details/80255143");

        /**
        MPAndroidChart
         */
        urls.add("https://blog.csdn.net/dt235201314/article/details/52222088");
        urls.add("https://blog.csdn.net/dt235201314/article/details/52242221");
        urls.add("https://blog.csdn.net/dt235201314/article/details/54135182");
        urls.add("https://blog.csdn.net/dt235201314/article/details/70142117");

        urls.add("https://blog.csdn.net/dt235201314/article/details/70237777");
        urls.add("https://blog.csdn.net/dt235201314/article/details/75009573");
        urls.add("https://blog.csdn.net/dt235201314/article/details/76576618");
        urls.add("https://blog.csdn.net/dt235201314/article/details/77248347");
        urls.add("https://blog.csdn.net/dt235201314/article/details/77534468");
        urls.add("https://blog.csdn.net/DT235201314/article/details/78085430");
        urls.add("https://blog.csdn.net/dt235201314/article/details/51367931");
        urls.add("https://blog.csdn.net/dt235201314/article/details/53894944");
        urls.add("https://blog.csdn.net/dt235201314/article/details/78741922");

        urls.add("https://blog.csdn.net/dt235201314/article/details/78804754");

        /**主推博客*/
        urls1.add("https://blog.csdn.net/dt235201314/article/details/50463194");
        urls1.add("https://blog.csdn.net/dt235201314/article/details/52090020");
        urls1.add("https://blog.csdn.net/dt235201314/article/details/51360013");
        urls1.add("https://blog.csdn.net/dt235201314/article/details/51341104");
        urls1.add("https://blog.csdn.net/dt235201314/article/details/54135182");

        urls1.add("https://blog.csdn.net/dt235201314/article/details/78190492");
        urls1.add("https://blog.csdn.net/dt235201314/article/details/78133932");
        urls1.add("https://blog.csdn.net/dt235201314/article/details/77534468");
        urls1.add("https://blog.csdn.net/dt235201314/article/details/77248347");

        urls1.add("https://blog.csdn.net/dt235201314/article/details/77161904");
        urls1.add("https://blog.csdn.net/dt235201314/article/details/76885747");
        urls1.add("https://blog.csdn.net/dt235201314/article/details/76528029");
        urls1.add("https://blog.csdn.net/dt235201314/article/details/75009573");
        urls1.add("https://blog.csdn.net/dt235201314/article/details/72833218");
        urls1.add("https://blog.csdn.net/dt235201314/article/details/71588252");

        urls1.add("https://blog.csdn.net/dt235201314/article/details/78085430");
        urls1.add("https://blog.csdn.net/dt235201314/article/details/78678419");
        urls1.add("https://blog.csdn.net/dt235201314/article/details/80255143");

        /**优秀源码分析*/
        urls1.add("https://blog.csdn.net/dt235201314/article/details/80421037");
        urls1.add("https://blog.csdn.net/dt235201314/article/details/80452010");
        urls1.add("https://blog.csdn.net/dt235201314/article/details/80661157");
        urls1.add("https://blog.csdn.net/dt235201314/article/details/80690157");

        
        return urls1;
    }

    /**
     * 模拟浏览器行为的请求头获取Document
     * @param url
     * @return
     * @throws IOException
     */
    public static Document getDoc(String url) throws IOException {
        /**
         * 在爬之前最好看一下浏览器访问目标网站的Request Header信息，然后进行模仿
         */
        return Jsoup.connect(url)
//                .header("accept", "application/json, text/plain,*/*")
//                .header("Accept-Encoding", "gzip, deflate,br")
//                .header("Accept-Language", "zh-CN,zh;q=0.8")//,en-US;q=0.5,en;q=0.3
//                .header("Referer", "https://www.baidu.com/")
                .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
                .header("Accept-Encoding", "gzip, deflate")
                .header("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")//,en-US;q=0.5,en;q=0.3
                .header("Cache-Control","max-age=0")
                .header("Connection","keep-alive")
                .header("Host", "www.cnblogs.com")
                .header("Referer","http://www.cnblogs.com/WangHaiMing/")
                .header("Upgrade-Insecure-Requests","1")
                .header("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64; rv:55.0) Gecko/20100101 Firefox/55.0")// "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:48.0) Gecko/20100101 Firefox/48.0
                .header("Cookie", "_ga=GA1.2.727269871.1498415016")
                .timeout(5000)
                .get();
    }
}
