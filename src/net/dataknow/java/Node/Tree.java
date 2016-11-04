package net.dataknow.java.Node;

import java.util.List;

public abstract class Tree {

    public enum type {
        Binary_0, Binary_1, Binary_2, Bracket, Decimal, Unary,
        plus, minus, time, divide, power, bracket_left, bracket_right
    }
    public type treeType;
    public String Symbol;
    public boolean isLeaf = false; //default
    public List<Tree> child;
    public abstract double eval();

    @Override
    public String toString() {
        return Symbol + " " + child_string(child);
    }

    protected String child_string(List<Tree> children){
        StringBuilder builer = new StringBuilder();
        child.iterator();

        for (Tree child : children)
            builer.append(child.toString() + " ");

        return builer.toString();
    }
}

