package ru.ustinov.player;

import ru.ustinov.CommonConstants;
import ru.ustinov.game.Side;
import ru.ustinov.items.Board;
import ru.ustinov.items.Cell;

import java.util.Set;

public class Human extends Player {
    public Human(PlayerType type, Side side) {
        super(type, side);
    }

    @Override
    public boolean tryMove(Board board) {
        Set<Cell> possibleMoves = board.getPossibleMoves(side);

        if (possibleMoves.isEmpty()) {
            System.out.println(String.format(PlayerConstants.IMPOSSIBLE_MOVE, type, side));
            return true;
        }

        boolean isFinished = false;
        do {
            String[] input = scanner.nextLine().split(" ");
            if (input.length == PlayerConstants.ONE_ARGUMENT) {
                if (input[0].equals(PlayerConstants.BACK_COMMAND)) {
                    isFinished = true;
                } else {
                    System.out.println(CommonConstants.INCORRECT_COMMAND);
                }
            } else if (input.length == PlayerConstants.TWO_ARGUMENTS) {
                try {
                    int x = Integer.parseInt(input[0]);
                    int y = Integer.parseInt(input[1]);
                    x--;
                    y--;
                    Cell possibleMove = new Cell(x, y);
                    if (possibleMoves.contains(possibleMove)) {
                        board.setCell(possibleMove, side);
                        return true;
                    } else {
                        System.out.println(PlayerConstants.INCORRECT_COORDINATES);
                    }
                } catch (NumberFormatException e) {
                    System.out.println(CommonConstants.INCORRECT_COMMAND);
                }
            } else {
                System.out.println(CommonConstants.INCORRECT_COMMAND);
            }

        } while (!isFinished);
        return false;
    }
}
