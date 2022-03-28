package uz.elmurodov.meal.telegrambot.processors.register;


import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import uz.elmurodov.meal.enums.Department;
import uz.elmurodov.meal.enums.Position;
import uz.elmurodov.meal.telegrambot.buttons.InlineBoard;
import uz.elmurodov.meal.telegrambot.utils.MessageExecutorService;
import uz.elmurodov.meal.telegrambot.utils.State;
import uz.elmurodov.meal.telegrambot.utils.UserStates;

@Component
public class RegisterCallBackProcessor{
        private final MessageExecutorService executorService;

    public RegisterCallBackProcessor(MessageExecutorService executorService) {
        this.executorService = executorService;
    }

    public void doProcess(CallbackQuery callbackQuery) {
        Message message = callbackQuery.getMessage();
        String chatId = message.getChatId().toString();
        String data = callbackQuery.getData();

        if (data.startsWith("d")) {
            int departmentOrder = Integer.parseInt(data.substring(data.indexOf("d_") + 2));
            Department department = Department.getByOrder(departmentOrder);
            // TODO: 10/03/22 save department
            executorService.deleteMessage(chatId, message.getMessageId());
            executorService.sendMessage(chatId, "Choose your position please", InlineBoard.positions());
        }
        if (data.startsWith("p_")) {
            int order = Integer.parseInt(data.substring(data.indexOf("p_") + 2));
            Position position = Position.getByOrder(order);
            // TODO: 10/03/22 save position
            // TODO: 10/03/22 save new user to db
            executorService.deleteMessage(chatId, message.getMessageId());
            executorService.sendMessage(chatId, "We sent your data to your head department. please wait untill your ....");
            UserStates.setState(chatId, State.WAITING_CONFIRMATION);
        }
    }
}
