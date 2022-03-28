package uz.elmurodov.meal.telegrambot.handlers;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import uz.elmurodov.meal.telegrambot.utils.BaseUtils;
//import org.telegram.telegrambots.meta.api.objects.Message;
//import org.telegram.telegrambots.meta.api.objects.Update;
import uz.elmurodov.meal.telegrambot.processors.register.RegisterCallBackProcessor;
//import uz.elmurodov.meal.telegrambot.utils.BaseUtils;

@Component
public class CallbackHandler{
    private final RegisterCallBackProcessor processor;
    private final BaseUtils utils;

    public CallbackHandler(RegisterCallBackProcessor processor, BaseUtils utils) {
        this.processor = processor;
        this.utils = utils;
    }

    public void handle(Update update) {
        Message message = update.getCallbackQuery().getMessage();
        String chatId = message.getChatId().toString();
        if (utils.isAnonymous(chatId)) {
            processor.doProcess(update.getCallbackQuery());
        }
    }
}
