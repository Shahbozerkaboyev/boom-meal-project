package uz.elmurodov.meal.telegrambot.processors.register;


import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import uz.elmurodov.meal.telegrambot.buttons.InlineBoard;
import uz.elmurodov.meal.telegrambot.buttons.MarkupBoard;
import uz.elmurodov.meal.telegrambot.dto.UserCreateDto;
import uz.elmurodov.meal.telegrambot.utils.MessageExecutorService;
import uz.elmurodov.meal.telegrambot.utils.State;
import uz.elmurodov.meal.telegrambot.utils.UserStates;

@Component
public class RegisterMessageProcessor {
    private final MessageExecutorService executorService;

    public RegisterMessageProcessor(MessageExecutorService executorService) {
        this.executorService = executorService;
    }


    public void doProcess(Message message) {
        String chatId = message.getChatId().toString();
        State state = UserStates.getState(chatId);
        Integer messageId = message.getMessageId();
        String text = message.getText();
        switch (state) {
            case UNDEFINED:
                askForFullName(chatId);
            case FULL_NAME: {
                saveFullName(chatId);
                askPhoneNumber(chatId);
            }
            case PHONE_NUMBER:
                savePhoneNumber(chatId, message);
            case DELETE:
                executorService.deleteMessage(chatId, messageId);
            case WAITING_CONFIRMATION:
                executorService.deleteMessage(chatId, messageId);
        }
    }

    private void askDepartment(String chatId) {
        executorService.sendMessage(chatId, "Choose your Department please", InlineBoard.departments());
        UserStates.setState(chatId, State.DELETE);
    }


    private void savePhoneNumber(String chatId, Message message) {
        String text = message.getText();
        if (!message.hasContact()) {
            executorService.deleteMessage(chatId, message.getMessageId());
        } else {
            askDepartment(chatId);
            UserCreateDto dto = UserStates.getUserCreateDto(chatId);
            dto.setPhoneNumber(text);
            // TODO: 10/03/22 save phone number
        }
    }


    private void askPhoneNumber(String chatId) {
        executorService.sendMessage(chatId, "Send your Phone number please", MarkupBoard.sharePhoneNumber());
    }


    private void saveFullName(String chatId) {
        // TODO: 10/03/22 save full name here
        UserStates.setState(chatId, State.PHONE_NUMBER);
    }


    private void askForFullName(String chatId) {
        executorService.sendMessage(chatId, "Send your full name please");
        UserStates.setState(chatId, State.FULL_NAME);
    }
}
