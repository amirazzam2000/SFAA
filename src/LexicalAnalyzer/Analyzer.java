package LexicalAnalyzer;

import Tokens.*;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.concurrent.locks.Condition;

public class Analyzer {
    private Node trie;

    public Analyzer(){
        trie = new Node(new HashMap<>(), null);
        for (String s : Symbol.getSymbols().keySet()) {
            initAdd(s, new Symbol(s));
        }
        for (String s : MathOp.getMathOp().keySet()) {
            initAdd(s, new MathOp(s));
        }
        for (String s : BooleanOP.getBooleans().keySet()) {
            initAdd(s, new BooleanOP(s));
        }
        for (String s : Keywords.getKeywords().keySet()) {
            initAdd(s, new Keywords(s));
        }
        for (String s : CondOp.getConditionals().keySet()) {
            initAdd(s, new CondOp(s));
        }
        for (String s : Datatype.getDatatype().keySet()) {
            initAdd(s, new Datatype(s));
        }

        //Node that will have all of the numbers which also points to itself
        //memory isn't wasted cuz we're accessing the same node
        //as long as numbers are being entered, we can continue to add
        // numbers in an infinite loop until we find another symbol
        Node number = new Node(null, null);

        HashMap<Character, Node> n = new HashMap<>(){{
            put('0', number);
            put('1', number);
            put('2', number);
            put('3', number);
            put('4', number);
            put('5', number);
            put('6', number);
            put('7', number);
            put('8', number);
            put('9', number);

        }};

        number.setNext(n);
        number.getNext().get('0').setValue(new Constants("0"));
        number.getNext().get('1').setValue(new Constants("1"));
        number.getNext().get('2').setValue(new Constants("2"));
        number.getNext().get('3').setValue(new Constants("3"));
        number.getNext().get('4').setValue(new Constants("4"));
        number.getNext().get('5').setValue(new Constants("5"));
        number.getNext().get('6').setValue(new Constants("6"));
        number.getNext().get('7').setValue(new Constants("7"));
        number.getNext().get('8').setValue(new Constants("8"));
        number.getNext().get('9').setValue(new Constants("9"));



        trie.getNext().put('0', number);
        trie.getNext().put('1', number);
        trie.getNext().put('2', number);
        trie.getNext().put('3', number);
        trie.getNext().put('4', number);
        trie.getNext().put('5', number);
        trie.getNext().put('6', number);
        trie.getNext().put('7', number);
        trie.getNext().put('8', number);
        trie.getNext().put('9', number);


    }
    //all numbers in trie have 9 as id

    private void initAdd(String s, Token value){
        Node node = trie;
        char[] arr = s.toCharArray();
        char c;
        for (int i = 0; i < arr.length; i++) {
            c = arr[i];

            if(node.getNext().containsKey(c)){
                node = node.getNext().get(c);
                if (i == arr.length - 1){
                    node.setValue(value);
                }
            }else{
                if(i == arr.length - 1) {
                    node.getNext().put(c, new Node(new HashMap<>(),value));
                }else{
                    node.getNext().put(c, new Node(new HashMap<>(), null));
                }
                node = node.getNext().get(c);

            }
        }

    }

    //TODO: Normal add
    //TODO: Search -> return (Token) Value
    //TODO: cut -> returns an array of arrays (Arraylist)
    //TODO: Node for all numbers
    /*
    * "34+35"
    *  34, +, 35
    * */

}
