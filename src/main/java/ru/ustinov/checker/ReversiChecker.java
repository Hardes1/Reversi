package ru.ustinov.checker;

import ru.ustinov.game.GameConstants;
import ru.ustinov.game.Side;
import ru.ustinov.items.Cell;

import static ru.ustinov.util.Utils.getOppositeSide;

public class ReversiChecker implements Checker {
    private final Side[][] table;

    private final int size;

    public ReversiChecker(Side[][] table, int size) {
        this.table = table;
        this.size = size;
    }


    @Override
    public boolean isPossibleMove(Cell cell, Side current) {
        boolean isPossible = false;
        for (int di = -1; di <= 1; ++di) {
            for (int dj = -1; dj <= 1; ++dj) {
                if (di * di + dj * dj > GameConstants.MINIMAL_BOARD_SIZE &&
                        isCorrectPosition(new Cell(cell.row() + di, cell.column() + dj)) &&
                        table[cell.row() + di][cell.column() + dj] == getOppositeSide(current)) {
                    isPossible |= isPossibleToCutDirection(cell, current, di, dj);
                }
            }
        }
        return isPossible;
    }

    @Override
    public boolean isPossibleToCutDirection(Cell cell, Side current, int di, int dj) {
        int deltaRow = 2 * di;
        int deltaColumn = 2 * dj;
        while (isCorrectPosition(new Cell(cell.row() + deltaRow, cell.column() + deltaColumn))) {
            if (table[cell.row() + deltaRow][cell.column() + deltaColumn] == current) {
                return true;
            }
            deltaRow += di;
            deltaColumn += dj;
        }
        return false;
    }

    @Override
    public boolean isCorrectPosition(Cell cell) {
        return cell.row() >= GameConstants.MINIMAL_BOARD_SIZE && cell.row() < size
                && cell.column() >= GameConstants.MINIMAL_BOARD_SIZE && cell.column() < size;
    }
}
