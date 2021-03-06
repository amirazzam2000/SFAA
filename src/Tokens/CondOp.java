package Tokens;

import java.util.HashMap;

public class CondOp extends Token{
    private static final HashMap<String, Integer> conditionals =
        new HashMap<>(){{
            put("<", 0);
            put(">", 1);
            put("<=", 2);
            put(">=", 3);
            put("==", 4);
            put("!=", 5);
        }};

    public CondOp(String Op) {
        super(conditionals.get(Op));
    }

    public static HashMap<String, Integer> getConditionals() {
        return conditionals;
    }
}
