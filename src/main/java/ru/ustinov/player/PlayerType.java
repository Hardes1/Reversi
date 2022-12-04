package ru.ustinov.player;

public enum PlayerType {
    HUMAN("человек"),
    WEAK_COMPUTER("слабый компьютер"),
    STRONG_COMPUTER("сильный компьютер");
    final String name;

    PlayerType(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
