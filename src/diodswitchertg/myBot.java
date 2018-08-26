package diodswitchertg;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import jssc.SerialPortException;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class myBot extends TelegramLongPollingBot {

    @Override
    public String getBotUsername() {

        return "PVV97Bot_bot";
    }

    @Override
    public String getBotToken() {

        return "640047738:AAHBmMm-CqE83m5tPxX5WUgsjGN44IelLQw";

    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String text = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();

            System.out.println("Incoming meassage: " + text + " from " + update.getMessage().getChat().getUserName());
            
           

            SendMessage message = new SendMessage();
            message.setChatId(chat_id);

            message.setText(text);
            
            if (text.equals("/help")) {
                message.setText("What do you want?");
            }
            if (text.equals("/time")) {
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date date = new Date();
                message.setText(dateFormat.format(date)); //2016/11/16 12:08:43
            }
            if (text.equals("/off")){
                message.setText("off");
                try {
                    ComConnection.serialPort.writeString("0");
                } catch (SerialPortException ex) {
                    System.out.println(ex.getMessage());
                }
            }
            
            if (text.equals("/on")){
                message.setText("on");
                try {
                    ComConnection.serialPort.writeString("1");
                } catch (SerialPortException ex) {
                    System.out.println(ex.getMessage());
                }
            }
               

            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

}
