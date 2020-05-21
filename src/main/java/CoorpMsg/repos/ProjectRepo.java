package CoorpMsg.repos;

import CoorpMsg.domain.Project;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProjectRepo extends CrudRepository<Project, Long> {

    Project findById(Integer id);
}