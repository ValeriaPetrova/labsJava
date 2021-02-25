package Morse;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

public interface Command {
    void run(MorzeCode morzeCode);

    void saveToFile(HashSet<Symbol> set, String file);
}
