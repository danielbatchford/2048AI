package danielbatchford;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game implements Constants {

    public Tile[][] tiles;
    Random random;

    // Standard constructor
    Game() {
        random = new Random();
        tiles = new Tile[BOARD_X][BOARD_Y];
        addNewTile();
    }

    // Deep copy constructor
    public Game(Game other) {
        Tile[][] newTiles = new Tile[BOARD_X][BOARD_Y];
        Tile[][] otherTiles = other.tiles;
        for (int x = 0; x < BOARD_X; x++) {
            for (int y = 0; y < BOARD_Y; y++) {
                newTiles[x][y] = otherTiles[x][y];
            }
        }
        this.tiles = newTiles;
        this.random = other.random;
    }

    // Simulate a step of the game in a provided direction
    public void step(int[] dir) {

        boolean moved = true;

        // Continue the loop until the board doesn't get updated in the loop
        while (moved) {
            moved = false;
            for (int x = 0; x < BOARD_X; x++) {
                for (int y = 0; y < BOARD_Y; y++) {
                    Tile tile = tiles[x][y];

                    // If tile is blank, skip it
                    if (tile == null) {
                        continue;
                    }

                    // If current tile + movement vector is out of range, skip it
                    if (x + dir[0] < 0 || x + dir[0] >= BOARD_X || y + dir[1] < 0 || y + dir[1] >= BOARD_Y) {
                        continue;
                    }

                    // Retrieve the adjacent tile to the current tile
                    Tile adj = tiles[x + dir[0]][y + dir[1]];


                    // e.g if this adjacent tile is an empty square.
                    if (adj == null) {

                        // Swap this tile with it's adjacent one
                        tiles[x + dir[0]][y + dir[1]] = new Tile(tile.getValue());
                        tiles[x][y] = null;

                        // Mark the board as updated
                        moved = true;

                    }

                    // Merges tiles together if their values match
                    else if (adj.getValue() == tile.getValue()) {

                        // Remove the current tile and double the value of the adjacent one
                        tiles[x + dir[0]][y + dir[1]] = new Tile(tile.getValue() * 2);
                        tiles[x][y] = null;

                        // Mark the board as updated
                        moved = true;
                    }
                }
            }
        }
    }


    // Produce a direction vector from a provided enum
    public void step(Direction direction) {
        int[] dir;
        switch (direction) {
            case UP:
                dir = new int[]{0, -1};
                break;
            case DOWN:
                dir = new int[]{0, 1};
                break;
            case LEFT:
                dir = new int[]{-1, 0};
                break;
            case RIGHT:
                dir = new int[]{1, 0};
                break;
            default:
                return;
        }

        this.step(dir);
    }


    // Add a new tile in a blank location
    public void addNewTile() {

        // The set of choices to add a tile to
        List<int[]> choices = new ArrayList<>();
        for (int x = 0; x < BOARD_X; x++) {
            for (int y = 0; y < BOARD_Y; y++) {

                // If a tile is empty, add it to the choice list
                if (tiles[x][y] == null) {
                    choices.add(new int[]{x, y});
                }
            }

        }

        // e.g if the board is full
        if (choices.size() == 0) {
            return;
        }

        // Pick a random location from the choices and add it to the board
        int[] newTilePos = choices.get(random.nextInt(choices.size()));
        tiles[newTilePos[0]][newTilePos[1]] = new Tile();
    }


}
