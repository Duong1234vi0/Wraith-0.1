package ua.babka.macro;

public class Macro {
    String message;
    int key;

    public String message() {
        return this.message;
    }

    public int key() {
        return this.key;
    }

    public Macro(String message, int key) {
        this.message = message;
        this.key = key;
    }
}

