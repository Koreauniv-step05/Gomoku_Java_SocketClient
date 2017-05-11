package com.asuscomm.yangyinetwork.gomoku_spring_socket_client.gomoku_ai.ai.algorithms.domain;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jaeyoung on 2017. 5. 10..
 */
@Slf4j
public class BaseTree<T extends BaseTree> {
    protected boolean isRoot;
    protected int stoneType;
    protected boolean extended;

    protected List<T> children;
    protected T mother;

    public BaseTree() {
        this.isRoot = true;
        this.extended = false;
        this.children = new ArrayList<T>();
    }

    public BaseTree(T mother) {
        this.isRoot = false;
        this.mother = mother;
        this.children = new ArrayList<T>();
        this.extended = false;
    }

    public T getRoot() {
        T root = (T)this;
        while(true) {
            if (!root.isRoot) {
                root = (T)root.mother;
            } else {
                return root;
            }
        }
    }

    public T getMothersChild() {
        T mothersChild = (T)this;
        if(mothersChild.isRoot) {
            return null;
        }
        while(true) {
            if (!mothersChild.mother.isRoot) {
                mothersChild = (T)mothersChild.mother;
            } else {
                return mothersChild;
            }
        }
    }



    public List<T> getChildren() {
        return this.children;
    }

    public int getChildrenSize() {
        return this.children.size();
    }

    @Override
    public String toString() {
        return "BaseTree{" +
                "children=" + children.toString() +
                ", stoneType=" + stoneType +
                ", extended=" + extended +
                '}';
    }

    public int getStoneType() {
        return stoneType;
    }
}
