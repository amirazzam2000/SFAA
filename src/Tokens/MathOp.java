package Tokens;

import java.util.HashMap;

public class MathOp extends Token{
    private static final HashMap<String, Integer> mathOp =
            new HashMap<>(){{
                put("+", 0);
                put("-", 1);
                put("/", 2);
                put("*", 3);
                put("%", 4);
                put("^", 5);
                put("+=", 6);
                put("-=", 7);
                put("/=", 8);
                put("*=", 9);
            }};


    public MathOp(String Op) {
        super(mathOp.get(Op));
    }

    public static HashMap<String, Integer> getMathOp() {
        return mathOp;
    }
}
