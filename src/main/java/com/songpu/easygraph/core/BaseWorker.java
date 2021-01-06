package com.songpu.easygraph.core;


import lombok.Data;

import java.util.List;

/**
 * @title: BaseWorker
 * @description: 基础工人
 * @author: weiqi.song
 * @create: 2020-07-22 15:55
 **/
@Data
public abstract class BaseWorker<T> {
    /**
     * 上级节点向本级节点传递的数据
     */
    private List deliver;

    /**
     * 节点数据
     */
    public T object;

    public Object action(){
        return worker(deliver);
    }

    /**
     * 继承此类并且实现此方法实现自己的业务逻辑
     * @param deliver 上级向本级传递的内容
     * @return 本级向下级传递的内容
     */
    public abstract Object worker(List deliver);
}
