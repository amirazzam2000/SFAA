package Tokens;

import java.util.HashMap;

public class Datatype extends Token{
    private static final HashMap<String, Integer> datatype =
            new HashMap<>(){{
                put("int", 0);
                put("float", 1);
                put("char", 2);
                put("string", 3);
                put("boolean", 4);
            }};


    public Datatype(String Op) {
        super(datatype.get(Op));
    }

    public static HashMap<String, Integer> getDatatype() {
        return datatype;
    }
}
