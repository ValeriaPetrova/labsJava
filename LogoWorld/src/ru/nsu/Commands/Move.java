package ru.nsu.Commands;

import ru.nsu.Enums.Direction;
import ru.nsu.Game;

public class Move implements Command {
    //private final static Logger logger = Logger.getLogger(Move.class);
    @Override
    public byte execute(String arguments, Game myGame) {
        //logger.info("Execute command MOVE.");

        StringBuilder sb = new StringBuilder();
        String strDirection = null;
        char[] args = arguments.toCharArray();
        int tmpPosition = args.length;
        int steps = 0;

        for (int i = 0; i < args.length; i++) {
            if (!(Character.isAlphabetic(args[i]) || Character.isWhitespace(args[i]))) {
                System.out.println("Incorrect arguments in command MOVE. Incorrect symbol : " + args[i]);
                return 0;
            }
            if ((args[i] == ' ') && sb.length() != 0) {
                strDirection = sb.toString();
                if (!(strDirection.equals("L") || strDirection.equals("R") || strDirection.equals("U") || strDirection.equals("D"))) {
                    System.out.println("Incorrect arguments in command MOVE. Incorrect symbol : " + args[i]);
                    return 0;
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
                return 0;
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
                    return 0;
                }
            }
        }

        Direction direction;
        switch (strDirection) {
            case "L" -> direction = Direction.L;
            case "R" -> direction = Direction.R;
            case "U" -> direction = Direction.U;
            case "D" -> direction = Direction.D;
            default -> {
                System.out.println("Incorrect arguments in command MOVE. Incorrect direction");
                return 0;
            }
        }
        myGame.move(steps, direction);
        return 0;
    }
}
