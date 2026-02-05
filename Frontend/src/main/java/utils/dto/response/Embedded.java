package utils.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import utils.dto.GameDTO;

import java.util.List;

// class to represent the "_embedded" part of the response from the API, which contains the list of games
@JsonIgnoreProperties(ignoreUnknown = true)
public class Embedded {
    private List<GameDTO> gameList;

    public List<GameDTO> getGameList() {
        return gameList;
    }

    public void setGameList(List<GameDTO> gameList) {
        this.gameList = gameList;
    }
}
