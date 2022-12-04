package ru.ustinov.items;

import ru.ustinov.game.Side;

import java.util.Set;

public interface Board {

    void setCell(final Cell coordinates, Side side);

    void print(Side side);

    boolean isGameFinished();

    Set<Cell> getPossibleMoves(Side side);

    SidesScore getScore();
}
