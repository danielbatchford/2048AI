package danielbatchford;

import java.util.Arrays;
import java.util.Random;

public class Tile implements Constants {

    private int value;
    private int[] pos;


    public Tile(int[] pos) {
        int index = new Random().nextInt(2);
        this.value = 2 * (index + 1);
        this.pos = pos;
    }

    public Tile() {
        //TODO this can be cleaned up
        this(new int[]{new Random().nextInt(BOARD_X), new Random().nextInt(BOARD_Y)});
    }

    int getValue() {
        return this.value;
    }

    void setValue(int value) {
        this.value = value;
    }

    int[] getPos() {
        return this.pos;
    }

    void setPos(int[] newPos) {
        pos = newPos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile tile = (Tile) o;
        return Arrays.equals(pos, tile.pos);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(pos);
    }
}
