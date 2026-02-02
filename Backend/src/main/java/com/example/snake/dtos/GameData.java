package com.example.snake.dtos;

public record GameData(
        String playerName,
        int foodEaten,
        float duration,
        int score
) {
}
