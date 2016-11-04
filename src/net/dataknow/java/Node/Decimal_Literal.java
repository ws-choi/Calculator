package net.dataknow.java.Node;

/**
 * Created by wschoi on 2016-11-04.
 */
public class Decimal_Literal extends Tree {

    double decimal;

    public Decimal_Literal(double decimal){

        this.treeType = type.Decimal;
        this.decimal = decimal;
        isLeaf = true;
        child = null;
        Symbol = Double.toString(decimal);

    }
    @Override
    public Tree eval() {
        return this;
    }
    @Override
    public String toString() {
        return Symbol;
    }
}
