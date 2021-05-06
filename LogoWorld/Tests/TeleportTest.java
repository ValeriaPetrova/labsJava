package ru.nsu.Tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.nsu.Commands.Command;
import ru.nsu.Commands.Init;
import ru.nsu.Commands.Teleport;
import ru.nsu.Game;

import static org.junit.jupiter.api.Assertions.*;

class TeleportTest {

    @Test
    void execute() {
        //test1
        Game myGame = new Game();
        String argInit = "10 10 5 5";
        Command commandInit = new Init();
        commandInit.execute(argInit, myGame);
        Command commandTeleport = new Teleport();
        String argTeleport = "0 0";
        commandTeleport.execute(argTeleport, myGame);
        Assertions.assertEquals(0, myGame.getX());
        Assertions.assertEquals(0, myGame.getY());

        //test2
        commandTeleport = new Teleport();
        argTeleport = "10 19";
        Assertions.assertEquals(0, commandTeleport.execute(argTeleport, myGame));

        //test3
        commandTeleport = new Teleport();
        argTeleport = "10 -19";
        Assertions.assertEquals(0, commandTeleport.execute(argTeleport, myGame));

        //test4
        commandTeleport = new Teleport();
        argTeleport = "10 19judb";
        Assertions.assertEquals(0, commandTeleport.execute(argTeleport, myGame));
    }
}