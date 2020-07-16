package danielbatchford;

import java.util.Random;

public class Tile {

    // Represents the tile value (e.g 2, 4, ... 1024, ...)
    private final int value;

    public Tile(int value) {
        this.value = value;
    }

    public Tile() {
        this(new Random().nextFloat() < 0.9 ? 2 : 4);
    }

    public int getValue() {
        return this.value;
    }
}