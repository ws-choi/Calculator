package net.dataknow.java;

import net.dataknow.java.Node.Tree;

public class Main {

    public static void main(String[] args) throws Exception {

        Tree tree = SimpleParser.parse("123.1 + .23 /3 +(1+2)");

        System.out.println();

    }
}
