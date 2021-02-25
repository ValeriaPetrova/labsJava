package Morse;

public class MyException extends Exception{
    private final String message;

    public MyException(String message) {
        this.message = message;
    }

    public void what() {
        System.out.println(message);
    }
}
