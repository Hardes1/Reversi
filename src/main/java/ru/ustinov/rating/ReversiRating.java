package ru.ustinov.rating;

import ru.ustinov.game.Side;
import ru.ustinov.player.PlayerType;

import java.util.Map;

import static java.lang.Math.max;

public final class ReversiRating extends Rating {

    @Override
    void updateScore(final Side side, final PlayerType playerType, final Integer newScore) {
        Map<PlayerType, Integer> scoreTable = getScoreTable(side);
        Integer updatedScore = max(newScore, scoreTable.getOrDefault(playerType, DEFAULT_SCORE));
        scoreTable.put(playerType, updatedScore);
    }

    @Override
    public String toString() {
        Map<PlayerType, Integer> whiteScoreTable = getScoreTable(Side.WHITE);
        Map<PlayerType, Integer> blackScoreTable = getScoreTable(Side.BLACK);

        if (whiteScoreTable.isEmpty() && blackScoreTable.isEmpty()) {
            return ReversiRatingConstants.NO_GAMES_DURING_SESSION;
        }
        return String.format(
                ReversiRatingConstants.RESULT,
                Side.WHITE,
                getOrDefaultString(whiteScoreTable, PlayerType.HUMAN),
                getOrDefaultString(whiteScoreTable, PlayerType.COMPUTER)
        ) + String.format(
                ReversiRatingConstants.RESULT,
                Side.BLACK,
                getOrDefaultString(blackScoreTable, PlayerType.HUMAN),
                getOrDefaultString(blackScoreTable, PlayerType.COMPUTER)
        );
    }
    private String getOrDefaultString(final Map<PlayerType, Integer> scoreTable, PlayerType type) {
        if (!scoreTable.containsKey(type)) {
            return ReversiRatingConstants.NO_GAMES_FOR_PLAYER_TYPE;
        }
        return scoreTable.get(type).toString();
    }

}
