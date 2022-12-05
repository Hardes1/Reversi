package ru.ustinov.items;

public class BoardConstants {
    private BoardConstants() {
        throw new IllegalStateException();
    }

    static final String BOTTOM_NUMBERS = "  1 2 3 4 5 6 7 8";

    static final String BLACK_SYMBOL = "B";

    static final String WHITE_SYMBOL = "W";

    static final String NEUTRAL_SYMBOL = ".";

    static final String POSSIBLE_MOVE_SYMBOL = "+";

    static final String CURRENT_SCORE = "Текущий счёт: Белые - %d; Чёрные - %d";

    static final String HINT = """
            "B" - чёрная шашка; "W" - белая шашка; "+" - возможный ход
            "X Y" - сходить на позицию (X, Y); BACK - выйти в главное меню""";

    static final String POSSIBLE_MOVES = "Возможные ходы:";

    public static final int MINIMAL_COORDINATE = 0;

    static final int INITIAL_MOVES_USED = 4;

    static final String CELL_FORMAT = "(%d, %d)";
}
