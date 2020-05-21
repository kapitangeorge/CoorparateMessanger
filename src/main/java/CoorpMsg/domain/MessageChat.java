package CoorpMsg.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MessageChat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer chatId;
    private String text;
    private User author;
    MessageChat(){

    }
    public MessageChat(String text, User user, Integer chatId){
        this.text = text;
        this.author = user;
        this.chatId = chatId;
    }
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }



    public Integer getChat_id() {
        return chatId;
    }

    public void setChat_id(Integer chat_id) {
        chatId = chat_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


}