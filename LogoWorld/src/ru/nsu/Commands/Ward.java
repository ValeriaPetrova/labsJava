package ru.nsu.Commands;

import ru.nsu.Game;
import ru.nsu.Main;

import java.util.logging.Logger;

public class Ward implements Command {
    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Ward.class);
    @Override
    public byte execute(String arguments, Game myGame) {
        logger.info("Execute command WARD.");
        if (arguments != null) {
            logger.error("Error. Arguments in command WARD!");
            System.out.println("Error. Arguments in command WARD!");
            return 0;
        }
        myGame.setDefaultMode();
        return 0;
    }
}
