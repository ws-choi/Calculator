package net.dataknow.java.Node;

import java.util.ArrayList;

public class Binary extends Tree {

    int priority;

    public Binary(Tree.type binary_type){
        this.Symbol=Symbol;
        this.treeType = binary_type;
        this.child = new ArrayList<Tree>(2);

    }
    @Override
    public double eval() {

        switch(treeType){
            case plus:
                return child.get(0).eval() + child.get(1).eval();
            case minus:
                return child.get(0).eval() - child.get(1).eval();
            case time:
                return child.get(0).eval() * child.get(1).eval();
            case divide:
                return child.get(0).eval() / child.get(1).eval();
            case power:
                return Math.pow(child.get(0).eval() , child.get(1).eval());

        }

    }
}
