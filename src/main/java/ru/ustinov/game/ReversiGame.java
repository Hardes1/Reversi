package ru.ustinov.game;

import ru.ustinov.items.Board;
import ru.ustinov.items.ReversiBoard;
import ru.ustinov.items.SidesScore;
import ru.ustinov.player.*;
import ru.ustinov.rating.Rating;

public class ReversiGame implements Game {
    private final Rating rating;

    private final Player firstPlayer;

    private final Player secondPlayer;

    private final Board board = new ReversiBoard(GameConstants.BOARD_SIZE);

    private Side currentSide = Side.BLACK;

    public ReversiGame(final Rating rating, final Side side, final PlayerType opponentType) {
        this.rating = rating;
        firstPlayer = new Human(PlayerType.HUMAN, side);
        Side oppositeSide = side == Side.BLACK ? Side.WHITE : Side.BLACK;
        secondPlayer = switch (opponentType) {
            case HUMAN -> new Human(PlayerType.HUMAN, oppositeSide);
            case WEAK_COMPUTER -> new WeakComputer(PlayerType.WEAK_COMPUTER, oppositeSide);
            case STRONG_COMPUTER -> new StrongComputer(PlayerType.HUMAN, oppositeSide);
        };
    }

    @Override
    public void begin() {
        GameStatus status = GameStatus.RUNNING;
        do {
            board.print(currentSide);
            Player currentPlayer = selectPlayerToMove();
            if (!currentPlayer.tryMove(board)) {
                status = GameStatus.CANCELLED;
            }
            if (board.isGameFinished()) {
                status = GameStatus.FINISHED;
            }
            currentSide = currentSide == Side.BLACK ? Side.WHITE : Side.BLACK;
        } while (status == GameStatus.RUNNING);
        System.out.println(GameConstants.FINISH);
        refreshBestScore();
    }

    private void refreshBestScore() {
        SidesScore score = board.getScore();
        if (firstPlayer.getSide() == Side.WHITE) {
            rating.updateScore(Side.WHITE, firstPlayer.getPlayerType(), score.whiteScore());
            rating.updateScore(Side.BLACK, secondPlayer.getPlayerType(), score.blackScore());
        } else {
            rating.updateScore(Side.WHITE, secondPlayer.getPlayerType(), score.whiteScore());
            rating.updateScore(Side.BLACK, firstPlayer.getPlayerType(), score.blackScore());
        }
    }

    private Player selectPlayerToMove() {
        if (firstPlayer.getSide() == currentSide) {
            return firstPlayer;
        } else {
            return secondPlayer;
        }
    }
}

