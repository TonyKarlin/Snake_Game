package backend.api.controllers;

import backend.api.dtos.GameData;
import backend.api.entities.Game;
import backend.api.exceptions.GameNotFoundException;
import backend.api.services.GameService;
import backend.api.utils.GameModelAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.EntityModel;

import java.util.List;
import java.util.Map;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/hiscores")
public class HiscoreController {
    private final GameService service;
    private final GameModelAssembler assembler;

    public HiscoreController(GameService service, GameModelAssembler assembler) {
        this.service = service;
        this.assembler = assembler;
    }

    // methods return RESTFUL links to the response using HATEOAS (EntityModel)
    // usually a link to self and to related resources

    // an aggregate root (collection) with links to each item, links to the collection they belong to, and to self
    @GetMapping()
    public CollectionModel<EntityModel<Game>> getAllGames() {
        List<EntityModel<Game>> games = service.getAllGames().stream()
                .map((assembler::toModel)).toList();

        return CollectionModel.of(games,
                linkTo(methodOn(HiscoreController.class).getAllGames()).withSelfRel());
    }

    @GetMapping("/{gameId}")
    public EntityModel<Game> getGameById(@PathVariable Long gameId) {
        Game game = service.findGameById(gameId).orElseThrow(() ->
                new GameNotFoundException("Game not found with id: " + gameId));

        return assembler.toModel(game);
    }

    @GetMapping("/name/{gameName}")
    public EntityModel<Game> getGameByName(@PathVariable String gameName) {
        Game game = service.findGameByName(gameName).orElseThrow(() ->
                new GameNotFoundException("Game not found with name: " + gameName));

        return assembler.toModel(game);
    }

    @PostMapping()
    public ResponseEntity<?> postGame(@RequestBody GameData request) {
        Game game = new Game(request.playerName(), request.foodEaten(), request.duration(), request.score());
        EntityModel<Game> gameModel = assembler.toModel(service.postScore(game));

        return ResponseEntity
                // location header with link to the created resource
                // e.g., /api/hiscores/{id}
                .created(gameModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(gameModel);
    }

    @DeleteMapping("/{gameId}")
    public ResponseEntity<?> deleteGameById(@PathVariable Long gameId) {
        service.deleteGameById(gameId);
        return ResponseEntity.noContent().build();
    }
}
