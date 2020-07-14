package danielbatchford;

import java.util.Random;

public class Tile {

    private int value;

    public Tile(int value) {
        this.value = value;
    }

    public Tile() {
        this(2 * (new Random().nextInt(2) + 1));
    }

    public int getValue() {
        return this.value;
    }

    void setValue(int value) {
        this.value = value;
    }
}