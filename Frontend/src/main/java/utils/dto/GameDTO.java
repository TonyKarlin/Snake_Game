package utils.dto;

public record GameDTO(
    String name,
    int foodEaten,
    double duration,
    int score
) {
}
