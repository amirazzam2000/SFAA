package Tokens;

import java.util.HashMap;

public class Constants extends Token{
    private static HashMap<String, Integer> constants = new HashMap<>();
    private static int value = 0;

    public Constants(String Op) {
        super(-1);
        if (constants.containsKey(Op))
            super.setId(constants.get(Op));
        else {
            super.setId(value);
            constants.put(Op, value++);
        }
    }

    public static HashMap<String, Integer> getConstants() {
        return constants;
    }
}
