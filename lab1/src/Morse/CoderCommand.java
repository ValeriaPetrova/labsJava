package Morse;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;

public class CoderCommand implements Command{
    private final String file;

    @Override
    public void run(MorzeCode morze) {
        FileReader fr = null;
        FileWriter fw = null;
        try {
            fr = new FileReader(file);
            Scanner scanner = new Scanner(fr);
            fw = new FileWriter("src/out/outputCoder.txt");
            HashSet<Symbol> statistics = new HashSet<>();
            StringBuilder word = new StringBuilder();
            while (scanner.hasNextLine()) {
                String str = scanner.nextLine() + '\n';
                for (int i = 0; i < str.length(); i++) {
                    char symbol = str.charAt(i);
                    symbol = Character.toLowerCase(symbol);
                    if (symbol == ' ') {
                        fw.write(word.toString() + "  ");
                        word.setLength(0);
                    } else {
                        if (Character.isLetter(symbol)) {
                            statistics.add(new Symbol(symbol));
                        }
                        String code = morze.coding(symbol);
                        word.append(code).append(' ');
                    }
                }
            }
            fw.write(word.toString().trim());
            saveToFile(statistics, "statisticsOfCoding.txt");
            }
        catch (IOException e) {
            System.err.println("Error while reading file: " + e.getLocalizedMessage());
        }
        finally {
            if (null != fr) {
                try {
                    fr.close();
                }
                catch (IOException e) {
                    e.printStackTrace(System.err);
                }
            }
            if (null != fw) {
                try {
                    fw.close();
                }
                catch (IOException e) {
                    e.printStackTrace(System.err);
                }
            }
        }
    }

    @Override
    public void saveToFile(HashSet<Symbol> set, String file) {
        FileWriter fw = null;
        try {
            fw = new FileWriter("src/out/" + file);
            for (Symbol smb : set) {
                fw.write(smb.toString() + '\n');
            }
        } catch (IOException e) {
            System.err.println("Error while reading file: " + e.getLocalizedMessage());
        } finally {
            if (null != fw) {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace(System.err);
                }
            }
        }
    }

    public  CoderCommand(String file) {
        this.file = file;
    }
}
