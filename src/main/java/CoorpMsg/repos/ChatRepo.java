package CoorpMsg.repos;

import CoorpMsg.domain.Chat;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ChatRepo extends CrudRepository<Chat, Long> {
}

