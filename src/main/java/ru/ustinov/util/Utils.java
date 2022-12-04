package ru.ustinov.util;

import ru.ustinov.game.Side;

public class Utils {
    public static Side getOppositeSide(Side current) {
        return current == Side.WHITE ? Side.BLACK : Side.WHITE;
    }
}
