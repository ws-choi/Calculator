package net.dataknow.java;

import net.dataknow.java.Node.Decimal_Literal;
import net.dataknow.java.Node.Tree;

import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

public class Parser {

    private static final int NOT_DEFINED = -1;
    private static final int START = 0;
    private static final int START_DECIMAL = 1;
    private static final int REGISTER_NUMERIC = 2;
    private static final int MAKE_NUMERIC = 3;
    private static final int MAKE_DECIMAL = 4;
    private static final int START_DOT = 5;
    private static final int START_BINARY_OP =6 ;


    public static Tree parse (String string) throws Exception {

        int next_state = NOT_DEFINED;
        int current_state = START;
        List<Character> buffer = null;
        List<Tree> parsed = new ArrayList<Tree>();

        for (char next_char : (string+" ").toCharArray()) {

            next_state = chg_state(current_state, next_char);

            switch(next_state){

                case NOT_DEFINED:
                    throw new Exception();
                case START:
                    break;

                case START_DECIMAL:
                    buffer = new ArrayList<Character>();
                    buffer.add(next_char);
                    break;

                case START_DOT:
                    buffer = new ArrayList<Character>();
                    buffer.add(next_char);
                    break;

                case MAKE_DECIMAL:
                    if(buffer == null) throw new Exception();
                    else buffer.add(next_char);
                    break;

                case MAKE_NUMERIC:
                    if(buffer == null) throw new Exception();
                    else buffer.add(next_char);
                    break;

                case REGISTER_NUMERIC:
                    String literal = bufftoString(buffer);
                    parsed.add( new Decimal_Literal(Double.parseDouble(literal)));
                break;

            }

            current_state = next_state;


        }


        return parsed.get(0);
    }

    private static String bufftoString(List<Character> buffer) {
        char[] buf = new char[buffer.size()];

        for(int i=0; i< buffer.size(); i++){
            buf[i]=buffer.get(i);
        }

        return String.copyValueOf(buf);
    }

    private static int chg_state(int current_state, char next) {

        int result = NOT_DEFINED;

        switch (current_state){
            case START :
                if(isBlank(next)) return START;
                if(isDecimal(next)) return START_DECIMAL;
                if(isDot(next)) return START_DOT;
                break;

            case START_DECIMAL:

                if(isDecimal(next)) return MAKE_DECIMAL;
                if(isDot(next)) return MAKE_NUMERIC;
                else return REGISTER_NUMERIC;

            case START_DOT:
                if(isDot(next)) return NOT_DEFINED;
                if(isDecimal(next)) return MAKE_NUMERIC;
                else return NOT_DEFINED;

            case MAKE_DECIMAL :

                if(isDecimal(next)) return MAKE_DECIMAL;
                if(isDot(next)) return MAKE_NUMERIC;
                else return REGISTER_NUMERIC;

            case MAKE_NUMERIC:
                if(isDecimal(next)) return MAKE_NUMERIC;
                else return REGISTER_NUMERIC;

            case REGISTER_NUMERIC:
                if(isBlank(next)) return START;
                if(isOperator(next)) return START_BINARY_OP;


        }

        return result;
    }

    private static boolean isOperator(char next) {
        return (next == '-' || next == '+' || next == '*' || next == '/'|| next == '^'  );
    }

    private static boolean isBlank(char next) {
        return (next == '.');
    }

    private static boolean isDot(char next) {
        return (next == '.');
    }

    private static boolean isDecimal(char character){
        return (character > '0' && character < '9');
    }

}
