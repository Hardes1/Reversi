package ru.ustinov.selector;

import ru.ustinov.CommonConstants;

class ModeSelectorConstants extends CommonConstants {
    static final String HELP_CHOOSE_SIDE = """
            Выберите сторону за которую будете играть:
            "WHITE" - белые.
            "BLACK" - чёрные.
            "RANDOM" - случайная сторона.
            "HELP" - вывести это меню ещё раз.
            "BACK" - вернуться в главное меню.""";

    static final String INFORM_ABOUT_CHOSEN_SIDE = "Вы - %s";

    static final String HELP_CHOOSE_OPPONENT = """
            Выберите вашего сопернкиа:
            "HUMAN" - соперник человек.
            "WEAK_COMPUTER - соперник слабый компьютер.
            "STRONG_COMPUTER - соперник сильный компьютер.
            "RANDOM" - случайный соперник.
            "HELP" - вывести это меню ещё раз.
            "BACK" - вернуться в главное меню.""";

    static final String INFORM_ABOUT_CHOSEN_OPPONENT = "Вашим соперником будет - %s";

    static final String GET_BACK = "Возвращаемся в главное меню.";

    static final int SIDES_COUNT = 2;

    static final int PLAYER_TYPE_COUNT = 3;
}
