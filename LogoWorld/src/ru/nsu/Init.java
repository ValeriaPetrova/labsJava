package ru.nsu;

public class Init implements Command{
    //private final static Logger logger = Logger.getLogger(Init.class);
    @Override
    public void execute(String arguments, Game myGame) {
        //logger.info("Execute command INIT.");

        StringBuilder sb = new StringBuilder();
        char[] args = arguments.toCharArray();
        int argCount = 0;
        int tmpWidth = 0, tmpHeight = 0, tmpX = 0, tmpY = 0;

        for (int i = 0; i < args.length; i++) {
            if (!(Character.isDigit(args[i]) || Character.isWhitespace(args[i]) || args[i] == '\n')) {
                System.out.println("Incorrect arguments in command INIT. Incorrect symbol : " + args[i]);
                return;
            }
            if ((args[i] == ' ' || args[i] == '\n') && sb.length() != 0) {
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
                        return;
                    }
                }
                sb.setLength(0);
            }

            if (Character.isDigit(args[i])) {
                sb.append(args[i]);
            }
        }
        if (sb.length() != 0 && argCount == 3) {
            tmpY = Integer.decode(sb.toString());
            argCount++;
        }
        if (argCount == 4) {
            if (tmpX >= tmpWidth || tmpY >= tmpHeight) {
                System.out.println("Position is out of range.");
                return;
            }
            myGame.setWidth(tmpWidth);
            myGame.setHeight(tmpHeight);
            myGame.setField();
            myGame.setPosition(tmpX, tmpY);
        }
        else {
            System.out.println("Incorrect arguments in command INIT. Incorrect number of arguments");
        }
    }
}
