package ru.ustinov.game;

public enum Side {
    WHITE("Белые"),
    BLACK("Чёрные");
    private final String name;

    Side(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
