package danielbatchford;

import java.util.*;

public class Game implements Constants {

    // The current tiles on the grid
    Set<Tile> tiles;

    Random random;

    // Represents the set of all possible tile placements
    Set<Tile> choiceTiles;

    Game() {
        this.tiles = new HashSet<>();
        tiles.add(new Tile());

        random = new Random();

        // Initialise the possible tile placements
        choiceTiles = new HashSet<Tile>();
        for (int x = 0; x < BOARD_X; x++) {
            for (int y = 0; y < BOARD_Y; y++) {
                choiceTiles.add(new Tile(new int[]{x, y}));
            }
        }
    }

    boolean step(Direction direction) {

        // Clone the possible choices
        Set<Tile> choices = new HashSet<Tile>(choiceTiles);

        // Remove all current tiles to prevent overlap
        choices.removeAll(tiles);
        int noOfChoices = choices.size();

        // If game is lost, return
        if (noOfChoices == 0) return false;

        // Build an arraylist from the choice set
        List<Tile> arrChoices = new ArrayList<Tile>(choices);

        // Add a random element from this choice list
        tiles.add(arrChoices.get(random.nextInt(arrChoices.size())));
        return true;
    }


}
