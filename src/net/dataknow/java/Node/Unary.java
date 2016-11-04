package net.dataknow.java.Node;


import java.util.ArrayList;

public class Unary extends Tree{

    public Unary(type unary_type){
        this.treeType = unary_type;
        child = new ArrayList<Tree>();

    }

    @Override
    public Tree eval() {

        Tree tree = new Binary(type.time);
        child.add(new Decimal_Literal(-1));
        child.add(this.child.get(0).eval());

        return tree.eval();

    }
}
