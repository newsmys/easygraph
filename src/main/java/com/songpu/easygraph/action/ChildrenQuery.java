package com.songpu.easygraph.action;


import cn.hutool.core.collection.CollectionUtil;
import com.songpu.easygraph.core.BaseWorker;
import com.songpu.easygraph.util.AdminArea;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;


/**
 * @title: ChildrenQuery
 * @description: 查询子节点的ID集合
 * @author: weiqi.song
 * @create: 2020-11-10 14:37
 **/
@AllArgsConstructor
public class ChildrenQuery extends BaseWorker<AdminArea> {

    @Override
    public Object worker(List o) {
        if(CollectionUtil.isNotEmpty(o)){
            object.setChildrenSet((String) o.stream().map(String::valueOf).collect(Collectors.joining(",")));
        }
        return object.getName();
    }
}
