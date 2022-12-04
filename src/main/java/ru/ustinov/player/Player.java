package ru.ustinov.player;

import ru.ustinov.game.Side;
import ru.ustinov.items.Board;

import java.util.Scanner;

public abstract class Player {
    protected Scanner scanner = new Scanner(System.in);

    protected final PlayerType type;

    protected final Side side;

    protected Player(PlayerType type, Side side) {
        this.type = type;
        this.side = side;
    }

    public Side getSide() {
        return side;
    }

    public PlayerType getPlayerType() {
        return type;
    }

    public abstract boolean tryMove(Board board);
}
