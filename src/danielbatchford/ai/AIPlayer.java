package danielbatchford.ai;

import danielbatchford.Constants;
import danielbatchford.Direction;
import danielbatchford.Game;
import danielbatchford.Tile;

public class AIPlayer implements Constants {

    Direction[] directions;
    int maxTileCount;

    public AIPlayer() {
        maxTileCount = BOARD_X * BOARD_Y;
        directions = Direction.values();
    }

    // Searches the 4 possible states from the current state, returning a direction corresponding to the state with the
    // smallest associated cost.
    public Direction nextMove(Game gameState) {

        // Create a copy of this game state and walk in the first direction
        Game curr = new Game(gameState);
        curr.step(directions[0]);

        // Set the current minimum found cost and index to this first direction's cost
        int minCost = getCost(curr);
        int minIndex = 0;

        // Loop through the other directions
        for (int i = 1, max = directions.length; i < max; i++) {

            // Create a copy of this game state and walk in a new direction
            curr = new Game(gameState);
            curr.step(directions[i]);
            int thisCost = getCost(curr);

            // Check if this new cost is lower than previous ones and if so, update minCost and minIndex accordingly
            if (thisCost < minCost) {
                minCost = thisCost;
                minIndex = i;
            }
        }

        // Return the direction corresponding to this minimum cost
        return directions[minIndex];
    }

    // Calculate the cost at a given game state
    private int getCost(Game game) {

        // Represents the sum of each tile value multiplied by its column index (Gives a tendency to move to the left)
        int weightedSum = 0;

        // Represents the number of diagonal pairs of matching value on the board.
        int diagCount = 0;

        // The number of tiles on the grid
        int tileCount = 0;

        Tile[][] tiles = game.tiles;

        for (int x = 0; x < BOARD_X; x++) {
            for (int y = 0; y < BOARD_Y; y++) {
                Tile tile = tiles[x][y];

                // Skip empty squares
                if (tile == null) {
                    continue;
                }

                // Increment the weighted sum with product of the row index and the tile's value
                weightedSum += x * tile.getValue();
                tileCount++;

                // If all squares are occupied, return an arbitrarily large value such that this option is not picked.
                if (tileCount == maxTileCount) {
                    return 100000;
                }

                // Since only up right and down right diagonals are computed, ignore the last row as this would push
                // index's out of range.
                if (x == BOARD_X - 1) {
                    continue;
                }

                // Retrieve the upper right tile and updates the diagonal counter
                if (y > 0) {
                    Tile ur = tiles[x + 1][y - 1];
                    if (ur != null) {
                        diagCount += (ur.getValue() == tile.getValue()) ? 1 : 0;
                    }
                }

                // Retrieve the lower right tile and updates the diagonal counter
                if (y < BOARD_Y - 1) {
                    Tile dr = tiles[x + 1][y + 1];
                    if (dr != null) {
                        if (dr.getValue() == tile.getValue()) {
                            diagCount += (dr.getValue() == tile.getValue()) ? 1 : 0;
                        }
                    }
                }
            }
        }

        return weightedSum + diagCount;
    }
}
