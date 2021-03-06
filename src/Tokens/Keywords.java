package Tokens;

import java.util.HashMap;

public class Keywords extends Token{
    private static final HashMap<String, Integer> keywords =
            new HashMap<>(){{
                put("start", 0);
                put("end", 1);
                put("if", 2);
                put("else", 3);
                put("elseif", 4);
                put("fi", 5);
                put("meanwhile", 6);
                put("done", 7);
                put("func", 8);
                put("return", 9);
                put("var", 10);
                put("call", 11);
            }};


    public Keywords(String Op) {
        super(keywords.get(Op));
    }

    public static HashMap<String, Integer> getKeywords() {
        return keywords;
    }
}
