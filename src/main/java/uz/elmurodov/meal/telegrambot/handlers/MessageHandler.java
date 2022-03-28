package uz.elmurodov.meal.telegrambot.handlers;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import uz.elmurodov.meal.telegrambot.processors.register.RegisterMessageProcessor;
import uz.elmurodov.meal.telegrambot.utils.BaseUtils;

@Component
public class MessageHandler{
    private final RegisterMessageProcessor registerProcessor;
    private final BaseUtils utils;

    public MessageHandler(RegisterMessageProcessor registerProcessor, BaseUtils utils) {
        this.registerProcessor = registerProcessor;
        this.utils = utils;
    }

    public void handle(Update update) {
        Message message = update.getMessage();
        String chatId = message.getChatId().toString();

        if (utils.isAnonymous(chatId)) {
            registerProcessor.doProcess(message);
        }

    }
}
