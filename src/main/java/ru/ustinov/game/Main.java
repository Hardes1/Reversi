package ru.ustinov.game;

import ru.ustinov.interactor.Interactor;
import ru.ustinov.interactor.ReversiInteractor;

public class Main {
    public static void main(String[] args) {
        Interactor interactor = new ReversiInteractor();
        interactor.run();
    }
}