package danielbatchford;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game implements Constants {

    public Tile[][] tiles;
    Random random;

    Game() {
        random = new Random();

        tiles = new Tile[BOARD_X][BOARD_Y];

        tiles[random.nextInt(BOARD_X)][random.nextInt(BOARD_Y)] = new Tile();
    }

    public Game(Game game){
        this.tiles = game.tiles;
        this.random = game.random;
    }


    public void step(int[] dir) {

        boolean moved = true;
        while (moved) {
            moved = false;
            for (int x = 0; x < BOARD_X; x++) {
                for (int y = 0; y < BOARD_Y; y++) {
                    Tile tile = tiles[x][y];
                    if (tile == null) {
                        continue;
                    }

                    Tile adj;
                    try {
                        adj = tiles[x + dir[0]][y + dir[1]];
                    } catch (IndexOutOfBoundsException e) {
                        continue;
                    }

                    if (adj == null) {
                        tiles[x + dir[0]][y + dir[1]] = new Tile(tile.getValue());
                        tiles[x][y] = null;
                        moved = true;
                    } else if (adj.getValue() == tile.getValue()) {
                        tiles[x + dir[0]][y + dir[1]] = new Tile(tile.getValue() * 2);
                        tiles[x][y] = null;
                        moved = true;
                    }
                }
            }
        }

        List<int[]> choices = new ArrayList<>();
        for (int x = 0; x < BOARD_X; x++) {
            for (int y = 0; y < BOARD_Y; y++) {
                if (tiles[x][y] == null) {
                    choices.add(new int[]{x, y});
                }
            }

        }
        if (choices.size() == 0) {
            return;
        }

        int[] newTilePos = choices.get(random.nextInt(choices.size()));
        tiles[newTilePos[0]][newTilePos[1]] = new Tile();
    }

    public void step(Direction direction){
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
}
