package ru.nsu;

public class Draw implements Command{
    //private final static Logger logger = Logger.getLogger(Draw.class);
    @Override
    public void execute(String arguments, Game myGame) {
        //logger.info("Execute command DRAW.");
        if (arguments != null) {
            System.out.println("Error. Arguments in command DRAW!");
            //logger.error("Trying to execute null command!");
            return;
        }
        myGame.setDrawMode();
    }
}
