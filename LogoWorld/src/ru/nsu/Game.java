package ru.nsu;

import ru.nsu.Enums.Direction;
import ru.nsu.Enums.Mode;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Arrays;

public class Game {
    //private final static Logger logger = Logger.getLogger(Game.class);

    private int width = 0;
    private int height = 0;
    private int x = 0;
    private int y = 0;

    private Mode mode = Mode.DEFAULT;
    private char[][] field = new char[height][width];

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public char getCell(int X, int Y) {
        return field[Y][X];
    }

    public void setWidth(int newWidth) {
        width = newWidth;
    }

    public void setHeight(int newHeight) {
        height = newHeight;
    }

    public void setDefaultMode() {
        mode = Mode.DEFAULT;
    }

    public void setDrawMode() {
        mode = Mode.DRAW;
    }

    public void setPosition(int newX, int newY) {
        if (y < height && x < width) {
            field[y][x] = '.';
        }
        x = newX;
        y = newY;
        field[y][x] = 'X';
    }

    public void setField() {
        field = new char[height][width];
        for (char[] chars : field) {
            Arrays.fill(chars, '.');
        }
    }

    public void print(OutputStream outStream) {
        PrintStream stream = new PrintStream(outStream);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                stream.print(field[i][j]);
            }
            stream.print("\n");
        }
        stream.print("\n");
    }

    public void move(int steps, Direction direction) {
        if (mode == Mode.DRAW) {
            int tmp_steps = steps;
            switch (direction) {
                case L -> {
                    field[y][x] = '0';
                    while (tmp_steps != 0) {
                        int tmpX = x - 1;
                        if (tmpX == -1) {
                            tmpX = field[y].length - 1;
                            x = tmpX + 1;
                        }
                        field[y][tmpX] = '*';
                        tmp_steps--;
                        x = tmpX;
                    }
                    field[y][x] = 'X';
                }
                case R -> {
                    field[y][x] = '*';
                    while (0 != tmp_steps) {
                        int tmpX = (x + 1) % width;
                        field[y][tmpX] = '*';
                        tmp_steps--;
                        x = tmpX;
                    }
                    field[y][x] = 'X';
                }
                case U -> {
                    field[y][x] = '*';
                    while (0 != tmp_steps) {
                        int tmpY = y - 1;
                        if (tmpY == -1) {
                            tmpY = field.length - 1;
                            y = tmpY + 1;
                        }
                        field[tmpY][x] = '*';
                        tmp_steps--;
                        y = tmpY;
                    }
                    field[y][x] = 'X';
                }
                case D -> {
                    field[y][x] = '*';
                    while (0 != tmp_steps) {
                        int tmpY = (y + 1) % height;
                        field[tmpY][x] = '*';
                        tmp_steps--;
                        y = tmpY;
                    }
                    field[y][x] = 'X';
                }
                default -> System.out.println("Incorrect arguments in command MOVE. Incorrect direction");
            }
        }
        else {
            switch (direction) {
                case L -> {
                    field[y][x] = '.';
                    int tmpX = x - steps;
                    while (tmpX < 0) {
                        tmpX += field[y].length;
                    }
                    x = tmpX;
                    field[y][x] = 'X';
                }
                case R -> {
                    field[y][x] = '.';
                    x = (x + steps) % width;
                    field[y][x] = 'X';
                }
                case U -> {
                    field[y][x] = '.';
                    int tmpY = y - steps;
                    while (tmpY < 0) {
                        tmpY += field.length;
                    }
                    y = tmpY;
                    field[y][x] = 'X';
                }
                case D -> {
                    field[y][x] = '.';
                    y = (y + steps) % height;
                    field[y][x] = 'X';
                }
                default -> System.out.println("Incorrect arguments in command MOVE. Incorrect direction");
            }
        }
    }
}
