package Morse;

public class ParsingConsole {

    public Command getCommands(String cmd) throws MyException{
        String[] args = cmd.split(" ");
        Command command;
        if (args[0].equals("code")) {
            command = new CoderCommand("src/resources/" + args[1]);
        } else if (args[0].equals("decode")) {
            command = new DecoderCommand("src/resources/" + args[1]);
        } else {
            throw new MyException("Wrong command.\n");
        }
        return command;
    }

}
