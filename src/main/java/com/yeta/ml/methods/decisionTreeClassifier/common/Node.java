package com.yeta.ml.methods.decisionTreeClassifier.common;

import java.util.Arrays;

/**
 * 决策树的节点
 * Created by YETA666 on 2018/3/23 0023.
 */
public class Node {
    //当前节点名
    private String nodeName;
    //父节点
    private Node parentNode;
    //父节点名
    private String parentNodeName;
    //父节点属性
    private String parentNodeAttr;
    //子节点
    private Node[] childNodes;
    //当前节点属性
    private Object[] nodeAttrs;

    public Node() {
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public Node getParentNode() {
        return parentNode;
    }

    public void setParentNode(Node parentNode) {
        this.parentNode = parentNode;
    }

    public String getParentNodeName() {
        return parentNodeName;
    }

    public void setParentNodeName(String parentNodeName) {
        this.parentNodeName = parentNodeName;
    }

    public String getParentNodeAttr() {
        return parentNodeAttr;
    }

    public void setParentNodeAttr(String parentNodeAttr) {
        this.parentNodeAttr = parentNodeAttr;
    }

    public Node[] getChildNodes() {
        return childNodes;
    }

    public void setChildNodes(Node[] childNodes) {
        this.childNodes = childNodes;
    }

    public Object[] getNodeAttrs() {
        return nodeAttrs;
    }

    public void setNodeAttrs(Object[] nodeAttrs) {
        this.nodeAttrs = nodeAttrs;
    }

    @Override
    public String toString() {
        return "Node{" +
                "nodeName='" + nodeName + '\'' +
                ", parentNode=" + parentNode +
                ", parentNodeName='" + parentNodeName + '\'' +
                ", parentNodeAttr='" + parentNodeAttr + '\'' +
                ", childNodes=" + Arrays.toString(childNodes) +
                ", nodeAttrs=" + Arrays.toString(nodeAttrs) +
                '}';
    }
}
