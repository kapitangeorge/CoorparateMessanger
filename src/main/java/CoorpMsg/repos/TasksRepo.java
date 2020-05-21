package CoorpMsg.repos;

import CoorpMsg.domain.Tasks;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TasksRepo extends CrudRepository<Tasks, Long> {
    List<Tasks> findByIdProj (Integer idProj);
    Tasks findById (Integer id);
}
