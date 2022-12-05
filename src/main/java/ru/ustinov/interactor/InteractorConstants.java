package ru.ustinov.interactor;

import ru.ustinov.CommonConstants;

final class InteractorConstants extends CommonConstants {
    private InteractorConstants() {
        throw new IllegalStateException();
    }

    static final String PARTING = "Спасибо за игру, возвращайтесь снова!";

    static final String GREETING = "Приветствую вас в моей реализации игры \"Реверси\"!";

    static final String HELP = "Вам доступны следующие команды для ввода:\n" +
            String.format("\"%s\" - начать новую игру.\n", Command.PLAY) +
            String.format("\"%s\" - посмотреть это меню ещё раз.\n", Command.HELP) +
            String.format("\"%s\" - вывести наилучший рейтинг за сессию.\n", Command.RATING) +
            String.format("\"%s\" - закончить сессию.", Command.QUIT);

}
