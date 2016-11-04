package net.dataknow.java;

import net.dataknow.java.Node.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

import static net.dataknow.java.Node.Tree.type.*;

public class SimpleParser {

    private static final int NOT_DEFINED = -1;
    private static final int START = 0;
    private static final int MAKE_UNARY = 1;
    private static final int MAKE_BRACKET = 2;
    private static final int MAKE_DECIMAL = 3;


    public static Tree parse (String string) throws Exception {


        StringTokenizer st = new StringTokenizer(string, "*/-+()", true);
        Stack<Tree> parseStack = new Stack<Tree>();

        int STATE = START;

        while(st.hasMoreTokens()){

            String token = st.nextToken();

            Tree.type token_type = get_Type(token);

            switch (STATE){

                case START:
                    int next_state = handle_START(parseStack, token_type, token);
                    break;

                case MAKE_UNARY:
                    int next_state = handle_UNARY(parseStack, token_type, token, st);
                    break;


            }




            System.out.println();

        }


        return null;
    }

    private static int handle_UNARY(Stack<Tree> parseStack, Tree.type token_type, String token, StringTokenizer st) {
        Unary tree;
        switch (token_type) {
            case plus:
                return MAKE_UNARY;
            case minus:
                tree = (Unary) parseStack.pop();
                tree.treeType = (tree.treeType == plus ) ? minus : plus;
                parseStack.push(tree);
                return MAKE_UNARY;
            case time:
            case divide:
            case power:
                return NOT_DEFINED;

            case bracket_left:
                parseStack.add(new Bracket());//TODO
                return MAKE_BRACKET;

            case bracket_right: //TODO
                break;

            case Decimal:
                tree = (Unary) parseStack.pop();
                tree.child.add(new Decimal_Literal(Double.parseDouble(token)));
                parseStack.push(tree.eval());
                return MAKE_DECIMAL;
        }

        return NOT_DEFINED;
    }

    private static int handle_START(Stack<Tree> parseStack, Tree.type token_type, String token) throws Exception {

        switch(token_type){
            case plus:
            case minus:
                parseStack.add(new Unary(token_type));
                return MAKE_UNARY;

            case time:
            case divide:
            case power:
                return NOT_DEFINED;

            case bracket_left:
                parseStack.add(new Bracket());
                return MAKE_BRACKET;

            case bracket_right:
                return NOT_DEFINED;

            case Decimal:
                parseStack.add(new Decimal_Literal(Double.parseDouble(token)));
                return MAKE_DECIMAL;


        }
        return NOT_DEFINED;
    }
    /*
            switch(token_type){
            case plus:
            case minus:
                break;
            case time:
            case divide:
                break;
            case power:
                break;
            case bracket_left:
                break;
            case bracket_right:
                break;
            case Decimal:
                break;
/*
     */
/*
    private static Tree make_Node(String buf) {

        if(buf.length() == 1){
            if(buf.equals("+")) return new Binary("+");
            else if(buf.equals("-"))return new Binary("-");
            else if(buf.equals("*"))return new Binary("*");
            else if(buf.equals("/"))return new Binary("/");
            else if(buf.equals("^"))return new Binary("^");
            else if(buf.equals("("))return new Bracket();


        }

        try{
            double res = Double.parseDouble(buf);
            return new Decimal_Literal(res);
        }catch(Exception e){
        }

        return null;
    }*/

    public static Tree.type  get_Type (String buf) {

        if(buf.length() == 1){
            if(buf.equals("+")) return Tree.type.Binary_2;
            else if(buf.equals("-"))return Tree.type.Binary_2;
            else if(buf.equals("*"))return Tree.type.Binary_1;
            else if(buf.equals("/"))return Tree.type.Binary_1;
            else if(buf.equals("^"))return Binary_0;
            else if(buf.equals("("))return Tree.type.Bracket;


        }

        try{
            double res = Double.parseDouble(buf);
            return Tree.type.Decimal;
        }catch(Exception e){
        }

        return null;
    }


}
