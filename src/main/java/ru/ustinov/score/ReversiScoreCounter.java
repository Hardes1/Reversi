package ru.ustinov.score;

import ru.ustinov.checker.Checker;
import ru.ustinov.game.Side;
import ru.ustinov.items.Cell;
import ru.ustinov.items.SidesScore;
import ru.ustinov.util.Utils;

public class ReversiScoreCounter implements ScoreCounter {
    private final Side[][] table;

    private final Checker checker;

    public ReversiScoreCounter(Side[][] table, Checker checker) {
        this.table = table;
        this.checker = checker;
    }

    @Override
    public SidesScore getSidesScore() {
        int whiteScore = 0;
        int blackScore = 0;
        for (int i = 0; i < table.length; ++i) {
            for (int j = 0; j < table.length; ++j) {
                if (table[i][j] == Side.WHITE) {
                    whiteScore++;
                } else if (table[i][j] == Side.BLACK) {
                    blackScore++;
                }
            }
        }
        return new SidesScore(whiteScore, blackScore);
    }

    @Override
    public double getCellScore(Cell cell, Side side) {
        return getScoreByCellType(cell) + getEatenCellsScore(cell, side);
    }

    private int getEatenCellsScore(Cell cell, Side side) {
        int result = 0;
        for (int di = -1; di <= 1; ++di) {
            for (int dj = -1; dj <= 1; ++dj) {
                if (di * di + dj * dj > 0 && checker.isCorrectPosition(new Cell(cell.row() + di, cell.column() + dj))
                        && table[cell.row() + di][cell.column() + dj] == Utils.getOppositeSide(side)) {
                    int deltaRow = di;
                    int deltaColumn = dj;
                    while (checker.isCorrectPosition(new Cell(cell.row() + deltaRow, cell.column() + deltaColumn)) &&
                            table[cell.row() + deltaRow][cell.column() + deltaColumn] == Utils.getOppositeSide(side)) {
                        if (checker.isBorderCell(new Cell(cell.row() + deltaRow, cell.column() + deltaColumn))) {
                            result += ScoreCounterConstants.BORDER_EATEN_SCORE;
                        } else {
                            result += ScoreCounterConstants.OTHERS_EATEN_SCORE;
                        }
                        deltaRow += di;
                        deltaColumn += dj;
                    }
                }
            }
        }
        return result;
    }

    private double getScoreByCellType(Cell cell) {
        if (checker.isCornerCell(cell)) {
            return ScoreCounterConstants.CELL_SCORE;
        } else if (checker.isBorderCell(cell)) {
            return ScoreCounterConstants.BORDER_SCORE;
        }
        return ScoreCounterConstants.OTHERS_SCORE;
    }
}
