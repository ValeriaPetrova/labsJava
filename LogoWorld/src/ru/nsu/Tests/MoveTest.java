package ru.nsu.Tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.common.TextParsingException;
import ru.nsu.Commands.Command;
import ru.nsu.Commands.Init;
import ru.nsu.Commands.Move;
import ru.nsu.Game;

import static org.junit.jupiter.api.Assertions.*;

class MoveTest {

    @Test
    void execute() {
        Game myGame = new Game();
        String argInit = "10 10 5 5";
        Command commandInit = new Init();
        commandInit.execute(argInit, myGame);
        String argMove = "L 5";
        Command commandMove = new Move();
        commandMove.execute(argMove, myGame);
        Assertions.assertEquals(0, myGame.getX());
        Assertions.assertEquals(5, myGame.getY());

        argMove = "R 10";
        commandMove.execute(argMove, myGame);
        Assertions.assertEquals(0, myGame.getX());
        Assertions.assertEquals(5, myGame.getY());

        argMove = "U 7";
        commandMove.execute(argMove, myGame);
        Assertions.assertEquals(0, myGame.getX());
        Assertions.assertEquals(8, myGame.getY());

        argMove = "D 7";
        commandMove.execute(argMove, myGame);
        Assertions.assertEquals(0, myGame.getX());
        Assertions.assertEquals(5, myGame.getY());

        //Big data
        argMove = "L 10000";
        commandMove.execute(argMove, myGame);
        Assertions.assertEquals(0, myGame.getX());
        Assertions.assertEquals(5, myGame.getY());

        argMove = "R 10000";
        commandMove.execute(argMove, myGame);
        Assertions.assertEquals(0, myGame.getX());
        Assertions.assertEquals(5, myGame.getY());

        argMove = "U 7777";
        commandMove.execute(argMove, myGame);
        Assertions.assertEquals(0, myGame.getX());
        Assertions.assertEquals(8, myGame.getY());

        argMove = "D 77777";
        commandMove.execute(argMove, myGame);
        Assertions.assertEquals(0, myGame.getX());
        Assertions.assertEquals(5, myGame.getY());

        //test3
        argMove = "L -10000";
        Assertions.assertEquals(0, commandMove.execute(argMove, myGame));

        argMove = "R -10000";
        Assertions.assertEquals(0, commandMove.execute(argMove, myGame));

        argMove = "U -7777";
        Assertions.assertEquals(0, commandMove.execute(argMove, myGame));

        argMove = "D -77777";
        Assertions.assertEquals(0, commandMove.execute(argMove, myGame));

        //test4 DRAW
        myGame = new Game();
        argInit = "10 10 5 5";
        commandInit = new Init();
        commandInit.execute(argInit, myGame);
        myGame.setDrawMode();
        argMove = "L 5";
        commandMove = new Move();
        commandMove.execute(argMove, myGame);
        Assertions.assertEquals('*', myGame.getCell(4, 5));
        Assertions.assertEquals('X', myGame.getCell(0, 5));

        //test5 WARD
        myGame.setDefaultMode();
        argMove = "U 10";
        commandMove = new Move();
        commandMove.execute(argMove, myGame);
        Assertions.assertEquals('.', myGame.getCell(0, 3));
        Assertions.assertEquals('.', myGame.getCell(0, 8));
        Assertions.assertEquals('X', myGame.getCell(0, 5));
    }

}