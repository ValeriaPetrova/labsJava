package Morse;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class Main {

    public static void loadData(MorzeCode alphabet) {
        FileReader fr = null;
        try {
            fr = new FileReader("src/resources/alphabet.txt");
            Scanner scanner = new Scanner(fr);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Character symbol = line.charAt(0);
                String code = line.substring(1);
                alphabet.setElem(symbol, code);
            }
            alphabet.setElem('\n', "\n");
            alphabet.setElem('\r', "\r");
        } catch (IOException e) {
            System.err.println("Error while reading file: " + e.getLocalizedMessage());
        } finally {
            if (null != fr) {
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace(System.err);
                }
            }

        }
    }

    public static void main(String[] args) {
        MorzeCode morzeCode = new MorzeCode();
        loadData(morzeCode);
        Scanner in = new Scanner(System.in);
        ParsingConsole input = new ParsingConsole();
        System.out.print("Type of work: ");
        String cmd = in.nextLine();
        try {
            Command command = input.getCommands(cmd);
            command.run(morzeCode);
        }
        catch (MyException e) {
            e.what();
        }
        in.close();
    }
}

