package Morse;

import java.util.TreeMap;

public class MorzeCode {
    private final TreeMap<Character, String> coding;

    private final TreeMap<String, Character> decoding;

    public MorzeCode() {
        coding = new TreeMap<>();
        decoding = new TreeMap<>();
    }

    public void setElem(Character symbol, String code) {
        coding.put(symbol, code);
        decoding.put(code, symbol);
    }

    public String coding(Character symbol) {
        return coding.get(symbol);
    }

    public Character decoding(String code) {
        return decoding.get(code);
    }
}
