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
        if (!update.hasMessage() && update.getMessage().hasText()) return;
        String text = update.getMessage().getText();
        Long chatId = update.getMessage().getChatId();

        String answerMessage = "";

        switch (text){
            case Command.START ->   answerMessage = answerCommand.menu();
            case Command.ARRAY_NUM ->   answerMessage = answerCommand.array_numbers();
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
