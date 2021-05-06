package ru.nsu.Commands;

import org.apache.log4j.Logger;
import ru.nsu.Game;
import ru.nsu.Main;

public class Draw implements Command {
    private static final org.apache.log4j.Logger logger = Logger.getLogger(Draw.class);

    @Override
    public byte execute(String arguments, Game myGame) {
        logger.info("Execute command DRAW.");
        if (arguments != null) {
            System.out.println("Error. Arguments in command DRAW!");
            logger.info("Trying to execute null command!");
            return 0;
        }
        myGame.setDrawMode();
        return 0;
    }
}
