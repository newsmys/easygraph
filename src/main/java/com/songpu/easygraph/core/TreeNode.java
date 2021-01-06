package com.songpu.easygraph.core;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @title: TreeNode
 * @description: 树形流式处理
 * @author: weiqi.song
 * @create: 2020-07-08 20:02
 **/

@Data
public class TreeNode<T>{

	String id;
	T object;
	List<String> inputNames;
	List<String> outputNames;
	Boolean isFired = Boolean.FALSE;
	Object result;
	Map<String,Object> deliver = new HashMap<>();
	BaseWorker worker;
	public void addDeliver(String id,Object result) {
		deliver.put(id,result);
	}
}
