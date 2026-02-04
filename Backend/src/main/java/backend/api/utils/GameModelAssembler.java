package backend.api.utils;

import backend.api.controllers.HiscoreController;
import backend.api.entities.Game;
import org.jspecify.annotations.NullMarked;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@NullMarked
@Component
public class GameModelAssembler implements RepresentationModelAssembler<Game, EntityModel<Game>> {

    @Override
    public EntityModel<Game> toModel(Game gameEntity) {
        return EntityModel.of(gameEntity,
                linkTo(methodOn(HiscoreController.class).getGameById(gameEntity.getId())).withSelfRel(),
                linkTo(methodOn(HiscoreController.class).getAllGames()).withRel("games"));
    }
}
