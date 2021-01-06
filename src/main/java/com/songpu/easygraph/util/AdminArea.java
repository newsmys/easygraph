package com.songpu.easygraph.util;

import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @title: AdminArea
 * @description: 行政区划
 * @author: weiqi.song
 * @create: 2020-12-03 10:18
 **/
@Data
public class AdminArea {

    public AdminArea(String name, String superiorCode, String code) {
        this.name = name;
        this.superiorCode = superiorCode;
        this.code = code;
    }

    String code;

    Integer level;

    String name;

    List<AdminArea> areaList;

    String superiorCode;

    String fullName;

    String childrenSet;


    public static List<AdminArea> buildList() {
        List<AdminArea> list = Arrays.asList(demoStr.split(";")).stream().map(obj->{
            String[] input = obj.split(",");
            AdminArea adminArea = new AdminArea(input[0],input[1],input[2]);
            return adminArea;
        }).collect(Collectors.toList());
        return list;
    }

    public static String demoStr =
            "湖南省,2,430000;" +
            "长沙市,430000,430100;" +
            "芙蓉区,430100,430102;" +
            "天心区,430100,430103;" +
            "岳麓区,430100,430104;" +
            "开福区,430100,430105;" +
            "雨花区,430100,430111;" +
            "长沙县,430100,430121;" +
            "望城县,430100,430122;" +
            "宁乡县,430100,430124;" +
            "浏阳市,430100,430181;" ;
}
