package backend.api.repositories;

import backend.api.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HiscoreRepository extends JpaRepository<Game, Long> {
}
