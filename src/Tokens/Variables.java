package Tokens;

import java.util.HashMap;

public class Variables extends Token{
    private static HashMap<String, Integer> variables = new HashMap<>();
    private static int value = 0;

    public Variables(String Op) {
        super(value);
        variables.put(Op, value++);
    }
}

/*
* {
*   r: {
*          e : {
*                   t: {
*                           u:{
*                                  r:{
*                                         n:{
*
*                                            }, value
*                                      }, value
*                              } , value
*                       }, value
*               }, value
*       }, value
*
* }
*  {
*    r : {hashmap, value}
*           |
*            --> e : {hashmap, value}
*            --> o : {hashmap, value}
*  }
* */
