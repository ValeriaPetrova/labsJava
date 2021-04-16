package ru.nsu;

public class Move implements Command{
    //private final static Logger logger = Logger.getLogger(Move.class);
    @Override
    public void execute(String arguments, Game myGame) {
        //logger.info("Execute command MOVE.");

        StringBuilder sb = new StringBuilder();
        String strDirection = null;
        char[] args = arguments.toCharArray();
        int tmpPosition = args.length;
        int steps = 0;

        for (int i = 0; i < args.length; i++) {
            if (!(Character.isAlphabetic(args[i]) || Character.isWhitespace(args[i]))) {
                System.out.println("Incorrect arguments in command MOVE. Incorrect symbol : " + args[i]);
                return;
            }
            if ((args[i] == ' ') && sb.length() != 0) {
                strDirection = sb.toString();
                if (!(strDirection.equals("L") || strDirection.equals("R") || strDirection.equals("U") || strDirection.equals("D"))) {
                    System.out.println("Incorrect arguments in command MOVE. Incorrect symbol : " + args[i]);
                    return;
                }
                sb.setLength(0);
                tmpPosition = i;
                break;
            }
            if (Character.isAlphabetic(args[i])) {
                sb.append(args[i]);
            }
        }
        sb.setLength(0);
        for (int i = tmpPosition; i < args.length; i++) {
            if (! (Character.isDigit(args[i]) || Character.isWhitespace(args[i]) || args[i] == '\n')) {
                System.out.println("Incorrect arguments in command MOVE. Incorrect symbol : " + args[i]);
                return;
            }
            if ((args[i] == ' ' || args[i] == '\n') && sb.length() != 0) {
                steps = Integer.decode(sb.toString());
                tmpPosition = i;
                break;
            }
            if (Character.isDigit(args[i])) {
                sb.append(args[i]);
            }
            tmpPosition = i;
        }
        if (tmpPosition == args.length - 1 && sb.length() != 0) {
            steps = Integer.decode(sb.toString());
        }
        else {
            for (int i = tmpPosition; i < args.length; i++) {
                if (! (Character.isWhitespace(args[i]) || args[i] == '\n')) {
                    System.out.println("Incorrect arguments in command MOVE. Incorrect symbol : " + args[i]);
                    return;
                }
            }
        }

        Direction direction;
        switch (strDirection) {
            case "L":
                direction = Direction.L;
                break;

            case "R":
                direction = Direction.R;
                break;

            case "U":
                direction = Direction.U;
                break;

            case "D":
                direction = Direction.D;
                break;

            default:
                System.out.println("Incorrect arguments in command MOVE. Incorrect direction");
                return;
        }
        myGame.move(steps, direction);
    }
}
