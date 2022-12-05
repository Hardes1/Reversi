package ru.ustinov.score;

public class ScoreCounterConstants {
    private ScoreCounterConstants() {
        throw new IllegalStateException();
    }

    static final double CELL_SCORE = 0.8;

    static final double BORDER_SCORE = 0.4;

    static final double OTHERS_SCORE = 0;

    static final int BORDER_EATEN_SCORE = 2;

    static final int OTHERS_EATEN_SCORE = 1;
}
