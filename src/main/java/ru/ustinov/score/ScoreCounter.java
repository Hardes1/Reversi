package ru.ustinov.score;

import ru.ustinov.game.Side;
import ru.ustinov.items.Cell;
import ru.ustinov.items.SidesScore;

public interface ScoreCounter {
    double getCellScore(Cell cell, Side side);

    SidesScore getSidesScore();
}
