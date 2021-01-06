package com.songpu.easygraph.action;


import cn.hutool.core.collection.CollectionUtil;
import com.songpu.easygraph.core.BaseWorker;
import com.songpu.easygraph.util.AdminArea;
import lombok.AllArgsConstructor;

import java.util.List;



/**
 * @title: ChildrenQuery
 * @description: 查询子节点的ID集合
 * @author: weiqi.song
 * @create: 2020-11-10 14:37
 **/
@AllArgsConstructor
public class NameJoin extends BaseWorker<AdminArea> {

    @Override
    public Object worker(List o) {
        if(CollectionUtil.isNotEmpty(o)){
            object.setFullName(CollectionUtil.getFirst(o)+"/"+object.getName());
        }else{
            object.setFullName(object.getName());
        }
        return object.getFullName();
    }
}
