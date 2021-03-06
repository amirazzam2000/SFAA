package Tokens;

public abstract class Token {
    private int id;

    public Token( int id) {

        this.id = id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}

