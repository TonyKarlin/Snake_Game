-- CREATE DATABASE snakedb;
-- DROP DATABASE IF EXISTS snakedb;
\c snakedb;

CREATE TABLE IF NOT EXISTS games
(
    id         BIGSERIAL PRIMARY KEY,
    name       VARCHAR(20) NOT NULL,
    food_eaten INT         NOT NULL DEFAULT 0,
    duration   DECIMAL     NOT NULL DEFAULT 0,
    score      BIGINT      NOT NULL DEFAULT 0,
    timestamp  TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- index to optimize retrieval of top scores
CREATE INDEX idx_game_score ON games (score DESC);

-- view is only for practice purposes and convenience
-- e.g., to avoid writing the same query multiple times
-- doesn't affect performance
CREATE OR REPLACE VIEW top_hiscores AS
SELECT
    g.name,
    g.score
FROM games g
ORDER BY g.score DESC
LIMIT 10;
