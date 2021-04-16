package ru.nsu;

public class Ward implements Command{
    //private final static Logger logger = Logger.getLogger(Ward.class);
    @Override
    public void execute(String arguments, Game myGame) {
        //logger.info("Execute command WARD.");
        if (arguments != null) {
            System.out.println("Error. Arguments in command WARD!");
            return;
        }
        myGame.setDefaultMode();
    }
}
