package com.songpu.easygraph.core;


import lombok.Data;

/**
 * @title: NodeValue
 * @description: 节点值
 * @author: weiqi.song
 * @create: 2020-12-24 15:47
 **/
@Data
public class NodeValue<T> {
    String id;
    String parentId;
    T value;
    BaseWorker worker;
}
