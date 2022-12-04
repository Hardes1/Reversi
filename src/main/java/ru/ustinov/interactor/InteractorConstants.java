package ru.ustinov.interactor;

import ru.ustinov.CommonConstants;

final class InteractorConstants extends CommonConstants {
    final static String PARTING = "Спасибо за игру, возвращайтесь снова!";

    final static String GREETING = "Приветствую вас в моей реализации игры \"Реверси\"!";

    final static String HELP = "Вам доступны следующие команды для ввода:\n" +
            String.format("\"%s\" - начать новую игру.\n", Command.PLAY) +
            String.format("\"%s\" - посмотреть это меню ещё раз.\n", Command.HELP) +
            String.format("\"%s\" - вывести наилучший рейтинг за сессию.\n", Command.RATING) +
            String.format("\"%s\" - закончить сессию.", Command.QUIT);

}
