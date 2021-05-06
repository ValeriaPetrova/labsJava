package ru.nsu.Tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.Commands.Command;
import ru.nsu.Commands.Init;
import ru.nsu.Game;

import static org.junit.jupiter.api.Assertions.*;

class InitTest {

    @Test
    void execute() {
        //test1
        Game myGame = new Game();
        String arg = "10 20 5 15";
        Command command = new Init();
        command.execute(arg, myGame);
        Assertions.assertEquals(10, myGame.getWidth());
        Assertions.assertEquals(20, myGame.getHeight());
        Assertions.assertEquals(5, myGame.getX());
        Assertions.assertEquals(15, myGame.getY());

        //test2
        myGame = new Game();
        arg = "10 20 15 150";
        command = new Init();
        Assertions.assertEquals(0, command.execute(arg, myGame));

        //test3
        myGame = new Game();
        arg = "0 10 15 150";
        command = new Init();
        Assertions.assertEquals(0, command.execute(arg, myGame));

        //test4
        myGame = new Game();
        arg = "10 -20 15 150";
        command = new Init();
        Assertions.assertEquals(0, command.execute(arg, myGame));
    }
}