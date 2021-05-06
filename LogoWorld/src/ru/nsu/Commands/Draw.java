package ru.nsu.Commands;

import ru.nsu.Game;

public class Draw implements Command {
    //private final static Logger logger = Logger.getLogger(Draw.class);
    @Override
    public byte execute(String arguments, Game myGame) {
        //logger.info("Execute command DRAW.");
        if (arguments != null) {
            System.out.println("Error. Arguments in command DRAW!");
            //logger.error("Trying to execute null command!");
            return 0;
        }
        myGame.setDrawMode();
        return 0;
    }
}
