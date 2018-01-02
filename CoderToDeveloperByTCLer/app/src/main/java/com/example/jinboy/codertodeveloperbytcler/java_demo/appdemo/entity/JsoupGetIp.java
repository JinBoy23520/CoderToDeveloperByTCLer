package com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.entity;

import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.entity.JsoupGetArticleUrl.getCsdnBlogsUrl;
import static com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.entity.JsoupGetArticleUrl.getDoc;


/**
 * Created by whm on 2017/8/31.
 */
public class JsoupGetIp {
    /**
     * 获取代理IP地址
     * @param url
     * @return
     */
    public static List<AgencyIp> getIp(String url) {
        List<AgencyIp> ipList = null;
        try {
            //1.向ip代理地址发起get请求，拿到代理的ip
            Document doc = getDoc(url);

            //2,将得到的ip地址解析除字符串
            String ipStr = doc.body().text().trim().toString();

            ipList = new ArrayList<AgencyIp>();

            List<String> ips = new ArrayList<String>();
            String lines[] = ipStr.split("\r\n");
            for(int i=0; i<lines.length;i++){
                //out.println(lines[i]+"<br />");
                //匹配到ip地址
                Pattern ipreg = Pattern.compile("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3} \\d{4}");
                Matcher ip = ipreg.matcher(lines[i]);
                while(ip.find()){
                    System.out.println(ip.group().replace(" ",":"));
                    ips.add(ip.group().replace(" ",":"));
                }
            }
            //4.循环遍历得到的ip字符串，封装成AgencyIp的bean

            for(final String ip : ips) {
                System.out.println(ip);
                AgencyIp AgencyIp = new AgencyIp();
                String[] temp = ip.split(":");
                AgencyIp.setAddress(temp[0].trim());
                AgencyIp.setPort(temp[1].trim());
                ipList.add(AgencyIp);
            }
        } catch (IOException e) {
            System.out.println("加载文档出粗");
        }
        return ipList;
    }

