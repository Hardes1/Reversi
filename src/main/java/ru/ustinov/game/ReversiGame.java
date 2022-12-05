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
        if (secondPlayer instanceof StrongComputer || firstPlayer instanceof StrongComputer) {
            System.out.println(GameConstants.NOT_IMPLEMENTED);
            status = GameStatus.CANCELLED;
        }
        while (status == GameStatus.RUNNING) {
            board.print(currentSide);
            Player currentPlayer = selectPlayerToMove();
            System.out.println(String.format(
                    GameConstants.MOVE_TURN,
                    currentPlayer.getSide(),
                    currentPlayer.getPlayerType()
            ));

            if (!currentPlayer.tryMove(board)) {
                status = GameStatus.CANCELLED;
            }
            if (board.isGameFinished()) {
                status = GameStatus.FINISHED;
            }
            currentSide = currentSide == Side.BLACK ? Side.WHITE : Side.BLACK;
        }
        if (status == GameStatus.FINISHED) {
            SidesScore score = board.getSidesScore();
            if (score.whiteScore() > score.blackScore()) {
                System.out.println(
                        String.format(GameConstants.FINISH, Side.WHITE, score.whiteScore(), score.blackScore())
                );
            } else {
                System.out.println(
                        String.format(GameConstants.FINISH, Side.BLACK, score.whiteScore(), score.blackScore())
                );
            }
            refreshBestScore();
        } else {
            System.out.println(GameConstants.CANCEL);
        }
    }

    private void refreshBestScore() {
        SidesScore score = board.getSidesScore();
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

