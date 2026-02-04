package backend.api.repositories;

import backend.api.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface HiscoreRepository extends JpaRepository<Game, Long> {
    Optional<Game> findByName(String name);

    // https://www.w3schools.com/sql/sql_like.asp
    // https://www.w3schools.com/sql/func_sqlserver_concat.asp
    // finds a game where the player has not defined a name and the server has defined a default name
    // query counts unique entries where the name is either exactly the default name
    // or starts with the default name followed by a space and some other characters
    @Query("""
            SELECT COUNT(g)
            FROM Game g
            WHERE g.name = :defaultName
            OR g.name LIKE CONCAT(:defaultName, ' %')
            """)
    long findGamesContainingDefaultName(@Param(("defaultName")) String defaultName);
}
