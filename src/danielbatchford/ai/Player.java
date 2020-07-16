package danielbatchford.ai;

import danielbatchford.Constants;
import danielbatchford.Direction;
import danielbatchford.Game;
import danielbatchford.Tile;

import java.util.Arrays;

public class Player implements Constants {

    int[][] gridHeuristic;
    Direction[] directions;

    public Player() {

        gridHeuristic = new int[BOARD_X][BOARD_Y];
        for (int x = 0; x < BOARD_X; x++) {
            for (int y = 0; y < BOARD_Y; y++) {
                gridHeuristic[x][y] = x+2*y;
            }
        }
        directions = Direction.values();

    }

    public Direction nextMove(Game gameState) {

        float[] costs = new float[4];
        for (int i = 0, max = directions.length; i < max; i++) {
            Game curr = new Game(gameState);
            curr.step(directions[i]); //TODO - this is the culprit
            costs[i] = getCost(curr);
        }

        int minIndex = 0;
        for (int i = 1; i < 4; i++) {
            if (costs[i] < costs[minIndex]) {
                minIndex = i;
            }
        }
        return directions[minIndex];
    }


    private float getCost(Game game) {
        float sum = 0;
        for (int x = 0; x < BOARD_X; x++) {
            for (int y = 0; y < BOARD_Y; y++) {

                Tile tile = game.tiles[x][y];
                if (tile != null) {
                    sum += tile.getValue() * gridHeuristic[x][y];
                }
            }
        }
        return sum;
    }
}
