package ru.ustinov.player;

import ru.ustinov.game.Side;
import ru.ustinov.items.Board;

public class StrongComputer extends Player{
    public StrongComputer(PlayerType type, Side side) {
        super(type, side);
    }

    @Override
    public boolean tryMove(Board board) {
        return true;
    }
}
