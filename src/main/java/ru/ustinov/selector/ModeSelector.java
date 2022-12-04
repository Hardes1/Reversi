package ru.ustinov.selector;

import ru.ustinov.game.Side;
import ru.ustinov.player.PlayerType;

public interface ModeSelector {
    PlayerType selectOpponent();

    Side selectSide();
}
