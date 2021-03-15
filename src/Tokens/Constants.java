package Tokens;

import java.util.HashMap;


public class Constants extends Token{
    private static HashMap<Character, Integer> char_constants = new HashMap<>();
    private static HashMap<Integer, Integer> int_constants = new HashMap<>();
    private static HashMap<Float, Integer> float_constants = new HashMap<>();
    private static int value = 0;

    public Constants(String Op, boolean isNumber) {
        super(-1);
        //TODO: check if the constant is already in the table
        if (isNumber) {
            // check for floats
            //if it has a period in it :
            if (Op.contains(".")) {
                // if it's only one period -> it's a float
                if (checkTooManyDot(Op)) {
                    //ERROR
                    System.out.println("Error too many dots in a float");
                } else{
                    float_constants.put(Float.parseFloat(Op), value++);
                    super.setId(value - 1);
                }
            }
            //else it's an integer :
            else {
                int_constants.put(Integer.parseInt(Op), value++);
                super.setId(value - 1);
            }

        } else {
            if (Op.length() > 1) {
                //Nope
                System.out.println("Error char can't be a string");
            } else {
                Character c = Op.charAt(0);
                char_constants.put(c, value++);
                super.setId(value - 1);
            }
        }
    }

    private boolean checkTooManyDot(String string){
        int i = 0 ;
        for (char c : string.toCharArray()) {
            if (c == '.') i++;
            if (i > 1) return true;
        }
        return false;
    }

    public static HashMap<Character, Integer> getCharConstants() {
        return char_constants;
    }

    public static HashMap<Integer, Integer> getIntConstants() {
        return int_constants;
    }

    public static HashMap<Float, Integer> getFloatConstants() {
        return float_constants;
    }


}
