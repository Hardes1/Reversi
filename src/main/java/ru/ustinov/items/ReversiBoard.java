package ru.ustinov.items;

import ru.ustinov.checker.Checker;
import ru.ustinov.checker.ReversiChecker;
import ru.ustinov.game.Side;
import ru.ustinov.score.ReversiScoreCounter;
import ru.ustinov.score.ScoreCounter;

import java.util.HashSet;
import java.util.Set;

public class ReversiBoard implements Board {
    private final Side[][] table;

    private final int size;

    private final Set<Cell> possibleMoves = new HashSet<>();

    private int movesLeft;

    private final Checker checker;

    private final ScoreCounter scoreCounter;

    public ReversiBoard(int size) {
        table = new Side[size][size];
        checker = new ReversiChecker(table, size);
        scoreCounter = new ReversiScoreCounter(table, checker);
        this.size = size;
        movesLeft = size * size - BoardConstants.INITIAL_MOVES_USED;
        initBoardState();
    }

    private void initBoardState() {
        table[3][3] = Side.BLACK;
        table[3][4] = Side.WHITE;
        table[4][3] = Side.WHITE;
        table[4][4] = Side.BLACK;
    }

    @Override
    public void print(Side side) {
        refreshPossibleMoves(side);
        for (int i = table.length - 1; i >= 0; --i) {
            System.out.print(i + 1 + " ");
            String[] transformed = new String[size];
            for (int j = 0; j < table[i].length; ++j) {
                transformed[j] = convertToString(new Cell(i, j), side);
            }
            System.out.println(String.join(" ", transformed));
        }
        System.out.println(BoardConstants.BOTTOM_NUMBERS);
        SidesScore score = getSidesScore();
        System.out.println(String.format(BoardConstants.CURRENT_SCORE, score.whiteScore(), score.blackScore()));
        System.out.println(BoardConstants.HINT);
        System.out.println(BoardConstants.POSSIBLE_MOVES);
        for (Cell cell : possibleMoves) {
            System.out.print(String.format(BoardConstants.CELL_FORMAT, cell.row() + 1, cell.column() + 1) + " ");
        }
        System.out.println();
    }

    @Override
    public boolean isGameFinished() {
        return movesLeft == 0;
    }

    private void refreshPossibleMoves(Side current) {
        possibleMoves.clear();
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                if (table[i][j] == null && checker.isPossibleMove(new Cell(i, j), current)) {
                    possibleMoves.add(new Cell(i, j));
                }
            }
        }
    }

    @Override
    public Set<Cell> getPossibleMoves(Side current) {
        return possibleMoves;
    }


    private String convertToString(Cell cell, Side side) {
        if (table[cell.row()][cell.column()] == Side.BLACK) {
            return BoardConstants.BLACK_SYMBOL;
        } else if (table[cell.row()][cell.column()] == Side.WHITE) {
            return BoardConstants.WHITE_SYMBOL;
        } else if (checker.isPossibleMove(cell, side)) {
            return BoardConstants.POSSIBLE_MOVE_SYMBOL;
        } else {
            return BoardConstants.NEUTRAL_SYMBOL;
        }
    }

    @Override
    public void setCell(Cell cell, Side side) {
        movesLeft--;
        table[cell.row()][cell.column()] = side;
        refreshCell(cell, side);
    }

    @Override
    public SidesScore getSidesScore() {
        return scoreCounter.getSidesScore();
    }

    @Override
    public double getCellScore(Cell cell, Side side) {
        return scoreCounter.getCellScore(cell, side);
    }

    private void refreshCell(Cell cell, Side current) {
        for (int di = -1; di <= 1; ++di) {
            for (int dj = -1; dj <= 1; ++dj) {
                Cell deltaCell = new Cell(di, dj);
                Cell newCell = new Cell(cell.row() + di, cell.column() + dj);
                if (checker.isNotNullMovement(deltaCell) &&
                        checker.isCorrectPosition(newCell) &&
                        checker.isOppositeSide(newCell, current) &&
                        checker.isPossibleToCutDirection(cell, current, di, dj)) {
                    int deltaRow = di;
                    int deltaColumn = dj;
                    newCell = new Cell(cell.row() + deltaRow, cell.column() + deltaColumn);
                    while (checker.isCorrectPosition(newCell) && checker.isOppositeSide(newCell, current)) {
                        table[cell.row() + deltaRow][cell.column() + deltaColumn] = current;
                        deltaRow += di;
                        deltaColumn += dj;
                    }
                }
            }
        }
    }
}

