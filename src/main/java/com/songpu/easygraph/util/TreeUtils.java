package com.songpu.easygraph.util;

import com.songpu.easygraph.action.ChildrenQuery;
import com.songpu.easygraph.action.NameJoin;
import com.songpu.easygraph.core.Node;
import com.songpu.easygraph.core.NodeValue;
import com.songpu.easygraph.core.TreeNode;
import com.songpu.easygraph.rule.BaseRule;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.InferenceRulesEngine;
import org.jeasy.rules.core.RulesEngineParameters;

import java.util.List;
import java.util.stream.Collectors;



/**
 * @title: TreeUtil
 * @description: 将树转换成流式处理/前序遍历树
 * @author: weiqi.song
 * @create: 2020-07-08 16:03
 **/
public class TreeUtils<T> {

	public static RulesEngine inferenceRulesEngine = new InferenceRulesEngine(new RulesEngineParameters().skipOnFirstFailedRule(true));


	public static<T> TreeUtils<T> getInstance() {
		return new TreeUtils<T>();
	}
	/**
	 * 构造一个utils对象
	 * @return
	 */
	public List<T> process(List<NodeValue<T>> nodeValues,List<NodeValue<T>> nodeRefs,Boolean isReverse) {
		Facts facts = new Facts();
		Node node  = isReverse?traverseReverse(nodeRefs):traverse(nodeRefs);
		Rules rules = new Rules();
		int index = nodeValues.size();
		for(NodeValue value: nodeValues) {
			TreeNode treeNode = new TreeNode();
			String id = value.getId();
			treeNode.setId(id);
			treeNode.setInputNames(node.getInput(id));
			treeNode.setOutputNames(node.getOutput(id));
			treeNode.setObject(value.getValue());
			treeNode.setWorker(value.getWorker());
			BaseRule rule = new BaseRule();
			rule.setPriority(--index);
			rule.setName(id);
			rules.register(rule);
			facts.put(id,treeNode);
		}
		inferenceRulesEngine.fire(rules,facts);
		return nodeValues.stream().map(o->o.getValue()).collect(Collectors.toList());
	}
	/**
	 * 构造一个utils对象
	 * @return
	 */
	public void process(List<NodeValue<T>> nodeValues) {
		process(nodeValues,nodeValues,Boolean.FALSE);
	}

    public void process(List<NodeValue<T>> nodeValues,List<NodeValue<T>> nodeRefs) {
		process(nodeValues,nodeRefs,Boolean.FALSE);
	}

	 public void process(List<NodeValue<T>> nodeValues,Boolean isReverse) {
		process(nodeValues,nodeValues,isReverse);
	}


	private  Node traverse(List<NodeValue<T>> nodeRefs){
		Node node = new Node();
		for(NodeValue o: nodeRefs) {
			String id = o.getId();
			String parentId = o.getParentId();
			node.addInput(parentId,id);
			node.addOutput(id,parentId);
			node.addSelf(id);
		}
		return node;
	}


	private  Node traverseReverse(List<NodeValue<T>> nodeRefs){
		Node node = new Node();
		for(NodeValue o: nodeRefs) {
			String id = o.getId();
			String parentId = o.getParentId();
			node.addOutput(parentId,id);
			node.addInput(id,parentId);
			node.addSelf(id);
		}
		return node;
	}


	public static void main(String[] args ) {
		ChildrenQuery childrenQuery = new ChildrenQuery();
		NameJoin nameJoin = new NameJoin();
		List<NodeValue<AdminArea>> list= AdminArea.buildList().stream().map(area->{
			NodeValue<AdminArea> value = new NodeValue<>();
			value.setId(area.getCode());
			value.setParentId(area.getSuperiorCode());
			value.setValue(area);
			value.setWorker(nameJoin);
			return value;
		}).collect(Collectors.toList());
		TreeUtils.<AdminArea>getInstance().process(list,Boolean.TRUE);
	}

}




