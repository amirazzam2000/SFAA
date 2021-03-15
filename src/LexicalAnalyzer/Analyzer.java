package LexicalAnalyzer;

import Tokens.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
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
            put('.', number);
        }};

        number.setNext(n);
        //TODO: add an empty constructor to constant
        number.getNext().get('0').setValue(new Constants("0", true));
        number.getNext().get('1').setValue(new Constants("1", true));
        number.getNext().get('2').setValue(new Constants("2", true));
        number.getNext().get('3').setValue(new Constants("3", true));
        number.getNext().get('4').setValue(new Constants("4", true));
        number.getNext().get('5').setValue(new Constants("5", true));
        number.getNext().get('6').setValue(new Constants("6", true));
        number.getNext().get('7').setValue(new Constants("7", true));
        number.getNext().get('8').setValue(new Constants("8", true));
        number.getNext().get('9').setValue(new Constants("9", true));
        number.getNext().get('.').setValue(new Constants("10", true));


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
        trie.getNext().put('.', number);


    }
    //all numbers in trie have 9 as id

    private void initAdd(String s, Token value){
        Node node = trie;
        char[] arr = s.toCharArray();
        char c;
        for (int i = 0; i < arr.length; i++){
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
    //NO HASH TAG HERE!!!!!!
    //This is only the parsed substring. so if we had #x String s would be x
    //so we only add the var name? yes
    //we will check if it's not a reserved keyword before this function is run
    public void addVar(String s){
        Node node = trie;
        char[] arr = s.toCharArray();
        char c;
        for (int i = 0; i < arr.length; i++){
            c = arr[i];
            //if the node doesn't exist then create it
            if(!node.getNext().containsKey(c)) {
                //if we're at the end of the substring
                if(i == arr.length - 1) {
                    //add the value of the variable
                    node.getNext().put(c, new Node(new HashMap<>(),
                            new Variables(s)));
                }else{
                    node.getNext().put(c, new Node(new HashMap<>(), null));
                }
            }
            node = node.getNext().get(c);
        }

    }

    //parse file that contains the code we want to compile
    public ArrayList<Token> getTokens(File file) throws AnalyzerExceptions{
        Scanner s = null;
        ArrayList<Token> tokens = new ArrayList<>();
        try {
            s = new Scanner(file);
            //get string
            while( s.hasNextLine()){
                String line = s.nextLine();
                //for each word in the line
                //split string by white spaces
                for (String word: line.split(" ")){
                    getTokensInWord(word, tokens);
                }
            }
            s.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        printTokens(tokens);
    return tokens;
    }

    private void getTokensInWord(String word, ArrayList<Token> tokens) throws AnalyzerExceptions{
        Node currentNode = trie;
        ArrayList<Token> aux = new ArrayList<>();
        char[] arr = word.toCharArray();
        char c;
        for (int i = 0; i < arr.length; i++){
            c = arr[i];
            //if our word contains the current char
            if (currentNode.getNext().containsKey(c)){
                //move there
                currentNode = currentNode.getNext().get(c);
                //check if we're in the last character of the array to see if
                // we should add the token
                if(i == arr.length - 1){
                    if (currentNode.getValue() != null){
                        aux.add(currentNode.getValue());
                        currentNode = trie;
                    }
                    //TODO: if it is a number then add it to the constant table
                    else{
                        throw new AnalyzerExceptions("Unknown Token");
                    }
                }
            }
            else{
                //if we are on a leaf
                if (currentNode.getValue() != null){
                    //if it is a #
                    if (currentNode.getValue() instanceof Symbol && currentNode.getValue().getId() == 3){
                        // get variable name
                        StringBuilder varName = new StringBuilder();
                        for (; i < arr.length; i++) {
                            c = arr[i];
                            varName.append(c);
                        }
                        //add it to ConVar

                        //add the variable token
                        aux.add(new Variables(varName.toString()));
                        break;
                    }
                    // if it's any other token then add it
                    else {
                        aux.add(currentNode.getValue());
                        currentNode = trie;
                        i--;
                    }
                }else{
                    //if we can't move on, but we're not at a leaf, throw an
                    // exception
                    throw new AnalyzerExceptions("Unknown Token");

                }
            }
        }
        tokens.addAll(aux);
    }

    public void printTokens(ArrayList<Token> tokens){
        for (Token t : tokens){
            System.out.println(t.getClass().getName() + " : " + t.getId());
            System.out.println(System.lineSeparator());
        }
    }



    //TODO: comments

}
