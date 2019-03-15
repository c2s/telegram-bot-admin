package cc.mrbird.system.telegram.Bot;

import cc.mrbird.system.telegram.BotConfig;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class NoticeBot extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {


        SendMessage message1 = new SendMessage() // Create a SendMessage object with mandatory fields
                .setChatId(update.getMessage().getChatId())
                .setText(Long.toString(update.getMessage().getChatId()));
        try {
            execute(message1); // Call method to send the message
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }


        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
                    .setChatId(update.getMessage().getChatId())
                    .setText(update.getMessage().getText());
            try {
                execute(message); // Call method to send the message
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getBotUsername() {
        return BotConfig.NOTICE_USERNAME;
    }

    @Override
    public String getBotToken() {
        return BotConfig.NOTICE_TOKEN;
    }

}
