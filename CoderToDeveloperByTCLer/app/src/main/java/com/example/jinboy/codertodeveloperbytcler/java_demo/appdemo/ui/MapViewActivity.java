package com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.R;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.entity.MapViewEntity;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui.view.ChinaMapView;
import com.example.jinboy.codertodeveloperbytcler.java_demo.appdemo.ui.view.MapView;

import java.util.ArrayList;
import java.util.List;


public class MapViewActivity extends AppCompatActivity {
    private List<MapViewEntity.Area> areaList;
    ChinaMapView lView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_view_activity);
        MapView mapView = (MapView) findViewById(R.id.mapview);
        getData();
        mapView.init(areaList);
        lView = (ChinaMapView)findViewById(R.id.vp);
        lView.setOnProvinceSelectedListener(new ChinaMapView.OnProvinceSelectedListener() {
            @Override
            public void onprovinceSelected(ChinaMapView.Area pArea) {
                Toast.makeText(MapViewActivity.this,"您选择了-->"+pArea.name(),Toast.LENGTH_SHORT).show();
            }
        });
        lView.setMapColor(Color.BLUE);
        lView.setPaintColor(ChinaMapView.Area.HeBei, Color.rgb(0xfa,0x74,0x01),true);
        lView.setPaintColor(ChinaMapView.Area.GuangDong, Color.rgb(0xd2,0x00,0x7f),true);
        lView.setPaintColor(ChinaMapView.Area.BeiJing, Color.rgb(0x00,0x6f,0xbf),true);
        lView.setPaintColor(ChinaMapView.Area.SiChuan, Color.rgb(0x00,0x9c,0x85),true);
        lView.setPaintColor(ChinaMapView.Area.AnHui, Color.rgb(0x8f,0xc4,0x1e),true);
//        lView.selectAProvince(ChinaMapView.Area.XinJiang);
    }

    public void getData(){
        areaList = new ArrayList<>();
        MapViewEntity.Area area1= new MapViewEntity.Area();
        area1.areaName = "武汉";
        area1.areaCount = 5;
        area1.ranking = 1;
        MapViewEntity.Area area2= new MapViewEntity.Area();
        area2.areaName = "深圳";
        area2.areaCount = 4;
        area2.ranking = 2;
        MapViewEntity.Area area3= new MapViewEntity.Area();
        area3.areaName = "北京";
        area3.areaCount = 3;
        area3.ranking = 3;
        MapViewEntity.Area area4= new MapViewEntity.Area();
        area4.areaName = "上海";
        area4.areaCount = 2;
        area4.ranking = 4;
        MapViewEntity.Area area5= new MapViewEntity.Area();
        area5.areaName = "惠州";
        area5.areaCount = 1;
        area5.ranking = 5;
        areaList.add(area1);
        areaList.add(area2);
        areaList.add(area3);
        areaList.add(area4);
        areaList.add(area5);
    }
}
