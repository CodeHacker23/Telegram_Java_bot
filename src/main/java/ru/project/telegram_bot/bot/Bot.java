package ru.project.telegram_bot.bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.groupadministration.GetChatMember;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.chatmember.ChatMember;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.project.telegram_bot.db.CounterRequest;
import ru.project.telegram_bot.db.DataBase;
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
        System.out.println(update);
        //если это не сообщение и не текст то выъодим из мтеода
        if (!update.hasMessage() && update.getMessage().hasText()) return;



        String text = update.getMessage().getText(); //запрос
        Long chatId = update.getMessage().getChatId(); //id чата
        Long userId = update.getMessage().getFrom().getId(); //id user


        System.out.println(userId);
        boolean userAdmin = isUserAdmin(chatId.toString(), userId);
        System.out.println("userAdmin: " + userAdmin);

        String answerMessage = ""; // для ответа

        /**
         * проверяем запрос для генерации ответа
         *
         * @text сообщение от пользователя
         * @сommand... Класс с командами (строки в переменных)
         * @answerCommand объект для генерации ответа (вернет строку ответа)
         */
        switch (text){
            case Command.START ->  {
                answerMessage = answerCommand.menu();
            }
            case Command.ARRAY_NUM ->   {
                answerMessage = answerCommand.array_numbers();
                CounterRequest.count++;
            }
            case  Command.ARRAY_NAMES -> {
                answerMessage = answerCommand.array_names();
                CounterRequest.count++;
            }
            case  Command.STATISTIC ->  {
                if (userId == DataBase.ADMIN1_ID || userId == DataBase.ADMIN2_ID) {
                    answerMessage = "кол во запросов всего было: " +CounterRequest.count;
                }else {
                    answerMessage = "иш чего захотел!";
                }

            }
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


    public boolean isUserAdmin(String chatId, long userId) {
        try {
            // Создание запроса для получения информации о члене чата
            GetChatMember getChatMember = new GetChatMember();
            getChatMember.setChatId(chatId); // ID чата
            getChatMember.setUserId(userId); // ID пользователя

            // Отправка запроса и получение результата
            ChatMember chatMember = execute(getChatMember);

            // Проверка статуса пользователя
            return chatMember.getStatus().equals("administrator") || chatMember.getStatus().equals("creator");
        } catch (TelegramApiException e) {
            e.printStackTrace(); // Обработка ошибок
        }
        return false; // Если произошла ошибка, считаем, что пользователь не администратор
    }

}
