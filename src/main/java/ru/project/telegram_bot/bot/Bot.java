package ru.project.telegram_bot.bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.project.telegram_bot.service.AnswerCommand;
import ru.project.telegram_bot.service.Command;

import java.util.Arrays;


@Component
public class Bot extends TelegramLongPollingBot {

    @Autowired
    private AnswerCommand answerCommand;

    @Value("${bot.username}")
    private String botName;

    public Bot(@Value("${bot.token}") String botToken) {
        super(botToken);
    }


    @Override
    public String getBotUsername() {
        return botName;
    }


    @Override
    public void onUpdateReceived(Update update) {
        //если это не сообщение и не текст то выъодим из мтеода
        if (!update.hasMessage() && update.getMessage().hasText()) return;

        String text = update.getMessage().getText(); //запрос
        Long chatId = update.getMessage().getChatId(); //id чата

        String answerMessage = ""; // для ответа

        /**
         * проверяем запрос для генерации ответа
         *
         * @text сообщение от пользователя
         * @сommand... Класс с командами (строки в переменных)
         * @answerCommand объект для генерации ответа (вернет строку ответа)
         */
        switch (text){
            case Command.START ->   answerMessage = answerCommand.menu();
            case Command.ARRAY_NUM ->   answerMessage = answerCommand.array_numbers();
            case  Command.ARRAY_NAMES -> answerMessage = answerCommand.array_names();
            case Command.CITY -> answerMessage = answerCommand.getClassCity();
            case  Command.ARRAY_CITY -> answerMessage = answerCommand.array_city();
            case Command.LIST_CITY -> answerMessage=answerCommand.list_city();

        }



       send(answerMessage,chatId);



    }

    private void send(String answerMessage, Long chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(answerMessage);
        sendMessage.setChatId(chatId);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
