package ru.ustinov.interactor;

import ru.ustinov.game.Game;
import ru.ustinov.game.ReversiGame;
import ru.ustinov.rating.Rating;
import ru.ustinov.rating.ReversiRating;

import java.util.Scanner;

public final class ReversiInteractor implements Interactor {
    private boolean isFinished = false;

    private final Scanner scanner = new Scanner(System.in);

    private final Game game = new ReversiGame();

    private final Rating rating = new ReversiRating();

    @Override
    public void run() {
        System.out.println(InteractorConstants.GREETING);
        System.out.println(InteractorConstants.HELP);
        do {
            String input = scanner.next();
            try {
                Command command = Command.valueOf(input);
                switch (command) {
                    case PLAY -> game.begin();
                    case HELP -> System.out.println(InteractorConstants.HELP);
                    case RATING -> System.out.println(rating);
                    case QUIT -> isFinished = true;
                }
            } catch (IllegalArgumentException e) {
                System.out.println(InteractorConstants.INCORRECT_COMMAND);
            }
        } while (!isFinished);
        System.out.println(InteractorConstants.PARTING);
    }
}
