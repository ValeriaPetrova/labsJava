package ru.nsu;

import ru.nsu.Commands.Command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
//    private final static Logger logger = Logger.getLogger(Main.class);
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
//            PropertyConfigurator.configure("");
//            logger.info("Entering application.");
            Game myGame = new Game();
            Factory factory = new Factory();
            Command command;
            String exitCommand = "EXIT";
            int step = 1;

            while (true) {
                System.out.println("Enter your command:");
                String str = reader.readLine();
                if (str.equals(exitCommand)) {
                    //logger.info("Execute command EXIT.");
                    System.out.println("Game over.");
                    break;
                }
                if (str.length() < 4) {
                    System.out.println("Incorrect command: " + str);
                    continue;
                }
                char [] charStr = str.toCharArray();
                StringBuilder stringBuilder = new StringBuilder();
                for (char c : charStr) {
                    if (Character.isWhitespace(c)) {
                        break;
                    }
                    stringBuilder.append(c);
                }
                String commandName = stringBuilder.toString();
                //System.out.println(commandName + ", length = " + commandName.length());
                if (step == 1 && !commandName.equals("INIT")) {
                    System.out.println("Firstly, you should enter: INIT <width> <height> <x> <y>");
                    continue;
                }
                command = (Command)factory.create(commandName);
                if (command != null) {
                    if (commandName.equals("DRAW") || commandName.equals("WARD")){
                    command.execute(null, myGame);
                    } else {
                        String arguments = str.substring(commandName.length() + 1, str.length());
                        command.execute(arguments, myGame);
                    }
                    if (step == 1 && myGame.getHeight() <= 0 || myGame.getWidth() <= 0)  {
                        continue;
                    }
                    myGame.print(System.out);
                    step++;
                }
                else {
                    System.out.println("Incorrect command: " + str);
                }
            }
        } catch (IOException | IllegalAccessException | InstantiationException | ClassNotFoundException e) {
//            logger.error("Catch exception:" + e.getClass());
//            logger.error(e.getStackTrace());
//            logger.info("Exiting application.");
//            logger.info("\n------------------------------------------\n");
            e.printStackTrace();
        }
    }
}
