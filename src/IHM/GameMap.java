package IHM;

public class GameMap {
    private final int[][] mapData = {
            {0, 0, 1, 1, 1},
            {0, 0, 0, 1, 1},
            {1, 1, 0, 0, 0}
    };

    // Getters
    public int getWidth() { return mapData[0].length; }
    public int getHeight() { return mapData.length; }
    public int getTile(int row, int col) { return mapData[row][col]; }

}
