package ru.ustinov.game;

public class GameConstants {
    private GameConstants() {
        throw new IllegalStateException();
    }

    static final String FINISH = """
            Поздравляем, игра завершена победой %s со счётом белые - %d чёрные - %d.
            Обновляем рекорды и возвращаемся в главное меню.""";

    static final String CANCEL = "Игра была отменена, возвращаемся в главное меню.";

    static final int BOARD_SIZE = 8;

    public static final int MINIMAL_BOARD_SIZE = 0;

    static final String MOVE_TURN = "Сейчас ходят %s (%s)";

    static final String NOT_IMPLEMENTED = "Увы, человечество ещё не дошло до таких технологий.";

}
