package backend.api.services;

import backend.api.entities.Game;
import backend.api.exceptions.GameNotFoundException;
import backend.api.repositories.HiscoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {
    public static final String DEFAULT_GAME_NAME = "Unknown Game";
    private final HiscoreRepository repository;

    public GameService(HiscoreRepository repository) {
        this.repository = repository;
    }

    public List<Game> getAllGames() {
        List<Game> games = repository.findAll();

        return games.stream()
                .sorted((g1, g2) -> Long.compare(g2.getScore(), g1.getScore()))
                .toList();
    }

    public Optional<Game> findGameById(Long id) {
        return repository.findById(id);
    }

    public Optional<Game> findGameByName(String name) {
        return repository.findByName(name);
    }

    public Game postScore(Game game) {
        if (game.getName() == null || game.getName().isBlank()) {
            long count = repository.findGamesContainingDefaultName(DEFAULT_GAME_NAME);
            game.setName(generateDefaultGameName(count));
        }
        return repository.save(game);
    }

    private String generateDefaultGameName(long count) {
        return count > 0
                ? DEFAULT_GAME_NAME + " " + (count + 1)
                : DEFAULT_GAME_NAME;
    }

    public void deleteGameById(Long id) {
        Game game = findGameById(id).orElseThrow(() ->
                new GameNotFoundException("Game not found with id: " + id));

        repository.delete(game);
    }
}
