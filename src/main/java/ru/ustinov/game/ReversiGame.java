package ru.ustinov.game;

import ru.ustinov.player.Player;
import ru.ustinov.player.PlayerType;
import ru.ustinov.rating.Rating;
import ru.ustinov.selector.ModeSelector;
import ru.ustinov.selector.ReversiModeSelector;

public class ReversiGame implements Game {
    private final Rating rating;

    private final ModeSelector modeSelector = new ReversiModeSelector();

    private final Player firstPlayer;

    private final Player secondPlayer;

    public ReversiGame(final Rating rating, final Side side, final PlayerType opponentType) {
        this.rating = rating;
        firstPlayer = null;
        secondPlayer = null;
    }

    @Override
    public void begin() {
    }
}
