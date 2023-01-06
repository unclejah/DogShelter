package ru.tt.DogShelter.KeyBoard;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import com.vdurmont.emoji.EmojiParser;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tt.DogShelter.listener.TelegramBotUpdatesListener;

@Service
public class KeyBoardShelter {
    @Autowired
    private TelegramBot telegramBot;

    private org.slf4j.Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);


    /**
     * Меню выбора
     *
     * @param chatId
     */

    public void chooseMenu(long chatId) {
        logger.info("Method sendMessage has been run: {}, {}", chatId, "Вызвано меню выбора ");
        String emoji_dog = EmojiParser.parseToUnicode(":dog:");
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(
                new KeyboardButton(emoji_dog + " DOG"));

        returnResponseReplyKeyboardMarkup(replyKeyboardMarkup, chatId, "Выберите, кого хотите приютить:");
    }

    /**
     * Основеное Меню
     *
     * @param chatId
     */
    public void sendMenu(long chatId) {
        logger.info("Method sendMessage has been run: {}, {}", chatId, "Вызвано основное меню ");
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(
                new KeyboardButton("Информация о возможностях бота"),
                new KeyboardButton("Узнать информацию о приюте"));
        replyKeyboardMarkup.addRow(new KeyboardButton("Как взять питомца из приюта"),
                new KeyboardButton("Прислать отчет о питомце"));
        replyKeyboardMarkup.addRow(new KeyboardButton("Позвать волонтера"));

        returnResponseReplyKeyboardMarkup(replyKeyboardMarkup, chatId, "Главное меню");
    }

    /**
     * Меню информации о приюте
     *
     * @param chatId
     */
    public void sendMenuInfoShelter(long chatId) {
        logger.info("Method sendMenuInfoShelter has been run: {}, {}", chatId, "Вызвали Информация о приюте");
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(new KeyboardButton("Информация о приюте"),
                new KeyboardButton("Оставить контактные данные").requestContact(true));
        replyKeyboardMarkup.addRow(new KeyboardButton("Позвать волонтера"),
                new KeyboardButton("Вернуться в меню"));
        returnResponseReplyKeyboardMarkup(replyKeyboardMarkup, chatId, "Информация о приюте");
    }

    /**
     * Меню как взять питомца
     *
     * @param chatId
     */
    public void sendMenuTakeAnimal(long chatId) {
        logger.info("Method sendMenuTakeAnimal has been run: {}, {}", chatId, "вызвали Как взять питомца из приюта");
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(
                new KeyboardButton("Советы и рекомендации"),
                new KeyboardButton("Оставить контактные данные").requestContact(true));
        replyKeyboardMarkup.addRow(new KeyboardButton("Позвать волонтера"),
                new KeyboardButton("Вернуться в меню"));
        returnResponseReplyKeyboardMarkup(replyKeyboardMarkup, chatId, "Как взять питомца из приюта");
    }

    public void returnResponseReplyKeyboardMarkup(ReplyKeyboardMarkup replyKeyboardMarkup, Long chatId, String text) {
        replyKeyboardMarkup.resizeKeyboard(true);
        replyKeyboardMarkup.oneTimeKeyboard(false);
        replyKeyboardMarkup.selective(false);
        SendMessage request = new SendMessage(chatId, text)
                .replyMarkup(replyKeyboardMarkup)
                .parseMode(ParseMode.HTML)
                .disableWebPagePreview(true);
        SendResponse sendResponse = telegramBot.execute(request);
        if (!sendResponse.isOk()) {
            int codeError = sendResponse.errorCode();
            String description = sendResponse.description();
            logger.info("code of error: {}", codeError);
            logger.info("description -: {}", description);
        }
    }
}
