package com.songpu.easygraph.core;

import cn.hutool.core.util.ObjectUtil;
import lombok.Data;

import java.util.*;


/**
 * @title: Node
 * @description: 节点
 * @author: weiqi.song
 * @create: 2020-09-03 09:38
 **/
@Data
public class Node {
    Map<String,List<String>> outputMap = new HashMap<>();
    Map<String,List<String>> inputMap = new HashMap<>();
    Map<String,NodeValue> valuesMap = new HashMap<>();
    Set<String> ids = new HashSet<>();
    public void addInput(String id,String node) {
        List<String> input = inputMap.get(id);
        if(ObjectUtil.isNull(input)){
            input = new ArrayList<>();
            inputMap.put(id,input);
        }
        input.add(node);
    }
    public void addOutput(String id,String node) {
        List<String> output = outputMap.get(id);
        if(ObjectUtil.isNull(output)){
            output = new ArrayList<>();
            outputMap.put(id,output);
        }
        output.add(node);
    }

    public void addSelf(String id){
        ids.add(id);
    }

    public List<String> getOutput(String id){
        return Optional.ofNullable(outputMap.get(id)).orElse(new ArrayList<>());
    }
    public List<String> getInput(String id){
        return Optional.ofNullable(inputMap.get(id)).orElse(new ArrayList<>());
    }

}
