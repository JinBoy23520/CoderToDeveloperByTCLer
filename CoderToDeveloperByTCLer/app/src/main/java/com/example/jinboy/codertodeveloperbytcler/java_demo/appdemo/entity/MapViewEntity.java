package com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhangchanghua on 2017/6/16.
 */

public class MapViewEntity implements Serializable {
    public String overviewName;//区块名称
    public String overviewSubName;//区块子名称
    public List<Area> areaList;//区域列表数据

    public static class Area implements Serializable {
        public int ranking;//排名
        public String areaName;//区域名称
        public int areaCount;//数量
    }
}
