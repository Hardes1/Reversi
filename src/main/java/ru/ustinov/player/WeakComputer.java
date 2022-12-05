package ru.ustinov.player;

import ru.ustinov.game.Side;
import ru.ustinov.items.Board;
import ru.ustinov.items.Cell;

import java.util.Set;

public class WeakComputer extends Player {

    public WeakComputer(PlayerType type, Side side) {
        super(type, side);
    }

    @Override
    public boolean tryMove(Board board) {
        Set<Cell> possibleMoves = board.getPossibleMoves(side);
        if (possibleMoves.isEmpty()) {
            System.out.println(String.format(PlayerConstants.IMPOSSIBLE_MOVE, type, side));
            return true;
        }
        double bestScore = 0;
        Cell bestCell = null;
        for (Cell cell : possibleMoves) {
            double score = board.getCellScore(cell, side);
            if (bestScore < score) {
                bestCell = cell;
                bestScore = score;
            }
        }
        board.setCell(bestCell, side);
        return true;
    }
}
