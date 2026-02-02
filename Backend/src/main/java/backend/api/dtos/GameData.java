package backend.api.dtos;

public record GameData(
        String playerName,
        int foodEaten,
        float duration,
        int score
) {
}
