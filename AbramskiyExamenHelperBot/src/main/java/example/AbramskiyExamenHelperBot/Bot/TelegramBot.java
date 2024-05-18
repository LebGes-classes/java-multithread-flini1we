package example.AbramskiyExamenHelperBot.Bot;

import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Component
public class TelegramBot extends TelegramLongPollingBot {
    String[] listOfLinks = new String[] {
            "https://drive.google.com/file/d/1WizTh1TyR_hdsRo4IrNVKiaKnz_9WJAl/view?usp=sharing",
            "https://drive.google.com/file/d/1jb2ja4gBv_uPIZgLEM6XwdXuZxh4jAM5/view?usp=sharing",
            "https://drive.google.com/file/d/1UgYodxfMj1N_W9mF8vBJVwy2YHIb7GDe/view?usp=sharing",
            "https://drive.google.com/file/d/10VZPioLilpa-98RZA6dbZ6e9wvdFdjp4/view?usp=sharing",
            "https://drive.google.com/file/d/1uhnhSRn7epijE0slHyuHy_kOUr2XBtUU/view?usp=sharing",
            "https://drive.google.com/file/d/1Q3RCcA_Oznk2WZqO9Ai0cnXJj4w0cUpk/view?usp=sharing",
            "https://drive.google.com/file/d/1w76dW2B1ui0IMdkab1JRLsVD8zJAhLb_/view?usp=sharing",
            "https://drive.google.com/file/d/1KhaIrSQXnK7BygxbVL2OESV0y_zrgO6s/view?usp=sharing",
            "https://drive.google.com/file/d/1Y6CZrtz7f5iSRht89zxCUu0svZFiUGcg/view?usp=sharing",
    };
    String[] firstPartOfReply = new String[] {
            "Подзаебала эта тема\n",
            "Это круто! Это реально круто!\n",
            "Ну как это происходит- я не понимаю..У кого-то это щелкает, а у кого-то это не щелкает\n",
            "Анвар мне похуй, я так чувствую\n",
            "Мы все виноваты в этом пиздеце...\n",
            "Я их не понимаю, мне это не интересно\n",
            "Это было не просто смело..Это было пиздец как смело\n",
            "Я реально..прихерел..\n",
            "Ну это пиздец какой-то ну, сколько можно...\n"
    };
    public String GREETING = "/start";
    final Config config;
    /*
    Initializing threads pull
     */
    private ExecutorService executorService = Executors.newFixedThreadPool(10);
    public TelegramBot(Config config) {
        this.config = config;
    }
    @Override
    public String getBotUsername() {
        return config.getBotName();
    }
    @Override
    public String getBotToken() {
        return config.getToken();
    }
    @Override
    public void onUpdateReceived(
            Update update
    ) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();

            long chatId = update.getMessage().getChat().getId();
            String userName = update.getMessage().getChat().getFirstName();
            /*
            Processing each incoming request in a separate thread,
            to avoid blocking the main thread
            -----------------------UPDATED-----------------------
            Adding Future<?> construction to work asynchronous
            Future<?> - interface witch gives us results of operations
            */
            Future<?> future = executorService.submit(() -> {
                switch (messageText) {
                    case "/start" ->
                        startCommand(
                                chatId, userName
                        );
                    case "1 - Вспомним 1 семестр" ->
                        sendMessage(
                                chatId, firstPartOfReply[0] + listOfLinks[0]
                        );
                    case "2 - Джинерики, Коллекции - начало" ->
                        sendMessage(
                                chatId, firstPartOfReply[1] + listOfLinks[1]
                    );
                    case "3 - Коллекции продолжение" ->
                        sendMessage(
                                chatId, firstPartOfReply[2] + listOfLinks[2]
                        );
                    case "4 - Функциональная Парадигма" ->
                            sendMessage(
                                    chatId, firstPartOfReply[3] + listOfLinks[3]
                            );
                    case "5 - Тестирование Разработчикам" ->
                            sendMessage(
                                    chatId, firstPartOfReply[4] + listOfLinks[4]
                            );
                    case "6 - Input/Output Потоки" ->
                            sendMessage(
                                    chatId, firstPartOfReply[5] + listOfLinks[5]
                            );
                    case "7 - Введение в многопоточность" ->
                            sendMessage(
                                    chatId, firstPartOfReply[6] + listOfLinks[6]
                            );
                    case "8 - Рефлексия" ->
                            sendMessage(
                                    chatId, firstPartOfReply[7] + listOfLinks[7]
                            );
                    case "9 - Введение в Паттерны Проектирования" ->
                            sendMessage(
                                    chatId, firstPartOfReply[8] + listOfLinks[8]
                            );
                }
            });
            // Blocking current thread
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
    // Stopping the program within closing app
    @PreDestroy
    public void destroy() {
        executorService.shutdownNow();
    }
    private void startCommand (
            long chatId, String userName
    ) {
        String answer = "Привет, " + userName + "!\n" +
                        "Я - специальный бот, сделанный Данилом и Тагиром.\n Моя цель - облегчить твою подготовку)" +
                        "Отправь мне цифру от 1 до 9, и я отправлю тебе презентацию М.М.Абрамского\n\n";
        sendMessage(
                chatId, answer
        );
    }

    private void sendMessage (
            long chatId, String message
    ) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId("" + chatId);
        sendMessage.setText(message);

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        ArrayList<KeyboardRow> keyboardRows = getKeyboardRows();

        keyboardMarkup.setKeyboard(keyboardRows);
        sendMessage.setReplyMarkup(keyboardMarkup);

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private static ArrayList<KeyboardRow> getKeyboardRows() {
        ArrayList<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();

        row.add("1 - Вспомним 1 семетр");
        row.add("2 - Джинерики, Коллекции - начало");
        row.add("3 - Коллекции продолжение");
        keyboardRows.add(row);

        row = new KeyboardRow();
        row.add("4 - Функциональная Парадигма");
        row.add("5 - Тестирование Разработчикам");
        row.add("6 - Input/Output Потоки");
        keyboardRows.add(row);

        row = new KeyboardRow();
        row.add("7 - Введение в многопоточность");
        row.add("8 - Рефлексия");
        row.add("9 - Введение в Паттерны Проектирования");
        keyboardRows.add(row);
        return keyboardRows;
    }
}
