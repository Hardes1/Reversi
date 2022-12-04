package ru.ustinov.player;

import ru.ustinov.game.Side;
import ru.ustinov.items.Board;

public class WeakComputer extends Player {
    public WeakComputer(PlayerType type, Side side) {
        super(type, side);
    }

    @Override
    public boolean tryMove(Board board) {
        return false;
    }
}
