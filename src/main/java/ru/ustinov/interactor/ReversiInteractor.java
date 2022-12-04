package ru.ustinov.interactor;

import ru.ustinov.game.Game;
import ru.ustinov.game.ReversiGame;
import ru.ustinov.game.Side;
import ru.ustinov.player.PlayerType;
import ru.ustinov.rating.Rating;
import ru.ustinov.rating.ReversiRating;
import ru.ustinov.selector.ModeSelector;
import ru.ustinov.selector.ReversiModeSelector;

import java.util.Scanner;

public final class ReversiInteractor implements Interactor {
    private boolean isFinished = false;

    private final Scanner scanner = new Scanner(System.in);

    private final Rating rating = new ReversiRating();

    private final ModeSelector selector = new ReversiModeSelector();

    @Override
    public void run() {
        System.out.println(InteractorConstants.GREETING);
        System.out.println(InteractorConstants.HELP);
        do {
            String input = scanner.nextLine();
            try {
                Command command = Command.valueOf(input);
                switch (command) {
                    case PLAY -> {
                        Side side = selector.selectSide();
                        if (side == null) {
                            System.out.println(InteractorConstants.HELP);
                            break;
                        }
                        PlayerType playerType = selector.selectOpponent();
                        if (playerType == null) {
                            System.out.println(InteractorConstants.HELP);
                            break;
                        }
                        Game game = new ReversiGame(rating, side, playerType);
                        game.begin();
                    }
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
