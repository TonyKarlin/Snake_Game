package game.models.map;

import game.controllers.GameSettingsController;

import java.util.Random;

public class Map {
    private TileType[][] logicalMap; // A map that indicates each Tiles purpose on the Grid
    private int size;

    public void initializeMap() {
        System.out.println("Initializing map of size: " + size);
        if (size < 1) {
            throw new IllegalArgumentException("Error initializing map! Size cannot be less than 1");
        }

        TileType[][] newMap = new TileType[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                newMap[i][j] = isEdge(i, j, size)
                    ? TileType.OBSTACLE
                    : TileType.EMPTY;
            }
        }
        logicalMap = newMap;
    }

    private void spawnFood(int size) {
        Random rand = new Random();
        int x, y;
        do {
            x = rand.nextInt(1, size - 1);
            y = rand.nextInt(1, size - 1);
        } while (!isEmpty(x, y));

        getLogicalMap()[x][y] = TileType.FOOD;
    }

    private void resetTile(int x, int y) {
        if (logicalMap == null) throw new IllegalStateException("Map not yet initialized");

        if (isOOB(x, y)) throw new IllegalArgumentException("Coordinates out of bounds");

        logicalMap[x][y] = TileType.EMPTY;
    }
    
    public int[][] getCenterPosition() {
        int center = size / 2;
        return new int[][] { {center, center} };
    }

    private boolean isEdge(int i, int j, int size) {
        // Determines weather a position within a nested loop is an edge of a matrix
        return (i == 0 || i == size - 1 || j == 0 || j == size - 1);
    }

    private boolean isEmpty(int x, int y) {
        return getLogicalMap()[x][y] == TileType.EMPTY;
    }

    private boolean isOOB(int x, int y) {
        // Checks if Coordinates are Out Of Bounds
        return (x < 0 || y < 0 || x >= logicalMap.length || y >= logicalMap.length);
    }
    
    public void setSize(int size) {
        this.size = size;
    }

    public TileType[][] getLogicalMap() {
        return logicalMap;
    }
}
