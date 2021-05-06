package ru.nsu.Commands;

import org.apache.log4j.Logger;
import ru.nsu.Game;
import ru.nsu.Main;

public class Init implements Command {
    private static final org.apache.log4j.Logger logger = Logger.getLogger(Init.class);
    @Override
    public byte execute(String arguments, Game myGame) {
        logger.info("Execute command INIT.");

        StringBuilder sb = new StringBuilder();
        char[] args = arguments.toCharArray();
        int argCount = 0;
        int tmpWidth = 0, tmpHeight = 0, tmpX = 0, tmpY = 0;

        for (char arg : args) {
            if (!(Character.isDigit(arg) || Character.isWhitespace(arg) || arg == '\n')) {
                System.out.println("Incorrect arguments in command INIT. Incorrect symbol : " + arg);
                return 0;
            }
            if ((arg == ' ' || arg == '\n') && sb.length() != 0) {
                switch (argCount) {
                    case 0 -> {
                        tmpWidth = Integer.decode(sb.toString());
                        argCount++;
                    }
                    case 1 -> {
                        tmpHeight = Integer.decode(sb.toString());
                        argCount++;
                    }
                    case 2 -> {
                        tmpX = Integer.decode(sb.toString());
                        argCount++;
                    }
                    case 3 -> {
                        tmpY = Integer.decode(sb.toString());
                        argCount++;
                    }
                    default -> {
                        System.out.println("Incorrect arguments in command INIT. Incorrect number of arguments");
                        return 0;
                    }
                }
                sb.setLength(0);
            }

            if (Character.isDigit(arg)) {
                sb.append(arg);
            }
        }
        if (sb.length() != 0 && argCount == 3) {
            tmpY = Integer.decode(sb.toString());
            argCount++;
        }
        if (argCount == 4) {
            if (tmpX >= tmpWidth || tmpY >= tmpHeight) {
                logger.error("Position is out of range.");
                System.out.println("Position is out of range.");
                return 0;
            }
            myGame.setWidth(tmpWidth);
            myGame.setHeight(tmpHeight);
            myGame.setField();
            myGame.setPosition(tmpX, tmpY);
        }
        else {
            System.out.println("Incorrect arguments in command INIT. Incorrect number of arguments");
        }
        return 0;
    }
}
