package CoorpMsg.controller;

import CoorpMsg.domain.Chat;
import CoorpMsg.domain.MessageChat;
import CoorpMsg.domain.User;
import CoorpMsg.repos.ChatRepo;
import CoorpMsg.repos.MessageChatRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
@Controller
public class ChatController {
    @Autowired
    private ChatRepo chatRepo;

    @Autowired
    private MessageChatRepo messageChatRepo;

    @GetMapping("/chat")
    public String chat(Map<String, Object> model){
        Iterable<Chat> chats = chatRepo.findAll();
        model.put("chat", chats);
        return "chat";
    }

    @PostMapping("/chat")
    public String add(@AuthenticationPrincipal User user,
                      @RequestParam String name,
                      Map<String, Object> model){
        Chat chat = new Chat(name, user);
        chatRepo.save(chat);
        Iterable<Chat> chats = chatRepo.findAll();
        model.put("chat", chats);
        return "chat";
    }



    @GetMapping("/chat/{id}")
    public String getById (@PathVariable("id") Integer id, Map<String, Object> model ) {
        Iterable<MessageChat> messageChat = messageChatRepo.findByChatId(id);
        model.put("messages", messageChat);
        return "chatForm";
    }

    @PostMapping ("/chat/{id}")
    public String add(@AuthenticationPrincipal User user,
                      @PathVariable("id") Integer chatId,
                      @RequestParam String text,
                      Map<String, Object> model){
        MessageChat message = new MessageChat(text, user, chatId);
        messageChatRepo.save(message);
        Iterable<MessageChat> messageChat = messageChatRepo.findByChatId(chatId);
        model.put("messages", messageChat);

        return  "chatForm";
    }
}
