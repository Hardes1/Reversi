package ru.ustinov.rating;

import ru.ustinov.game.Side;
import ru.ustinov.player.PlayerType;

import java.util.HashMap;
import java.util.Map;


public abstract class Rating {
    protected static final int DEFAULT_SCORE = 0;

    private final Map<PlayerType, Integer> whiteScore = new HashMap<>();

    private final Map<PlayerType, Integer> blackScore = new HashMap<>();

    abstract void updateScore(final Side side, final PlayerType playerType, final Integer newScore);

    protected Map<PlayerType, Integer> getScoreTable(Side side) {
        return side == Side.BLACK ? blackScore : whiteScore;
    }
}
