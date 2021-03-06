package LexicalAnalyzer;
import Tokens.Token;

import java.security.PublicKey;
import java.util.HashMap;

public class Node {
    private HashMap<Character, Node> next;
    private Token value;

    public Node(HashMap<Character, Node> next, Token value){
        this.next = next;
        this.value = value;
    }

    public HashMap<Character, Node> getNext() {
        return next;
    }

    public void setNext(HashMap<Character, Node> next) {
        this.next = next;
    }

    public Token getValue() {
        return value;
    }

    public void setValue(Token value) {
        this.value = value;
    }
}
