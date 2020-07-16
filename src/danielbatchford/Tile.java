package danielbatchford;

import java.util.Random;

public class Tile {

    private int value;

    public Tile(int value) {
        this.value = value;
    }

    public Tile() {
            this(new Random().nextFloat() < 0.9 ? 2 : 4);
    }

    public int getValue() {
        return this.value;
    }

    void setValue(int value) {
        this.value = value;
    }
}