    /**
     * 访问文章
     * @param blogUrl
     * @param ipList
     */
    public static void visit(String blogUrl,List<AgencyIp> ipList){
        int time = 100;
        int count = 0;

        for(int i = 0; i< time; i++) {
            //2.设置ip代理
            for(final AgencyIp AgencyIp : ipList) {
                System.setProperty("http.maxRedirects", "50");
                System.getProperties().setProperty("proxySet", "true");
                System.getProperties().setProperty("http.proxyHost", AgencyIp.getAddress());
                System.getProperties().setProperty("http.proxyPort", AgencyIp.getPort());

                try {
                    Thread.sleep(1000);
                    Document doc = getDoc(blogUrl);
                    if(doc != null) {
                        count++;
                        System.out.println("成功刷新次数: " + count);
                        System.out.println("文章地址:" + blogUrl);
//                        System.out.println("doc====================================="+doc);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) {
        //1.想http代理地址api发起请求，获得想要的代理ip地址
        String url = "http://www.xicidaili.com/nn/";
        String foreignUrl = "http://www.kuaidaili.com/free/outha/";
        final List<AgencyIp> ipList = getForeignIp();
//        final List<AgencyIp> ipList = Arrays.asList(new AgencyIp("178.62.123.38","8118"));
        ipList.addAll(getIp(url));

        List<String> urls = getCsdnBlogsUrl();
        urls.add("https://s01.flagcounter.com/map/i6UK/size_s/txt_000000/border_CCCCCC/pageviews_1/viewers_0/flags_0");
//        List<String> urls = Arrays.asList("http://blog.csdn.net/w980994974/article/details/77718751");
        for (final String u:urls){
            new Thread(new Runnable() {

                public void run() {
                    System.out.println("文章地址:" + u);
                    visit(u,ipList);
                }
            }).start();
        }
    }
    /**
     * 获取代理IP地址
     * @param
     * @return
     */
    public static List<AgencyIp> getForeignIp() {
        List<AgencyIp> ipList = null;
        try {
            //2,将得到的ip地址解析除字符串
            String ipStr = htmlContent.trim().toString().substring(htmlContent.indexOf("<tbody>"),htmlContent.indexOf("</tbody>"));

            ipList = new ArrayList<AgencyIp>();

            List<String> ips = new ArrayList<String>();
            String lines[] = ipStr.split("</tr>");
            for(int i=0; i<lines.length;i++){
                //匹配到ip地址
//                String ip = lines[i].substring(lines[i].indexOf("<td data-title='IP'>"),lines[i].fiindexOf("</td>"));
                Pattern ipreg = Pattern.compile("((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)");
                Matcher ip = ipreg.matcher(lines[i]);
                Pattern portreg = Pattern.compile("([0-9]{4})");
                Matcher port = portreg.matcher(lines[i]);

               if(ip.find()&& port.find()){
                    ips.add(ip.group()+":"+port.group());
                   System.out.println(ip.group()+":"+port.group());
                }
            }
            //4.循环遍历得到的ip字符串，封装成AgencyIp的bean

            for(final String ip : ips) {
                System.out.println(ip);
                AgencyIp AgencyIp = new AgencyIp();
                String[] temp = ip.split(":");
                AgencyIp.setAddress(temp[0].trim());
                AgencyIp.setPort(temp[1].trim());
                ipList.add(AgencyIp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ipList;
    }

    private static String htmlContent = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
            "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
            "<head>\n" +
            "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"/>\n" +
            "<meta name=\"format-detection\" content=\"telephone=no\">\n" +
            "<meta name=\"viewport\" content=\"width=device-width,initial-scale=1.0, maximum-scale=1.0,minimum-scale=1.0,user-scalable=no\">\n" +
            "<title>国外高匿免费HTTP代理IP - 快代理</title>\n" +
            "\n" +
            "<meta name=\"keywords\" content=\"高匿代理,国外代理,海外代理,代理服务器,免费代理服务器,代理ip,ip代理,高匿代理ip,海外代理ip\" />\n" +
            "<meta name=\"description\" content=\"快代理专业为您提供国外高匿免费HTTP代理服务器。\" />\n" +
            "\n" +
            "\n" +
            "<link rel=\"stylesheet\" href=\"http://img.kuaidaili.com/css/all.css?v=16\" media=\"screen\" />\n" +
            "\n" +
            "<style>\n" +
            "    .tag_area2 { margin:10px 0 0px 0; text-align: left; }\n" +
            "    .tag_area2 .label { background-color:#c1c1bf;text-decoration:none; font-size:13px; padding:3px 5px 3px 5px;}\n" +
            "    .tag_area2 .label.active, .tag_area2 .label.active:hover { background-color:#468847; }\n" +
            "    .tag_area2 .label:hover { background-color:#aaa; }\n" +
            "    tbody a { color:#777; }\n" +
            "    tbody a:hover { text-decoration:none; }\n" +
            "</style>\n" +
            "\n" +
            "\n" +
            "<meta name=\"renderer\" content=\"webkit\">\n" +
            "<meta name=\"baidu-site-verification\" content=\"AO3Q6dKj9R\" />\n" +
            "<meta name=\"sogou_site_verification\" content=\"9ELczs5cQc\"/>\n" +
            "<meta name=\"360-site-verification\" content=\"feeadcad97dd7093f9abb1ee5285f031\" />\n" +
            "</head>\n" +
            "\n" +
            "<body>\n" +
            "<div class=\"body\">\n" +
            "<!--start header-->\n" +
            "\n" +
            "    <!--PC端header-->\n" +
            "    <div id=\"navigationBar\" class=\"topnav topnav-has\" style=\"z-index: 1000;\">\n" +
            "        <div class=\"navigation-inner\">\n" +
            "            <div class=\"logo\">\n" +
            "                <h1>\n" +
            "                    <a href=\"/\" class=\"logo-img\">\n" +
            "                        <img class=\"logo-lit\" src=\"http://img.kuaidaili.com/img/kdl-logo.png\" alt=\"\">\n" +
            "                    </a>\n" +
            "                </h1>\n" +
            "            </div>\n" +
            "            <div class=\"categories\" id=\"nav-con\">\n" +
            "                <ul class=\"menu\">\n" +
            "                    <li id=\"menu_free\" class=\"presentation\">\n" +
            "                        <h2><a href=\"/free/\">免费代理</a></h2>\n" +
            "                    </li>\n" +
            "                    <li id=\"menu_pricing\" class=\"presentation\">\n" +
            "                        <h2><a href=\"/pricing/\">购买代理</a></h2>\n" +
            "                    </li>\n" +
            "                    <li id=\"menu_ops\" class=\"presentation\">\n" +
            "                        <h2><a href=\"/ops/\">开放代理</a></h2>\n" +
            "                    </li>\n" +
            "                    <li id=\"menu_dps\" class=\"presentation has-menu\">\n" +
            "                        <h2><a href=\"/dps/\">私密代理</a></h2>\n" +
            "                    </li>\n" +
            "                    <li id=\"menu_kps\" class=\"presentation\">\n" +
            "                        <h2><a href=\"/kps/\">独享代理</a></h2></h2>\n" +
            "                    </li>\n" +
            "                    <li id=\"menu_fetch\" class=\"presentation has-menu\">\n" +
            "                        <h2><a href=\"/fetch/\">代理提取</a></h2>\n" +
            "                        <div class=\"menu-list\">\n" +
            "                            <ul>\n" +
            "                                <li><a href=\"/fetch/\">提取开放代理</a></li>\n" +
            "                                <li><a href=\"/dps/fetch/\">提取私密代理</a></li>\n" +
            "                            </ul>\n" +
            "                        </div>\n" +
            "                    </li>\n" +
            "                    <li id=\"menu_apidoc\" class=\"presentation has-menu\">\n" +
            "                        <h2><a href=\"/apidoc/\">API接口</a></h2>\n" +
            "                        <div class=\"menu-list\">\n" +
            "                            <ul>\n" +
            "                                <li><a href=\"/apidoc/\">开放代理API</a></li>\n" +
            "                                <li><a href=\"/dps/apidoc/\">私密代理API</a></li>\n" +
            "                            </ul>\n" +
            "                        </div>\n" +
            "                    </li>\n" +
            "                    <li id=\"menu_help\" class=\"presentation has-menu\">\n" +
            "                        <h2><a href=\"http://help.kuaidaili.com\" target=\"_blank\">帮助中心</a></h2>\n" +
            "                    </li>\n" +
            "                </ul>\n" +
            "            </div>\n" +
            "            <div class=\"operation\">\n" +
            "                <span class=\"unlogin\">\n" +
            "                    <a href=\"/login/\" class=\"qc-btn link-dl\"><span>登录</span></a>\n" +
            "                    <span class=\"stick\">|</span>\n" +
            "                    <a href=\"/regist/\" class=\"qc-btn link-dl\"><span>注册</span></a>\n" +
            "                </span>\n" +
            "                <a href=\"/usercenter/\" class=\"qc-btn link-name welcome-link\"><span class=\"welcome\"></span></a>\n" +
            "                <a href=\"/usercenter/\" class=\"qc-btn link-mc\"><span>会员中心</span></a><span id=\"noti\"></span>\n" +
            "            </div>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "\n" +
            "    <!--手机端header-->\n" +
            "    <div id=\"navigationMobileBar\" class=\"topnav-m topnav-m-has\" style=\"z-index: 101;\">\n" +
            "        <div class=\"navigation-inner\" id=\"navDefault\" style=\"transition: left 0s ease-in-out; transform: translateZ(0px); position: absolute; width: 100%; left: 0px;\">\n" +
            "            <div class=\"navigation-bar m-nav-1\" id=\"navigation-bar\">\n" +
            "                <div class=\"area-left\">\n" +
            "                    <div class=\"logo\">\n" +
            "                        <h1>\n" +
            "                            <a href=\"/\" class=\"logo-img\">\n" +
            "                                <img class=\"logo-lit\" src=\"http://img.kuaidaili.com/img/kdl-logo.png\" alt=\"\">\n" +
            "                                <img class=\"logo-dark\" src=\"http://img.kuaidaili.com/img/kdl-logo.png\" alt=\"\">\n" +
            "                            </a>\n" +
            "                        </h1>\n" +
            "                    </div>\n" +
            "                </div>\n" +
            "                <div class=\"area-right\">\n" +
            "                    <a href=\"javascript:;\" class=\"nav-mobile-button m-more\">\n" +
            "                        <span class=\"button-img\"></span>\n" +
            "                    </a>\n" +
            "                    <a href=\"javascript:;\" class=\"nav-mobile-button m-close\">\n" +
            "                        <span class=\"button-img\"></span>\n" +
            "                    </a>\n" +
            "                </div>\n" +
            "            </div>\n" +
            "            <div class=\"categories-mobile\" id=\"navDefaultSub\" style=\"opacity: 0; transition: opacity 0.4s ease-in-out; transform: translateZ(0px); display: none;\">\n" +
            "                <ul id=\"m_top_menu\" class=\"menu\">\n" +
            "                    <li class=\"presentation nav-right\"><h2><a href=\"/free/\">免费代理</a></h2></li>\n" +
            "                    <li class=\"presentation nav-right\"><h2><a href=\"/pricing/\">购买代理</a></h2></li>\n" +
            "                    <li class=\"presentation nav-right\"><h2><a href=\"/ops/\">开放代理</a></h2></li>\n" +
            "                    <li class=\"presentation nav-right\"><h2><a href=\"/dps/\">私密代理</a></h2></li>\n" +
            "                    <li class=\"presentation nav-right\"><h2><a href=\"/kps/\">独享代理</a></h2></li>\n" +
            "                    <li class=\"presentation nav-down\">\n" +
            "                        <h2><a href=\"javascript:void(0);\">代理提取</a></h2>\n" +
            "                        <div class=\"nav-down-menu\" style=\"display: none;\">\n" +
            "                            <ul class=\"nav-down-menu-detail\">\n" +
            "                                <li><a class=\"title\" href=\"/dps/fetch/\">提取私密代理</a></li>\n" +
            "                                <li><a class=\"title\" href=\"/fetch/\">提取开放代理</a></li>\n" +
            "                            </ul>\n" +
            "                        </div>\n" +
            "                    </li>\n" +
            "                    <li class=\"presentation nav-down\">\n" +
            "                        <h2><a href=\"javascript:void(0);\">API接口</a></h2>\n" +
            "                        <div class=\"nav-down-menu\" style=\"display: none;\">\n" +
            "                            <ul class=\"nav-down-menu-detail\">\n" +
            "                                <li><a class=\"title\" href=\"/dps/apidoc/\">私密代理API</a></li>\n" +
            "                                <li><a class=\"title\" href=\"/apidoc/\">开放代理API</a></li>\n" +
            "                            </ul>\n" +
            "                        </div>\n" +
            "                    </li>\n" +
            "                    <li class=\"presentation nav-right\"><h2><a href=\"http://help.kuaidaili.com\" target=\"_blank\">帮助中心</a></h2></li>\n" +
            "                </ul>\n" +
            "                <ul class=\"op\">\n" +
            "                    <li><a href=\"/usercenter/\" class=\"op-btn btn-style-2\">会员中心</a></li>\n" +
            "                </ul>\n" +
            "                <div class=\"sign-in\">\n" +
            "                    <a href=\"/usercenter/\"class=\"sign-in-links welcome-link\"><span class=\"welcome\"></span></a>\n" +
            "                    <span class=\"unlogin\">\n" +
            "                        <a id=\"m_login_btn\" href=\"/login/\" class=\"sign-in-links\">登录</a>\n" +
            "                        <span class=\"stick\">|</span>\n" +
            "                        <a id=\"m_opt_btn\" href=\"/regist/\" class=\"sign-in-links\">注册</a>\n" +
            "                    </span>\n" +
            "                </div>\n" +
            "                <div class=\"contact\">\n" +
            "                    <a href=\"tel:4000580638\" class=\"ct-num\">\n" +
            "                        <i class=\"icon\"></i>\n" +
            "                        <span>400-058-0638</span>\n" +
            "                    </a>\n" +
            "                </div>\n" +
            "            </div>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "\n" +
            "<!--end header-->\n" +
            "\n" +
            "\n" +
            "<div id=\"content\">\n" +
            "\n" +
            "\n" +
            "\n" +
            "<div class=\"con-pt\"></div>\n" +
            "<div class=\"con-body\">\n" +
            "<div>\n" +
            "    <div class=\"tag_area2\" >\n" +
            "        <a id=\"tag_inha\" class=\"label\" href=\"/free/inha/\">国内高匿代理</a> \n" +
            "        <a id=\"tag_intr\" class=\"label\" href=\"/free/intr/\">国内普通代理</a> \n" +
            "\n" +
            "        <span class=\"buy\"><a href=\"/pricing/\">购买更多代理>></a></span>\n" +
            "    </div>\n" +
            "\n" +
            "    <div id=\"list\" style=\"margin-top:15px;\">\n" +
            "        <table class=\"table table-bordered table-striped\">\n" +
            "          <thead>\n" +
            "              <tr>\n" +
            "                <th>IP</th>\n" +
            "                <th>PORT</th>\n" +
            "                <th>匿名度</th>\n" +
            "                <th>类型</th>\n" +
            "                <th>位置</th>\n" +
            "                <th>响应速度</th>\n" +
            "                <th>最后验证时间</th>\n" +
            "              </tr>\n" +
            "            </thead>\n" +
            "            <tbody>\n" +
            "                \n" +
            "                <tr>\n" +
            "                    <td data-title=\"IP\">46.101.27.174</td>\n" +
            "                    <td data-title=\"PORT\">8118</td>\n" +
            "                    <td data-title=\"匿名度\">高匿名</td>\n" +
            "                    <td data-title=\"类型\">HTTP</td>\n" +
            "                    <td data-title=\"位置\">英国   </td>\n" +
            "                    <td data-title=\"响应速度\">0.3秒</td>\n" +
            "                    <td data-title=\"最后验证时间\">2017-09-21 16:40:28</td>\n" +
            "                </tr>\n" +
            "                \n" +
            "                <tr>\n" +
            "                    <td data-title=\"IP\">104.224.137.44</td>\n" +
            "                    <td data-title=\"PORT\">80</td>\n" +
            "                    <td data-title=\"匿名度\">高匿名</td>\n" +
            "                    <td data-title=\"类型\">HTTP</td>\n" +
            "                    <td data-title=\"位置\"></td>\n" +
            "                    <td data-title=\"响应速度\">1秒</td>\n" +
            "                    <td data-title=\"最后验证时间\">2017-09-21 15:40:36</td>\n" +
            "                </tr>\n" +
            "                \n" +
            "                <tr>\n" +
            "                    <td data-title=\"IP\">178.62.123.38</td>\n" +
            "                    <td data-title=\"PORT\">8118</td>\n" +
            "                    <td data-title=\"匿名度\">高匿名</td>\n" +
            "                    <td data-title=\"类型\">HTTP</td>\n" +
            "                    <td data-title=\"位置\">英国   </td>\n" +
            "                    <td data-title=\"响应速度\">1秒</td>\n" +
            "                    <td data-title=\"最后验证时间\">2017-09-21 14:40:19</td>\n" +
            "                </tr>\n" +
            "                \n" +
            "                <tr>\n" +
            "                    <td data-title=\"IP\">24.132.146.51</td>\n" +
            "                    <td data-title=\"PORT\">80</td>\n" +
            "                    <td data-title=\"匿名度\">高匿名</td>\n" +
            "                    <td data-title=\"类型\">HTTP</td>\n" +
            "                    <td data-title=\"位置\"></td>\n" +
            "                    <td data-title=\"响应速度\">1.0秒</td>\n" +
            "                    <td data-title=\"最后验证时间\">2017-09-21 13:40:30</td>\n" +
            "                </tr>\n" +
            "                \n" +
            "                <tr>\n" +
            "                    <td data-title=\"IP\">111.155.116.210</td>\n" +
            "                    <td data-title=\"PORT\">8123</td>\n" +
            "                    <td data-title=\"匿名度\">高匿名</td>\n" +
            "                    <td data-title=\"类型\">HTTP</td>\n" +
            "                    <td data-title=\"位置\"></td>\n" +
            "                    <td data-title=\"响应速度\">3秒</td>\n" +
            "                    <td data-title=\"最后验证时间\">2017-09-21 12:40:32</td>\n" +
            "                </tr>\n" +
            "                \n" +
            "                <tr>\n" +
            "                    <td data-title=\"IP\">119.5.1.23</td>\n" +
            "                    <td data-title=\"PORT\">22</td>\n" +
            "                    <td data-title=\"匿名度\">高匿名</td>\n" +
            "                    <td data-title=\"类型\">HTTP</td>\n" +
            "                    <td data-title=\"位置\"></td>\n" +
            "                    <td data-title=\"响应速度\">2秒</td>\n" +
            "                    <td data-title=\"最后验证时间\">2017-09-21 11:40:37</td>\n" +
            "                </tr>\n" +
            "                \n" +
            "                <tr>\n" +
            "                    <td data-title=\"IP\">62.33.80.249</td>\n" +
            "                    <td data-title=\"PORT\">3129</td>\n" +
            "                    <td data-title=\"匿名度\">高匿名</td>\n" +
            "                    <td data-title=\"类型\">HTTP</td>\n" +
            "                    <td data-title=\"位置\"></td>\n" +
            "                    <td data-title=\"响应速度\">1秒</td>\n" +
            "                    <td data-title=\"最后验证时间\">2017-09-21 10:40:31</td>\n" +
            "                </tr>\n" +
            "                \n" +
            "                <tr>\n" +
            "                    <td data-title=\"IP\">182.129.240.197</td>\n" +
            "                    <td data-title=\"PORT\">9000</td>\n" +
            "                    <td data-title=\"匿名度\">高匿名</td>\n" +
            "                    <td data-title=\"类型\">HTTP</td>\n" +
            "                    <td data-title=\"位置\"></td>\n" +
            "                    <td data-title=\"响应速度\">3秒</td>\n" +
            "                    <td data-title=\"最后验证时间\">2017-09-21 09:40:31</td>\n" +
            "                </tr>\n" +
            "                \n" +
            "                <tr>\n" +
            "                    <td data-title=\"IP\">159.203.30.115</td>\n" +
            "                    <td data-title=\"PORT\">80</td>\n" +
            "                    <td data-title=\"匿名度\">高匿名</td>\n" +
            "                    <td data-title=\"类型\">HTTP</td>\n" +
            "                    <td data-title=\"位置\">加拿大   </td>\n" +
            "                    <td data-title=\"响应速度\">3秒</td>\n" +
            "                    <td data-title=\"最后验证时间\">2017-09-21 08:40:31</td>\n" +
            "                </tr>\n" +
            "                \n" +
            "                <tr>\n" +
            "                    <td data-title=\"IP\">113.105.202.184</td>\n" +
            "                    <td data-title=\"PORT\">3128</td>\n" +
            "                    <td data-title=\"匿名度\">高匿名</td>\n" +
            "                    <td data-title=\"类型\">HTTP</td>\n" +
            "                    <td data-title=\"位置\"></td>\n" +
            "                    <td data-title=\"响应速度\">0.9秒</td>\n" +
            "                    <td data-title=\"最后验证时间\">2017-09-21 07:40:33</td>\n" +
            "                </tr>\n" +
            "                \n" +
            "                <tr>\n" +
            "                    <td data-title=\"IP\">117.85.52.55</td>\n" +
            "                    <td data-title=\"PORT\">808</td>\n" +
            "                    <td data-title=\"匿名度\">高匿名</td>\n" +
            "                    <td data-title=\"类型\">HTTP</td>\n" +
            "                    <td data-title=\"位置\"></td>\n" +
            "                    <td data-title=\"响应速度\">3秒</td>\n" +
            "                    <td data-title=\"最后验证时间\">2017-09-21 06:40:33</td>\n" +
            "                </tr>\n" +
            "                \n" +
            "                <tr>\n" +
            "                    <td data-title=\"IP\">119.23.239.87</td>\n" +
            "                    <td data-title=\"PORT\">80</td>\n" +
            "                    <td data-title=\"匿名度\">高匿名</td>\n" +
            "                    <td data-title=\"类型\">HTTP</td>\n" +
            "                    <td data-title=\"位置\"></td>\n" +
            "                    <td data-title=\"响应速度\">2秒</td>\n" +
            "                    <td data-title=\"最后验证时间\">2017-09-21 05:40:25</td>\n" +
            "                </tr>\n" +
            "                \n" +
            "                <tr>\n" +
            "                    <td data-title=\"IP\">180.118.135.48</td>\n" +
            "                    <td data-title=\"PORT\">9000</td>\n" +
            "                    <td data-title=\"匿名度\">高匿名</td>\n" +
            "                    <td data-title=\"类型\">HTTP</td>\n" +
            "                    <td data-title=\"位置\"></td>\n" +
            "                    <td data-title=\"响应速度\">2秒</td>\n" +
            "                    <td data-title=\"最后验证时间\">2017-09-21 04:40:19</td>\n" +
            "                </tr>\n" +
            "                \n" +
            "                <tr>\n" +
            "                    <td data-title=\"IP\">106.75.3.237</td>\n" +
            "                    <td data-title=\"PORT\">80</td>\n" +
            "                    <td data-title=\"匿名度\">高匿名</td>\n" +
            "                    <td data-title=\"类型\">HTTP</td>\n" +
            "                    <td data-title=\"位置\"></td>\n" +
            "                    <td data-title=\"响应速度\">3秒</td>\n" +
            "                    <td data-title=\"最后验证时间\">2017-09-21 03:40:21</td>\n" +
            "                </tr>\n" +
            "                \n" +
            "                <tr>\n" +
            "                    <td data-title=\"IP\">120.77.173.13</td>\n" +
            "                    <td data-title=\"PORT\">80</td>\n" +
            "                    <td data-title=\"匿名度\">高匿名</td>\n" +
            "                    <td data-title=\"类型\">HTTP</td>\n" +
            "                    <td data-title=\"位置\"></td>\n" +
            "                    <td data-title=\"响应速度\">2秒</td>\n" +
            "                    <td data-title=\"最后验证时间\">2017-09-21 02:40:30</td>\n" +
            "                </tr>\n" +
            "                \n" +
            "            </tbody>\n" +
            "        </table>\n" +
            "        <p>注：表中响应速度是中国测速服务器的测试数据，仅供参考。响应速度根据你机器所在的地理位置不同而有差异。</p>\n" +
            "\n" +
            "        <div id=\"listnav\">\n" +
            "        <ul><li>第</li><li><a href=\"/free/outha/1/\" class=\"active\">1</a></li><li><a href=\"/free/outha/2/\">2</a></li><li><a href=\"/free/outha/3/\">3</a></li><li><a href=\"/free/outha/4/\">4</a></li><li><a href=\"/free/outha/5/\">5</a></li><li>...</li><li><a href=\"/free/outha/1836/\">1836</a></li><li><a href=\"/free/outha/1837/\">1837</a></li><li>页</li></ul>\n" +
            "        </div>\n" +
            "\n" +
            "        <div class=\"btn center be-f\"><a id=\"tobuy\" href=\"/pricing/\" target=\"_blank\">购买更多代理</a></div>\n" +
            "    </div>\n" +
            "</div>\n" +
            "</div>\n" +
            "\n" +
            "</div>\n" +
            "\n" +
            "\n" +
            "<div class=\"footer\">\n" +
            "    <div class=\"con-body clearfix\">\n" +
            "        <div class=\"footer-left\">\n" +
            "            <a href=\"/\" class=\"logo-link\"><img height=\"35\" src=\"/img/footer-logo.png\"/></a>\n" +
            "        <ul class=\"foot-link clearfix\">\n" +
            "            <li><a href=\"/about/\">关于我们</a><span>|</span></li>\n" +
            "            <li><a href=\"/contract/\" target=\"_blank\">服务条款</a><span>|</span></li>\n" +
            "            <li><a href=\"/law/\" target=\"_blank\">法律声明</a><span>|</span></li>\n" +
            "            <li><a href=\"/sitemap.xml\">网站地图</a><span>|</span></li>\n" +
            "            <li><a href=\"http://help.kuaidaili.com\" target=\"_blank\">帮助中心</a></li>\n" +
            "        </ul>\n" +
            "        <p class=\"foot-owner\">© 2013-2017 Kuaidaili.com 版权所有 <a href=\"http://www.miitbeian.gov.cn/state/outPortal/loginPortal.action\" target=\"_blank\">京ICP备16054786号</a></p>\n" +
            "        </div>\n" +
            "        <div class=\"foot-safe clearfix\">\n" +
            "            <a class=\"safe01\" href=\"https://ss.knet.cn/verifyseal.dll?sn=e161117110108652324qkr000000&ct=df&a=1&pa=0.3305956236561214\" target=\"_blank\"></a>\n" +
            "            <a class=\"safe02\" href=\"http://webscan.360.cn/index/checkwebsite/url/www.kuaidaili.com\" target=\"_blank\"></a>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "</div>\n" +
            "<div class=\"m-footer\">\n" +
            "    <div class=\"con-body\">\n" +
            "        <ul class=\"foot-link clearfix\">\n" +
            "            <li><a href=\"/about/\">关于我们</a></li>\n" +
            "            <li><a href=\"http://help.kuaidaili.com/contract/\" target=\"_blank\">服务条款</a></li>\n" +
            "            <li><a href=\"http://help.kuaidaili.com/law/\" target=\"_blank\">法律声明</a></li>\n" +
            "            <li><a href=\"/sitemap.xml\">网站地图</a></li>\n" +
            "            <li><a href=\"http://help.kuaidaili.com\" target=\"_blank\">帮助中心</a></li>\n" +
            "        </ul>\n" +
            "        <p class=\"foot-owner\">©2013-2017 Kuaidaili.com 版权所有</p>\n" +
            "        <a class=\"foot-owner\">京ICP备16054786号</a>\n" +
            "        <div class=\"foot-safe clearfix\">\n" +
            "            <a class=\"safe01\" href=\"https://ss.knet.cn/verifyseal.dll?sn=e161117110108652324qkr000000&ct=df&a=1&pa=0.3305956236561214\" target=\"_blank\"></a>\n" +
            "            <a class=\"safe02\" href=\"http://webscan.360.cn/index/checkwebsite/url/www.kuaidaili.com\" target=\"_blank\"></a>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "</div>\n" +
            "<div id=\"mNavMask\" style=\"position: fixed; top: 0px; left: 0px; bottom: 0; right: 0; width: 100%; z-index: 100; height: 100%; display: none; background: rgba(0, 0, 0, 0.74902);\"></div>\n" +
            "<ul class=\"onMShow\">\n" +
            "    <li>\n" +
            "  \n" +
            "    <a class=\"online-chat\" href=\"javascript:void(0);\"><br>\n" +
            "  \n" +
            "            <span class=\"bt3\"></span>\n" +
            "            <div class=\"two\">在线咨询QQ: 3255904996<br>周一至周六 9:00-18:00</div>\n" +
            "        </a>\n" +
            "    </li>\n" +
            "    <li>\n" +
            "        <a href=\"tel:4000580638\"><br>\n" +
            "            <span class=\"bt2\"></span>\n" +
            "            <div class=\"two\">客服电话：400-058-0638<br>周一至周六 9:00-18:00</div>\n" +
            "        </a>\n" +
            "    </li>\n" +
            "    <li>\n" +
            "        <a href=\"/support/\"><br>\n" +
            "            <span class=\"bt1\"></span>\n" +
            "            <div>提交工单</div>\n" +
            "        </a>\n" +
            "    </li>\n" +
            "</ul>\n" +
            "<a href=\"javascript:void(0);\" id=\"top_btn\" class=\"label btt\" style=\"display:none;\"><span class=\"btn-top\"></span></a>\n" +
            "\n" +
            "</div>\n" +
            "\n" +
            "\n" +
            "<script type=\"text/javascript\" src=\"http://img.kuaidaili.com/js/all.js?v=2\"></script>\n" +
            "\n" +
            "<script type=\"text/javascript\">\n" +
            "$(\"#tag_outha\").addClass(\"active\")\n" +
            "$(document).ready(function() {\n" +
            "});\n" +
            "</script>\n" +
            "\n" +
            "<script type=\"text/javascript\">\n" +
            "var chat_url = \"https://static.meiqia.com/dist/standalone.html?_=t&eid=72194\";\n" +
            "$(document).ready(function() {\n" +
            "    $('.online-chat').click(function () {\n" +
            "        if($.os.ios || $.os.android){\n" +
            "            window.open(chat_url);\n" +
            "        }\n" +
            "        else{\n" +
            "            var from_left = document.documentElement.clientWidth - 760;\n" +
            "            var from_top = 300;\n" +
            "            if (window.screen.height < 1000) from_top = 200;\n" +
            "            window.open(chat_url, \"_blank\", \"location=0, status=0, left=\"+from_left+\", top=\"+from_top+\", width=700, height=540\");\n" +
            "        }\n" +
            "    });\n" +
            "});\n" +
            "</script>\n" +
            "<script type=\"text/javascript\">\n" +
            "var menu = \"menu_free\";\n" +
            "if(menu) $('#'+menu).addClass('active');\n" +
            "var ucm = \"\";\n" +
            "if(ucm){\n" +
            "    $('#ucm_'+ucm).addClass('active');\n" +
            "    $('#ucm_'+ucm+' a i').addClass('icon-white');\n" +
            "}\n" +
            "\n" +
            "var _hmt = _hmt || [];\n" +
            "(function() {\n" +
            "  var hm = document.createElement(\"script\");\n" +
            "  hm.src = \"//hm.baidu.com/hm.js?7ed65b1cc4b810e9fd37959c9bb51b31\";\n" +
            "  var s = document.getElementsByTagName(\"script\")[0]; \n" +
            "  s.parentNode.insertBefore(hm, s);\n" +
            "})();\n" +
            "\n" +
            "(function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){\n" +
            "(i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),\n" +
            "m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)\n" +
            "})(window,document,'script','http://img.kuaidaili.com/ga/ga.js','ga');\n" +
            "ga('create', 'UA-76097251-1', 'auto');\n" +
            "ga('send', 'pageview');\n" +
            "\n" +
            "</script>\n" +
            "\n" +
            "\n" +
            "\n" +
            "\n" +
            "<!--[if lt IE 9]><link rel=\"stylesheet\" href=\"http://img.kuaidaili.com/css/ie.css?v=9\" media=\"screen\" /><![endif]-->\n" +
            "</body>\n" +
            "</html>\n";
}
