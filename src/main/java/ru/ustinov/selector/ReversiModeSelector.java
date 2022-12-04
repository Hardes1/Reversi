package ru.ustinov.selector;

import ru.ustinov.game.Side;
import ru.ustinov.player.PlayerType;

import java.util.Random;
import java.util.Scanner;

public class ReversiModeSelector implements ModeSelector {
    Scanner scanner = new Scanner(System.in);
    Random random = new Random(System.currentTimeMillis());

    @Override
    public PlayerType selectOpponent() {
        boolean isFound = false;
        PlayerType playerType = null;
        System.out.println(ModeSelectorConstants.HELP_CHOOSE_OPPONENT);
        do {
            try {
                String input = scanner.nextLine();
                SelectorPlayerType selectorPlayerType = SelectorPlayerType.valueOf(input);
                switch (selectorPlayerType) {
                    case HUMAN -> {
                        isFound = true;
                        playerType = PlayerType.HUMAN;
                    }
                    case WEAK_COMPUTER -> {
                        isFound = true;
                        playerType = PlayerType.WEAK_COMPUTER;
                    }
                    case STRONG_COMPUTER -> {
                        isFound = true;
                        playerType = PlayerType.STRONG_COMPUTER;
                    }
                    case RANDOM -> {
                        int randomResult = random.nextInt(ModeSelectorConstants.PLAYER_TYPE_COUNT);
                        isFound = true;
                        if (randomResult == 0) {
                            playerType = PlayerType.HUMAN;
                        } else if (randomResult == 1) {
                            playerType = PlayerType.WEAK_COMPUTER;
                        } else {
                            playerType = PlayerType.STRONG_COMPUTER;
                        }
                    }
                    case BACK -> {
                        System.out.println(ModeSelectorConstants.GET_BACK);
                        isFound = true;
                    }
                    case HELP -> System.out.println(ModeSelectorConstants.HELP_CHOOSE_OPPONENT);
                }
            } catch (IllegalArgumentException e) {
                System.out.println(ModeSelectorConstants.INCORRECT_COMMAND);
            }
        } while (!isFound);
        if (playerType != null) {
            System.out.println(String.format(ModeSelectorConstants.INFORM_ABOUT_CHOSEN_OPPONENT, playerType));
        }
        return playerType;
    }

    @Override
    public Side selectSide() {
        boolean isFound = false;
        Side side = null;
        System.out.println(ModeSelectorConstants.HELP_CHOOSE_SIDE);
        do {
            try {
                String input = scanner.nextLine();
                SelectorSide selectorSide = SelectorSide.valueOf(input);
                switch (selectorSide) {
                    case WHITE -> {
                        isFound = true;
                        side = Side.WHITE;
                    }
                    case BLACK -> {
                        isFound = true;
                        side = Side.BLACK;
                    }
                    case RANDOM -> {
                        int randomResult = random.nextInt(ModeSelectorConstants.SIDES_COUNT);
                        isFound = true;
                        side = randomResult == 0 ? Side.WHITE : Side.BLACK;
                    }
                    case BACK -> {
                        System.out.println(ModeSelectorConstants.GET_BACK);
                        isFound = true;
                    }
                    case HELP -> System.out.println(ModeSelectorConstants.HELP_CHOOSE_SIDE);
                }
            } catch (IllegalArgumentException e) {
                System.out.println(ModeSelectorConstants.INCORRECT_COMMAND);
            }
        } while (!isFound);
        if (side != null) {
            System.out.println(String.format(ModeSelectorConstants.INFORM_ABOUT_CHOSEN_SIDE, side));
        }
        return side;
    }
}
