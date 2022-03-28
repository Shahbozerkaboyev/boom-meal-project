package uz.elmurodov.meal.telegrambot;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@RequiredArgsConstructor
public class TelegramBot {
    private final TelegramBotConfiguration telegramBotConfiguration;

    public void run() {
//        TelegramBotsApi telegramBotsApi =
//                new TelegramBotsApi(telegramBotConfiguration);
    }
}
