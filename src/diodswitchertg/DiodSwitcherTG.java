package diodswitchertg;

import javax.swing.JOptionPane;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class DiodSwitcherTG {

    //подключение к СOM порту
    public static ComConnection cc = new ComConnection();

    public static void main(String[] args) {
        
        //инициализируем API Telegram
        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();
        try {
            botsApi.registerBot(new myBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        String str = "";
        while (!"exit".equals(str)) {
            str = JOptionPane.showInputDialog("Enter Input:");
            cc.send(str);
            //cc.send(str);
        }

        

    }

}
