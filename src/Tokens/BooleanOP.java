package Tokens;

import java.util.HashMap;
public class BooleanOP extends Token{
    private static final HashMap<String, Integer> booleans
            = new HashMap<String, Integer>(){{
        put("false",0);
        put("true",1);
        put("OR",2);
        put("AND",3);
        put("!",4);
    }};


    public BooleanOP(String Op) {
        super(booleans.get(Op));
    }

    public static HashMap<String, Integer> getBooleans() {
        return booleans;
    }
}