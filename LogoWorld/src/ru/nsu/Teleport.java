package ru.nsu;

public class Teleport implements Command{
    //private final static Logger logger = Logger.getLogger(Teleport.class);
    @Override
    public void execute(String arguments, Game myGame) {
       // logger.info("Execute command TELEPORT.");

        StringBuilder sb = new StringBuilder();
        char[] args = arguments.toCharArray();
        int argCount = 0;
        int tmpX = 0, tmpY = 0;

        for (int i = 0; i < args.length; i++) {
            if (! (Character.isDigit(args[i]) || Character.isWhitespace(args[i]) || args[i] == '\n')) {
                System.out.println("Incorrect arguments in command TELEPORT. Incorrect symbol : " + args[i]);
                return;
            }
            if ((args[i] == ' ' || args[i] == '\n') && sb.length() != 0) {
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
                        System.out.println("Incorrect arguments in command TELEPORT. Incorrect number of arguments");
                        return;
                    }
                }
                sb.setLength(0);
            }
            if (Character.isDigit(args[i])) {
                sb.append(args[i]);
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
            System.out.println("Incorrect arguments in command TELEPORT. . Incorrect number of arguments or coordinates");
        }
    }
}
