package Morse;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;

public class DecoderCommand implements Command{
    private String file;

    enum Mode {
        read,
        check
    }

    @Override
    public void run(MorzeCode morzeCode) {
        FileReader fr = null;
        FileWriter fw = null;
        try {
            fr = new FileReader(file);
            Scanner scanner = new Scanner(fr);
            fw = new FileWriter("src/out/outputDecoder.txt");
            int count = 0;
            HashSet<Symbol> statistics = new HashSet<>();
            StringBuilder word = new StringBuilder();
            StringBuilder code = new StringBuilder();
            Mode mode = Mode.read;
            while (scanner.hasNextLine()) {
                String str = scanner.nextLine() + '\n';
                for (int i = 0; i < str.length(); i++) {
                    char symbol = str.charAt(i);
                    if (mode == Mode.read) {
                        if (symbol == '.' || symbol == '-') {
                            code.append(symbol);
                        } else if (symbol == ' ') {
                            count++;
                            mode = Mode.check;
                        }
                    } else {
                        if (symbol == ' ') {
                            count++;
                            continue;
                        }
                        if (count == 1) {
                            char letter = morzeCode.decoding(code.toString());
                            word.append(letter);
                            if (Character.isLetter(letter)) {
                                statistics.add(new Symbol(letter));
                            }
                            code.setLength(0);
                            code.append(symbol);
                            count = 0;
                            mode = Mode.read;
                        } else if (count == 3) {
                            char letter = morzeCode.decoding(code.toString());
                            word.append(letter);
                            if (Character.isLetter(letter)) {
                                statistics.add(new Symbol(letter));
                            }
                            fw.write(word.toString() + ' ');
                            word.setLength(0);
                            code.setLength(0);
                            code.append(symbol);
                            count = 0;
                            mode = Mode.read;
                        }
                    }
                }
            }
            char letter = morzeCode.decoding(code.toString());
            word.append(letter);
            if (Character.isLetter(letter)) {
                statistics.add(new Symbol(letter));
            }
            fw.write(word.toString());
            saveToFile(statistics, "statisticsOfDecoding.txt");
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

    public DecoderCommand(String file) {
        this.file = file;
    }
}
