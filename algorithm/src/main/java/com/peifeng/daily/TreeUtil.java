package com.peifeng.daily;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.*;

public class TreeUtil {


    public static void main(String[] args) {
        List<TreeDto> treeData = TreeUtil.getTreeData(getData(), null);



        System.out.println(JSONArray.fromObject(treeData));
    }

    public static List<TreeDto> getTreeData(List<Map<String, String>> data, Map<String, String> config){

        List<TreeDto> treeData = new ArrayList<>();

        String id = "id";
        String pid = "pid";
        String children = "children";

        List<Map<String, String>> data1 = getData();
        Iterator<Map<String, String>> it = data1.iterator();

        while(it.hasNext()){
            Map<String, String> next = it.next();
            TreeDto treeDto = new TreeDto();
            treeDto.setId(next.get(id));
            treeDto.setPid(next.get(pid));
            treeData.add(treeDto);
        }


        List<TreeDto> result = new ArrayList<>();
        for (int i = 0; i < treeData.size(); i++) {
            TreeDto element = treeData.get(i);
            String element_id = element.getId();
            if("".equals(element.getPid())){
                result.add(element);
            }
            List<TreeDto> children1 = element.getChildren();
            for (int j = 0; j < treeData.size(); j++) {
                TreeDto treeDto = treeData.get(j);
                if(element_id.equals(treeDto.getPid())){
                    children1.add(treeDto);
                }
            }
        }
        return result;
    }


    public static List<Map<String, String>> getData(){
        List<Map<String, String>> data = new ArrayList<>();

        Map<String, String> map1 = new HashMap<>();
        map1.put("id","01");
        map1.put("pid","");
        map1.put("name","测试01");
        data.add(map1);

        Map<String, String> map2 = new HashMap<>();
        map2.put("id","01_1");
        map2.put("pid","01");
        map2.put("name","测试01_1");
        data.add(map2);

        Map<String, String> map3 = new HashMap<>();
        map3.put("id","01_2");
        map3.put("pid","01");
        map3.put("name","测试01_2");
        data.add(map3);

        Map<String, String> map4 = new HashMap<>();
        map4.put("id","01_3");
        map4.put("pid","01");
        map4.put("name","测试01_3");
        data.add(map4);

        Map<String, String> map5 = new HashMap<>();
        map5.put("id","05");
        map5.put("pid","");
        map5.put("name","测试05");
        data.add(map5);

        Map<String, String> map6 = new HashMap<>();
        map6.put("id","05_6");
        map6.put("pid","05");
        map6.put("name","测试05_6");
        data.add(map6);

        Map<String, String> map7 = new HashMap<>();
        map7.put("id","05_6_7");
        map7.put("pid","05_6");
        map7.put("name","测试05_6_7");
        data.add(map7);

        return data;
    }
}
