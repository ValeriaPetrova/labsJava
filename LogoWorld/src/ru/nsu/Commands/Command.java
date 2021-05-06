package ru.nsu.Commands;

import ru.nsu.Game;

public interface Command {
    byte execute(String args, Game myGame);
}
