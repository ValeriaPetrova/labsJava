package ru.nsu.Commands;

import ru.nsu.Game;

public class Ward implements Command {
    //private final static Logger logger = Logger.getLogger(Ward.class);
    @Override
    public byte execute(String arguments, Game myGame) {
        //logger.info("Execute command WARD.");
        if (arguments != null) {
            System.out.println("Error. Arguments in command WARD!");
            return 0;
        }
        myGame.setDefaultMode();
        return 0;
    }
}
