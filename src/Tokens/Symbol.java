package Tokens;

import java.util.HashMap;

public class Symbol extends Token{
    private static final HashMap<String, Integer> symbols =
        new HashMap<>(){{
            put(";", 0);
            put("(", 1);
            put(")", 2);
            put("#", 3);
            put("{", 4);
            put("}", 5);
            put("\"", 6);
            put(":", 7);
            put("\\*", 8);
            put("*\\", 9);
            put(",", 10);
            put("<-", 11);
            put("=", 12);
        }};


    public Symbol(String Op) {
        super(symbols.get(Op));
    }

    public static HashMap<String, Integer> getSymbols() {
        return symbols;
    }
}
