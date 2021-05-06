package ru.nsu.Commands;

import ru.nsu.Game;
import ru.nsu.Main;

import java.util.logging.Logger;

public class Teleport implements Command {
    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Teleport.class);
    @Override
    public byte execute(String arguments, Game myGame) {
        logger.info("Execute command TELEPORT.");

        StringBuilder sb = new StringBuilder();
        char[] args = arguments.toCharArray();
        int argCount = 0;
        int tmpX = 0, tmpY = 0;

        for (char arg : args) {
            if (!(Character.isDigit(arg) || Character.isWhitespace(arg) || arg == '\n')) {
                logger.warn("Incorrect arguments in command TELEPORT. Incorrect symbol : " + arg);
                System.out.println("Incorrect arguments in command TELEPORT. Incorrect symbol : " + arg);
                return 0;
            }
            if ((arg == ' ' || arg == '\n') && sb.length() != 0) {
                switch (argCount) {
                    case 0 -> {
                        tmpX = Integer.decode(sb.toString());
                        argCount++;
                    }
                    case 1 -> {
                        tmpY = Integer.decode(sb.toString());
                        argCount++;
                    }
                    default -> {
                        logger.warn("Incorrect arguments in command TELEPORT. Incorrect number of arguments");
                        System.out.println("Incorrect arguments in command TELEPORT. Incorrect number of arguments");
                        return 0;
                    }
                }
                sb.setLength(0);
            }
            if (Character.isDigit(arg)) {
                sb.append(arg);
            }
        }
        if (sb.length() != 0) {
            tmpY = Integer.decode(sb.toString());
            argCount++;
        }
        if (argCount == 2 && tmpX <= myGame.getWidth() && tmpY <= myGame.getHeight()) {
            myGame.setPosition(tmpX, tmpY);
        }
        else {
            logger.warn("Incorrect arguments in command TELEPORT. . Incorrect number of arguments or coordinates");
            System.out.println("Incorrect arguments in command TELEPORT. . Incorrect number of arguments or coordinates");
        }
        return 0;
    }
}
