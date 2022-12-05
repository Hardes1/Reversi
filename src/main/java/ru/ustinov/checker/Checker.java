package ru.ustinov.checker;

import ru.ustinov.game.Side;
import ru.ustinov.items.Cell;

public interface Checker {
    boolean isPossibleMove(Cell cell, Side side);

    boolean isPossibleToCutDirection(Cell cell, Side side, int di, int dj);

    boolean isCorrectPosition(Cell cell);

    boolean isCornerCell(Cell cell);

    boolean isBorderCell(Cell cell);

    boolean isNotNullMovement(Cell cell);

    boolean isOppositeSide(Cell cell, Side side);
}
