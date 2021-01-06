package com.songpu.easygraph.rule;

import cn.hutool.core.util.ObjectUtil;
import com.songpu.easygraph.core.BaseWorker;
import com.songpu.easygraph.core.TreeNode;
import lombok.Data;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @title: Convergence
 * @description: 融合操作类
 * @author: weiqi.song
 * @create: 2020-08-26 11:16
 **/
@Data
public class BaseRule implements Rule {

    String name;

    int priority;

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return "null";
    }

    @Override
    public boolean evaluate(Facts facts){
        TreeNode node = facts.get(name);
        List<String> inputNames = node.getInputNames();
        Boolean flag = !node.getIsFired();
        for(String inputName :inputNames){
            TreeNode preNode = facts.get(inputName);
            if(ObjectUtil.isNotNull(preNode)){
                flag &= preNode.getIsFired();
            }
        }
        return flag;
    }


    @Override
    public void execute(Facts facts){
        TreeNode node = facts.get(name);
        node.setIsFired(Boolean.TRUE);
        List list = new ArrayList();
        Map<String,Object> deliver = node.getDeliver();
        list.addAll(deliver.values());
        BaseWorker worker = node.getWorker();
        worker.setObject(node.getObject());
        worker.setDeliver(list);
        Object result = worker.action();
        List<String> outputNames = node.getOutputNames();
        outputNames.stream().forEach(outputName -> {
            TreeNode postNode = facts.get(outputName);
            if(ObjectUtil.isNotNull(postNode)){
                postNode.addDeliver(name,result);
            }
        });
    }

    @Override
    public int compareTo(final Rule rule) {
        if (getPriority() < rule.getPriority()) {
            return -1;
        } else if (getPriority() > rule.getPriority()) {
            return 1;
        } else {
            return getName().compareTo(rule.getName());
        }
    }
}
