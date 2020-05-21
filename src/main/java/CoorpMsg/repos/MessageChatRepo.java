package CoorpMsg.repos;
import CoorpMsg.domain.MessageChat;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageChatRepo extends CrudRepository<MessageChat, Long> {
    List<MessageChat> findByChatId(Integer chatId);
}

