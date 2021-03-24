//package com.example.aworldtoyou.telegram;
//
//
//import com.example.aworldtoyou.models.Bot;
//import com.example.aworldtoyou.repository.BotRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.stereotype.Service;
//import org.telegram.telegrambots.api.methods.send.SendMessage;
//import org.telegram.telegrambots.api.objects.Update;
//import org.telegram.telegrambots.bots.TelegramLongPollingBot;
//import org.telegram.telegrambots.exceptions.TelegramApiException;
//
//import java.util.List;
//
//@Service
//@PropertySource("classpath:telegram.properties")
//public class BotService extends TelegramLongPollingBot {
//
//    @Value("${bot.name}")
//    private String botName;
//
//    @Value("${bot.token}")
//    private String botToken;
//
//    @Value("${bot.chat_id}")
//    private Long chat_id;
//
//    @Override
//    public void onUpdateReceived(Update update) {
//    }
//
//    @Override
//    public void onUpdatesReceived(List<Update> updates) {
//    }
//
//    @Override
//    public String getBotUsername() {
//        return botName;
//    }
//
//    @Override
//    public String getBotToken() {
//        return botToken;
//    }
//
//    @Override
//    public void onClosing() {
//
//    }
//
//    @Autowired
//    private BotRepository botRepository;
//
//    public void setBotRepository(Bot bot) {
//
//        Bot message = Bot.builder()
//                .botform(bot.getBotform())
//                .botphone(bot.getBotphone())
//                .build();
//
//        botRepository.save(message);
//
//
//        StringBuilder sb = new StringBuilder("Щойно звилося нове повідомлення від :");
//        List<Bot> bots = botRepository.findAll();
//
//        bots.forEach(bot1 -> sb.append(bot.getBotform())
//                .append(" Номер телефону: ")
//                .append(bot.getBotphone())
//                .append("\r\n"));
//        botRepository.deleteAll();
//        sendMessage(sb.toString());
//    }
//
//    private void sendMessage(String text) {
//        SendMessage message = new SendMessage()
//                .setChatId(chat_id)
//                .setText(text);
//        try {
//            sendMessage(message);
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//}
